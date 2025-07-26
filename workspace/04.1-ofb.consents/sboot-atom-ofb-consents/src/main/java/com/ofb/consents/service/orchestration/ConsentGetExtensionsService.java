package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.client.authentication.resources.handler.AppAuthenticationResourcesApi;
import com.ofb.consents.client.authentication.resources.model.*;
import com.ofb.consents.client.authentication.resources.model.BusinessEntity;
import com.ofb.consents.client.authentication.resources.model.BusinessEntityDocument;
import com.ofb.consents.client.authentication.resources.model.LoggedUser;
import com.ofb.consents.client.authentication.resources.model.LoggedUserDocument;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ConsentPermissionAuthorisedModel;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.repository.data.ConsentPersonalRepository;
import com.ofb.consents.repository.views.ConsentPermissionsAuthorisedlViewRepository;
import com.ofb.consents.repository.views.ConsentPersonalViewRepository;
import com.ofb.consents.server.consents.resources.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.sql.Timestamp;
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
    private ConsentPersonalRepository consentPersonalRepository;

    @Autowired
    private ConsentPermissionsAuthorisedlViewRepository permissionsAuthorised;

    @Value("${app.paths.clients.authentication-server}")
    private String PATH_AUTHENTICATION_SERVER;

    @Autowired private AppAuthenticationResourcesApi authenticationResourcesApi;

    public ResponseConsentReadExtensions consentsGetConsentsConsentIdExtensions(String consentId, String authorization, UUID xFapiInteractionId, Integer page, Integer pageSize) {

        List<ResponseConsentReadExtensionsDataInner> listResponseExtentions = new ArrayList<>();
        Page<ConsentPersonalModel> consentRequestList;
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
            Pageable pageable =  PageRequest.of(Long.valueOf(page - 1).intValue(), Long.valueOf(pageSize).intValue());
            consentRequestList = consentsRepositoryView.findAll(pageable);
            for (ConsentPersonalModel reg : consentRequestList.getContent()) {
                LoggedUserExtensions loggedUser = LoggedUserExtensions.builder()
                                .document(LoggedUserDocumentExtensions.builder()
                                        .identification(reg.getLoggedUserIdentification())
                                        .rel(reg.getLoggedUserDocumentRel()).build())
                                        .build();
                listResponseExtentions.add(ResponseConsentReadExtensionsDataInner.builder()
                        .expirationDateTime(reg.getExpirationDatetime())
                        .loggedUser(loggedUser)
                        .previousExpirationDateTime(reg.getExpirationDatetime())
                        .requestDateTime(reg.getCreationDatetime())
                        .xCustomerUserAgent(reg.getLoggedUserIdentification())
                        .xFapiCustomerIpAddress(null)
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
                .data(listResponseExtentions)
                .meta(meta)
                .links(links)
                .build();

        return responseConsentReadData;
    }

}
