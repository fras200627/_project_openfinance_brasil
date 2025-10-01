package com.ofb.resources.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import com.ofb.resources.model.ResourcesAuthorisedModel;
import com.ofb.resources.repository.ResourcesAuthorisedRecordFilter;
import com.ofb.resources.repository.ResourcesAuthorisedRepository;
import com.ofb.resources.repository.ResourcesAuthorisedlPaginationSettings;
import com.ofb.resources.server.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class ResourcesService {

    @Autowired
    private ResourcesAuthorisedRepository resourcesRepository;

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
