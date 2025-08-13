package com.ofb.resources.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import com.ofb.resources.client.consent.resources.handler.ConsentsApi;
import com.ofb.resources.client.consent.resources.model.ResponseConsentRead;
import com.ofb.resources.model.ResourcesAuthorisedModel;
import com.ofb.resources.model.ResourcesPermissionsAuthorisedModel;
import com.ofb.resources.repository.ResourcesAuthorisedRecordFilter;
import com.ofb.resources.repository.ResourcesAuthorisedRepository;
import com.ofb.resources.repository.ResourcesAuthorisedlPaginationSettings;
import com.ofb.resources.repository.ResourcesPermissionsAuthorisedRepository;
import com.ofb.resources.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class ResourcesService {

    @Value("${app.paths.clients.ofb-consents}")
    private String OFB_PATH_CONSENTS;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ConsentsApi consentsApi;

    @Autowired
    private ResourcesAuthorisedRepository resourcesRepository;

    @Autowired
    private ResourcesPermissionsAuthorisedRepository resourcesPermissionsRepository;

    @Autowired
    private JwtDecoder jwtDecoder;

    public ResponseResourceList resourcesGetResources(String authorization,
                                                      UUID xFapiInteractionId,
                                                      String xFapiAuthDate,
                                                      String xFapiCustomerIpAddress,
                                                      String xCustomerUserAgent,
                                                      Integer page, Integer pageSize) {

        Gson gson = new Gson();
        String      consentId;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        ResponseConsentRead               responseConsentRead = null;

        /// Extract AccessToken claims values
        try {
            consentId = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        ///  Consents Resources API parameters
        try {
            consentsApi.getApiClient().setBasePath(OFB_PATH_CONSENTS);
            consentsApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));
            responseConsentRead = consentsApi.consentsGetConsentsConsentId(consentId,
                    authorization,
                    xFapiInteractionId,
                    xFapiAuthDate,
                    xFapiCustomerIpAddress,
                    xCustomerUserAgent);
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Resources request error (in ConsentsAPI method consentsGetConsentsConsentId)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Resources request error (in ConsentsAPI method consentsGetConsentsConsentId)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in ConsentsAPI method consentsGetConsentsConsentId)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Validations
        if (!responseConsentRead.getData().getStatus().getValue().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES information. The consentId (" + consentId + ") provided in the " +
                            "AccessToken has a current status of [" + responseConsentRead.getData().getStatus().getValue() + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(responseConsentRead.getData().getExpirationDateTime()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES information. The consentId (" + consentId + ") provided in " +
                            "the AccessToken has an ExpirationDateTime " +
                            "(" +
                            responseConsentRead.getData().getExpirationDateTime()
                            + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Search Consent Resources
        Page<ResourcesAuthorisedModel> resourcesConfirmedList;
        List<ResponseResourceListDataInner> responseData = new ArrayList<>();
        int recordsNotInclude = 0;

        try {
            ResourcesAuthorisedlPaginationSettings page_settings = new ResourcesAuthorisedlPaginationSettings(page, pageSize, "consentResourceId", "DESC");
            ResourcesAuthorisedRecordFilter filter = new ResourcesAuthorisedRecordFilter(page_settings, null, consentId);
            Specification<ResourcesAuthorisedModel> filterSpecs = this.buildFilter(filter);
            Pageable pageParams = ResourcesAuthorisedlPaginationSettings
                    .PaginationSettingsTemplate(filter.page_settings(), "consentResourceId");

            resourcesConfirmedList = resourcesRepository.findAll(filterSpecs, pageParams);

            for (ResourcesAuthorisedModel reg : resourcesConfirmedList.getContent()) {
                if (!reg.getResourceType().trim().toUpperCase().equals("CUSTOMER")) {
                    responseData.add(ResponseResourceListDataInner.builder()
                            .resourceId(reg.getResourceId())
                            .status(ResponseResourceListDataInner.StatusEnum.fromValue(reg.getResourceStatus().trim().toUpperCase()))
                            .type(ResponseResourceListDataInner.TypeEnum.fromValue(reg.getResourceType().trim().toUpperCase()))
                            .build());
                } else {
                    recordsNotInclude++;
                }
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in FindAll Resources registry)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        /// Build response objects
        ResponseResourceList responseResourceList;
        Links links = null;
        MetaResponse meta;
        try {
            if (resourcesConfirmedList.getContent().size() != 0) {
                int pageFirst = 1;
                int pageNext = resourcesConfirmedList.getTotalPages() - page == 0 ? resourcesConfirmedList.getTotalPages() : page + 1;
                int pagePrevius = resourcesConfirmedList.getTotalPages() - page == 0 ? resourcesConfirmedList.getTotalPages() : page - 1;
                int pageLast = resourcesConfirmedList.getTotalPages();

                links = links.builder().self(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources"))
                        .first(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page=" + pageFirst + "&page-size=" + pageSize))
                        .last(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page=" + pageLast + "&page-size=" + pageSize))
                        .next(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page=" + pageNext + "&page-size=" + pageSize))
                        .prev(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page=" + pagePrevius + "&page-size=" + pageSize))
                        .build();
            }

            meta = MetaResponse.builder()
                    .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                    .totalPages(resourcesConfirmedList.getTotalPages())
                    .totalRecords((int) resourcesConfirmedList.getTotalElements() - recordsNotInclude)
                    .build();

            responseResourceList = ResponseResourceList.builder()
                    .data(responseData)
                    .links(links)
                    .meta(meta)
                    .build();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in build ResponseResourceList)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        return responseResourceList;
    }

    public ResponseResourcePermissionsList resourcesGetResourcesPermissions(String authorization, String consentId) {

        Gson gson = new Gson();
        ConsentIdentification consentIdentification = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        List<ResourcesPermissionsAuthorisedModel> listResourcesPermissions = new ArrayList<>();
        List<ResourcesAuthorisedInner> listResourcesAuthorised = new ArrayList<>();
        List<PermissionsInner> listPermissions = new ArrayList<>();

        /// Validations
        try {
            listResourcesPermissions = resourcesPermissionsRepository.findAllResourcesAccountsPermissionsByConsentId(consentId);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (listResourcesPermissions.size() == 0) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in the " +
                            "request has not found in authorized resources.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        for (ResourcesPermissionsAuthorisedModel reg : listResourcesPermissions) {
            consentIdentification = ConsentIdentification.builder()
                    .consentId(reg.getConsentId())
                    .consentStatus(reg.getConsentStatus())
                    .consentDateCreation(reg.getConsentDateCreation())
                    .consentExpiration(reg.getConsentExpiration())
                    .personalCPF(reg.getPersonalCPF())
                    .personalId(reg.getPersonalId())
                    .personalName(reg.getPersonalName())
                    .build();
            break;
        }

        if (!consentIdentification.getConsentStatus().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in the " +
                            "request has a current status of [" + consentIdentification.getConsentStatus() + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(consentIdentification.getConsentExpiration()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in " +
                            "the request has an ExpirationDateTime " +
                            "(" +
                            consentIdentification.getConsentExpiration()
                            + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        String resourceId = listResourcesPermissions.get(0).getResourceId();
        ResourcesAuthorisedInner resourcesAuthorisedInner = null;
        for (ResourcesPermissionsAuthorisedModel reg : listResourcesPermissions) {

            if (!reg.getResourceId().equals(resourceId)) {
                listResourcesAuthorised.add(resourcesAuthorisedInner);
                resourceId = reg.getResourceId();
                listPermissions = new ArrayList<>();
                resourcesAuthorisedInner = new ResourcesAuthorisedInner();
            }

            listPermissions.add(PermissionsInner.builder()
                    .permission(reg.getPermission())
                    .build());

            resourcesAuthorisedInner = ResourcesAuthorisedInner.builder()
                    .resourceId(reg.getResourceId())
                    .resourceStatus(reg.getResourceStatus())
                    .resourceSummary(reg.getResourceIdSummary())
                    .resourceType(reg.getResourceType())
                    .permissions(listPermissions)
                    .build();

        }
        // add last resource after loop
        listResourcesAuthorised.add(resourcesAuthorisedInner);

        ResponseResourcePermissionsList responseResourcePermissionsList = ResponseResourcePermissionsList.builder()
                .data(ResourcesPermissionsData.builder()
                        .consentIdentification(consentIdentification)
                        .resourcesAuthorised(listResourcesAuthorised)
                        .build())
                .meta(Meta.builder()
                        .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                        .build())
                .build();

        return responseResourcePermissionsList;
    }

    private Specification<ResourcesAuthorisedModel> buildFilter(ResourcesAuthorisedRecordFilter filter) {
        Specification<ResourcesAuthorisedModel> specs = null;
        RequestFilterParams requestFilterParams = null;

        if (filter.consentResourceId() != null
                && filter.consentResourceId().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("id",
                    RequestFilterPredicatesEnum.EQUAL_NUMBER,
                    filter.consentResourceId().trim().toUpperCase(),
                    null);

            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.consentId() != null
                && filter.consentId().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("consentId",
                    RequestFilterPredicatesEnum.EQUAL_STRING,
                    filter.consentId().trim().toUpperCase(),
                    null);

            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        return specs;
    }

}
