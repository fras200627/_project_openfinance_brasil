package com.ofb.resources.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import com.ofb.lib.handlers.exception.template.MetaErrorResponseTemplate;
import com.ofb.lib.handlers.exception.template.ResponseErrorTemplate;
import com.ofb.resources.client.consent.resources.handler.ConsentsApi;
import com.ofb.resources.client.consent.resources.model.ResponseConsentRead;
import com.ofb.resources.client.consent.resources.model.ResponseErrorErrorsInner;
import com.ofb.resources.model.ResourcesConfirmedModel;
import com.ofb.resources.repository.ResourcesConfirmedRecordFilter;
import com.ofb.resources.repository.ResourcesConfirmedRepository;
import com.ofb.resources.repository.ResourcesConfirmedlPaginationSettings;
import com.ofb.resources.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class ResourcesService {

    @Value("${app.paths.clients.consent-resources}")
    private String pathConsentResources;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ConsentsApi consentsApi;

    @Autowired
    private ResourcesConfirmedRepository resourcesRepository;

    public ResponseResourceList resourcesGetResources(String authorization,
                                                      UUID xFapiInteractionId,
                                                      String xFapiAuthDate,
                                                      String xFapiCustomerIpAddress,
                                                      String xCustomerUserAgent,
                                                      Integer page, Integer pageSize) {

        Gson gson = new Gson();
        Base64.Decoder decoder = Base64.getUrlDecoder();
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        /// Extract AccessToken values
        String      subMember = authorization.replace("Bearer ", "").split("\\.")[1];
        JsonObject  subObject = gson.fromJson(new String(decoder.decode(subMember)), JsonObject.class);
        String      consentId = subObject.get("ofb.consent.id").getAsString();

        ///  Consents Resources API parameters
        consentsApi.getApiClient().setBasePath(pathConsentResources);
        consentsApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));
        ResponseConsentRead response =  consentsApi.consentsGetConsentsConsentId(consentId,
                                                                                authorization,
                                                                                xFapiInteractionId,
                                                                                xFapiAuthDate,
                                                                                xFapiCustomerIpAddress,
                                                                                xCustomerUserAgent);

        /// Validations
        if (!response.getData().getStatus().getValue().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES information. The consentId (" + consentId + ") provided in the " +
                            "AccessToken has a current status of [" + response.getData().getStatus().getValue() + "].")
                    .build());
            throw new BadRequestException(new com.google.gson.Gson().toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(response.getData().getExpirationDateTime()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES information. The consentId (" + consentId + ") provided in " +
                            "the AccessToken has an ExpirationDateTime " +
                            "(" + subObject.get("ofb.consent.expiration.datetime").getAsString() + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(new com.google.gson.Gson().toJson(listResponseErrors));
        }

        /// Search Reasources
        Page<ResourcesConfirmedModel> resourcesConfirmedList;
        List<ResponseResourceListDataInner> responseData = new ArrayList<>();
        int recordsNotInclude = 0;

        try {
            ResourcesConfirmedlPaginationSettings page_settings = new ResourcesConfirmedlPaginationSettings(page, pageSize, "consentResourceId", "DESC");
            ResourcesConfirmedRecordFilter filter = new ResourcesConfirmedRecordFilter(page_settings, null, consentId);
            Specification<ResourcesConfirmedModel> filterSpecs = this.buildFilter(filter);
            Pageable pageParams = ResourcesConfirmedlPaginationSettings
                    .PaginationSettingsTemplate(filter.page_settings(), "consentResourceId");

            resourcesConfirmedList = resourcesRepository.findAll(filterSpecs, pageParams);

            for (ResourcesConfirmedModel reg : resourcesConfirmedList.getContent()) {
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
                    .title("Consent GET request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(new com.google.gson.Gson().toJson(listResponseErrors));
        }

        /// Build response objects
        Links links = null;
        if (resourcesConfirmedList.getContent().size() != 0) {
            int pageFirst   = 1;
            int pageNext    = resourcesConfirmedList.getTotalPages() - page == 0 ? resourcesConfirmedList.getTotalPages() : page + 1 ;
            int pagePrevius = resourcesConfirmedList.getTotalPages() - page == 0 ? resourcesConfirmedList.getTotalPages() : page - 1;
            int pageLast    = resourcesConfirmedList.getTotalPages();

            links = links.builder().self(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources"))
                    .first(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page="  + pageFirst   + "&page-size=" + pageSize))
                    .last(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page="   + pageLast    + "&page-size=" + pageSize))
                    .next(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page="   + pageNext    + "&page-size=" + pageSize))
                    .prev(URI.create("https://api.banco.com.br/open-banking/resources/v3/resources?page="   + pagePrevius + "&page-size=" + pageSize))
                    .build();
        }

        MetaResponse meta  = MetaResponse.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                .totalPages(resourcesConfirmedList.getTotalPages())
                .totalRecords((int) resourcesConfirmedList.getTotalElements() - recordsNotInclude)
                .build();

        ResponseResourceList responseResourceList = ResponseResourceList.builder()
                .data(responseData)
                .links(links)
                .meta(meta)
                .build();

        return responseResourceList;
    }

    private Specification<ResourcesConfirmedModel> buildFilter(ResourcesConfirmedRecordFilter filter) {

        Specification<ResourcesConfirmedModel> specs = null;
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
