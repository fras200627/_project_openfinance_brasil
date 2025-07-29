package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ConsentPersonalExpirationControlModel;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.repository.jpa.ConsentsExpirationControlPaginationSettings;
import com.ofb.consents.repository.jpa.ConsentsExpirationControlRecordFilter;
import com.ofb.consents.repository.views.ConsentPersonalExpirationControlViewRepository;
import com.ofb.consents.repository.views.ConsentPersonalViewRepository;
import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service @Slf4j
public class ConsentGetExtensionsService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private ConsentPersonalViewRepository consentsRepositoryView;

    @Autowired
    private ConsentPersonalExpirationControlViewRepository consentsExpirationControlRepositoryView;

    public ResponseConsentReadExtensions consentsGetConsentsConsentIdExtensions(String consentId, String authorization, UUID xFapiInteractionId, Integer page, Integer pageSize) {

        List<ResponseConsentReadExtensionsDataInner> listResponseExtensions = new ArrayList<>();
        Page<ConsentPersonalExpirationControlModel> consentRequestList;
        List<ResponseErrorErrorsInner> listError = new ArrayList<>();

        ConsentPersonalModel consentRequested;

        try {
            consentRequested = consentsRepositoryView.findById(consentId).get();
        } catch (NoSuchElementException e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET EXTENSIONS request error")
                    .code(ConsentResponseEnum.CodeEnum.CONSENT_NOT_FOUND.getValue())
                    .detail("The informed consentId does not exist")
                    .build());
            throw new ConsentBadRequestException(new Gson().toJson(listError));
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET EXTENSIONS request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        if (!consentRequested.getStatus().equals("AUTHORISED")) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET EXTENSIONS request error")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Consent cannot be extensions for ExpirationDateTime. Actual Status is [" + consentRequested.getStatus() + "].")
                    .build());
            throw new ConsentUnprocessedEntityException(new Gson().toJson(listError));
        }

        try {
            ConsentsExpirationControlPaginationSettings page_settings = new ConsentsExpirationControlPaginationSettings(page, pageSize, "createAt", "DESC");
            ConsentsExpirationControlRecordFilter filter = new ConsentsExpirationControlRecordFilter(page_settings, null, consentId);
            Specification<ConsentPersonalExpirationControlModel> filterSpecs = this.buildFilter(filter);
            Pageable pageParams = ConsentsExpirationControlPaginationSettings
                    .PaginationSettingsTemplate(filter.page_settings(), "createAt");

            consentRequestList = consentsExpirationControlRepositoryView.findAll(filterSpecs, pageParams);

            for (ConsentPersonalExpirationControlModel reg : consentRequestList.getContent()) {
                LoggedUserExtensions loggedUser = LoggedUserExtensions.builder()
                                .document(LoggedUserDocumentExtensions.builder()
                                        .identification(reg.getLoggedUserIdentification())
                                        .rel(reg.getLoggedUserDocumentRel()).build())
                                        .build();
                listResponseExtensions.add(ResponseConsentReadExtensionsDataInner.builder()
                        .expirationDateTime(reg.getExpirationDatetime())
                        .loggedUser(loggedUser)
                        .previousExpirationDateTime(reg.getPreviusExpirationDatetime())
                        .requestDateTime(reg.getRequestDatetime())
                        .xCustomerUserAgent(reg.getXCustomerUserAgent())
                        .xFapiCustomerIpAddress(reg.getXFapiCustomerIdAddress())
                        .build());

            }
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        Links links = null;
        if (consentRequestList.getContent().size() != 0) {
            int pageFirst   = 1;
            int pageNext    = consentRequestList.getTotalPages() - page == 0 ? consentRequestList.getTotalPages() : page + 1 ;
            int pagePrevius = consentRequestList.getTotalPages() - page == 0 ? consentRequestList.getTotalPages() : page - 1;
            int pageLast    = consentRequestList.getTotalPages();

            links = links.builder().self(URI.create("https://api.banco.com.br/open-banking/api/v1/resource"))
                           .first(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=" + pageFirst + "1&page-size=" + pageSize ))
                           .last(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=" + pageLast + "&page-size=" + pageSize ))
                           .next(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=" +pageNext + "&page-size=" + pageSize ))
                           .prev(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=" + pagePrevius + "&page-size=" + pageSize ))
                           .build();
        }

        MetaExtensions meta  = MetaExtensions.builder()
                                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                                .totalPages(consentRequestList.getTotalPages())
                                .totalRecords((int) consentRequestList.getTotalElements())
                                .build();

        ResponseConsentReadExtensions responseConsentReadData = ResponseConsentReadExtensions.builder()
                .data(listResponseExtensions)
                .meta(meta)
                .links(links)
                .build();

        return responseConsentReadData;
    }

    public Specification<ConsentPersonalExpirationControlModel> buildFilter(ConsentsExpirationControlRecordFilter filter) {

        Specification<ConsentPersonalExpirationControlModel> specs = null;
        RequestFilterParams requestFilterParams = null;

        if (filter.id() != null
                && filter.id().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("id",
                    RequestFilterPredicatesEnum.EQUAL_NUMBER,
                    filter.id().trim().toUpperCase(),
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
