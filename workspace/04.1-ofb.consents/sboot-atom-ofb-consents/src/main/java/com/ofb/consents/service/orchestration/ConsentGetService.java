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
import com.ofb.consents.model.ConsentPermissionAuthorisedModel;
import com.ofb.consents.model.ConsentPermissionRequestedModel;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.repository.data.ConsentPersonalRepository;
import com.ofb.consents.repository.views.ConsentPermissionsAuthorisedlViewRepository;
import com.ofb.consents.repository.views.ConsentPermissionsRequestedlViewRepository;
import com.ofb.consents.repository.views.ConsentPersonalViewRepository;
import com.ofb.consents.server.consents.resources.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ConsentGetService {

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

    @Autowired
    private ConsentPermissionsRequestedlViewRepository permissionsRequested;

    @Value("${app.paths.clients.authentication-server}")
    private String PATH_AUTHENTICATION_SERVER;

    @Autowired private AppAuthenticationResourcesApi authenticationResourcesApi;

    public ResponseConsentRead consentsGetConsentsConsentId(String consentId, String authorization, UUID xFapiInteractionId) {

        List<ResponseErrorErrorsInner> listError = new ArrayList<>();
        ConsentPersonalModel consentRequested;

        try {
            consentRequested = consentsRepositoryView.findById(consentId).get();
        } catch (NoSuchElementException e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET request error")
                    .code(ConsentResponseEnum.CodeEnum.CONSENT_NOT_FOUND.getValue())
                    .detail("The informed consentId does not exist")
                    .build());
            throw new ConsentBadRequestException(new Gson().toJson(listError));
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        List<ResponseConsentReadData.PermissionsEnum> permissions = new ArrayList<>();
        List<String> permissionsAuthorisedList = permissionsAuthorised.findAllConsentsPermissionsNamesAuthorisedByConsentId(consentId);
        if (!permissionsAuthorisedList.isEmpty()) {
            for (String reg : permissionsAuthorisedList.stream().distinct().toList()) {
                permissions.add(ResponseConsentReadData.PermissionsEnum.fromValue(reg));
            }
        } else {
            List<String> permissionRequestedList = permissionsRequested.findAllConsentsPermissionsNamesRequestedByConsentId(consentId);
            for (String reg : permissionRequestedList.stream().distinct().toList()) {
                permissions.add(ResponseConsentReadData.PermissionsEnum.fromValue(reg));
            }
        }

        ResponseConsentReadData responseConsentReadData = ResponseConsentReadData.builder()
                .consentId(consentId)
                .creationDateTime(consentRequested.getCreationDatetime())
                .status(ResponseConsentReadData.StatusEnum.fromValue(consentRequested.getStatus()))
                .statusUpdateDateTime(consentRequested.getStatusUpdateDatetime())
                .permissions(permissions)
                .build();

        if (consentRequested.getExpirationDatetime() != null) {
            responseConsentReadData.setExpirationDateTime(consentRequested.getExpirationDatetime());
        }

        if (consentRequested.getStatus().equals("REJECT")) {
            ResponseConsentReadDataRejection responseRejection = ResponseConsentReadDataRejection.builder()
                    .reason(ResponseConsentReadDataRejectionReason.builder()
                            .additionalInformation(consentRequested.getRejectedAdditionalInfo())
                            .code(ResponseConsentReadDataRejectionReason.CodeEnum.fromValue(consentRequested.getRejectedCode()))
                            .build())
                    .rejectedBy(EnumRejectedBy.fromValue("USER"))
                    .build();
            responseConsentReadData.setRejection(responseRejection);
        }

        if (consentRequested.getStatus().equals("AUTHORISED") &&
            (consentRequested.getAccessTokenAuthorised() == null || consentRequested.getAccessTokenAuthorised().isEmpty())) {
            String accessToken = this.getAcessToken(consentRequested, authorization, xFapiInteractionId);
            httpServletResponse.addHeader("AccessToken", accessToken);
            ConsentPersonalData consentCreated = consentPersonalRepository.findById(consentRequested.getConsentId()).get();
            Timestamp timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
            consentCreated.setModifyAt(timestampThisOperation);
            consentCreated.setUserCode("ConsentsServiceAPI");
            consentCreated.setAccessTokenAuthorised(accessToken);
            consentPersonalRepository.saveAndFlush(consentCreated);
        }

        LinksConsents links          = LinksConsents.builder().self(URI.create("https://api.banco.com.br/open-banking/api/v1/resource")).build();
        Meta meta                    = Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build();

        ResponseConsentRead responseConsentRead = ResponseConsentRead.builder()
                .data(responseConsentReadData)
                .meta(meta)
                .links(links)
                .build();

        return responseConsentRead;
    }

    private String getAcessToken(ConsentPersonalModel consentsPersonalAccepted, String authorization, UUID xFapiInteractionId) {

        /// Verificação dos 60 minutos: deve ser alterado de AWAITING_AUTHORISATION para REJECTED após 60 minutos.

        /// CREATE AND REGISTER A ACCESS TOKEN FOR CONSENT
        /// Step 01 - RegisteredClients API parameters
        authenticationResourcesApi.getApiClient().setBasePath(PATH_AUTHENTICATION_SERVER);
        authenticationResourcesApi.getApiClient().setBearerToken(httpServletRequest.getHeader("Authorization").replace("Bearer ", ""));

        /// Step 02 -
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setConsentId(consentsPersonalAccepted.getConsentId());
        accessTokenRequest.setCreationDateTime(consentsPersonalAccepted.getCreationDatetime().substring(0, 19) + "Z");
        accessTokenRequest.setExpirationDateTime(consentsPersonalAccepted.getExpirationDatetime().substring(0, 19) + "Z");

        /// Step 03 -
        LoggedUserDocument loggedUserDocument = new LoggedUserDocument();
        loggedUserDocument.setLoggedUserName(consentsPersonalAccepted.getCivilName());
//        loggedUserDocument.setIdentification(consentsPersonalAccepted.getLoggedUserIdentification());
//        loggedUserDocument.setRel(consentsPersonalAccepted.getLoggedUserDocumentRel());
        loggedUserDocument.setIdentification("73991016982");
        loggedUserDocument.setRel("CPF");

        /// Step 04 -
        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setDocument(loggedUserDocument);
        accessTokenRequest.setLoggedUser(loggedUser);

        /// Step 05 -
        BusinessEntityDocument businessEntityDocument = new BusinessEntityDocument();
        businessEntityDocument.setEntityBusinessName(httpServletRequest.getUserPrincipal().getName());
//        businessEntityDocument.setIdentification(consentsPersonalAccepted.getBusinessEntityIdentification());
//        businessEntityDocument.setRel(consentsPersonalAccepted.getBusinessEntityDocumentRel());
        businessEntityDocument.setIdentification("11111111111111");
        businessEntityDocument.setRel("CNPJ");
        /// Step 06 -
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setDocument(businessEntityDocument);
        accessTokenRequest.setBusinessEntity(businessEntity);

        /// Step 07 -
        List<ConsentPermissionAuthorisedModel> listPermissionAuthorised = permissionsAuthorised.findAllConsentsPermissionsAuthorisedByConsentId(
                consentsPersonalAccepted.getConsentId());
        List<AccessTokenRequest.ScopesEnum> scopes = new ArrayList<>();
        for (ConsentPermissionAuthorisedModel reg : listPermissionAuthorised) {
            scopes.add(AccessTokenRequest.ScopesEnum.fromValue(reg.getPermission()));
        }
        accessTokenRequest.setScopes(scopes);

        /// Step 08 -
        TokenResponseModelTemplate tokenResponseModelTemplate = authenticationResourcesApi.postAccessTokenConsents(accessTokenRequest, xFapiInteractionId);
        log.info("ConsentId [" + consentsPersonalAccepted.getConsentId() + "]  tokenAccess [" + tokenResponseModelTemplate.getAccessToken() + "]");

        return tokenResponseModelTemplate.getAccessToken();
    }

}
