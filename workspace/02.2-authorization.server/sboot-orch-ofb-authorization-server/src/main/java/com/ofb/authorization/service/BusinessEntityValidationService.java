package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.consents.model.*;
import com.ofb.authorization.client.participants.handler.ClientsBusinessResourcesApi;
import com.ofb.authorization.client.participants.model.OAuth2ClientResponse;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.ofb.UnprocessedEntityException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Passo 03: Verificação ds BusinessEntity (Participante indicado no client.document):
 *     O Participante existe na base de participantes ?
 *     O Participante deve estar ATIVO e DESBLOQUEADO (status: ACTIVE, LOCKED, INACTIVE)
 *     O Participante deve ter data de expiração válida. (client_secret_expires_at)
 *     O Participante possui as roles necessarias: client.ofb.read e client.ofb.write?
 */
@Service @Slf4j
public class BusinessEntityValidationService {

    @Value("${app.parameters.consents.validate.check-if-consent-already-exists}")
    private boolean EXECUTE_CHECK_IF_CONSENTS_ALREADY_EXISTS;

    @Value("${app.parameters.execute-throw-immediately}")
    private boolean EXECUTE_THROW_IMMEDIATELY;

    @Value("${app.parameters.consents.organization}")
    private String CONSENTS_ORGANIZATION;

    @Value("${app.paths.clients.consents-api}")
    private String PATH_CONSENTS_API;

    @Value("${app.paths.clients.participants-api}")
    private String PATH_PARTICIPANTS_API;

    @Value("${app.paths.clients.customers-api}")
    private String PATH_CUSTOMERS_API;

    @Value("${app.paths.clients.resources-api}")
    private String PATH_RESOURCES_API;

    private final HttpServletRequest request;
    private final ClientsBusinessResourcesApi registeredClientsResourcesApi = null;
    private List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
    private String registeredClientName = "";
    private OAuth2ClientResponse registeredClient = new OAuth2ClientResponse();

    public BusinessEntityValidationService(HttpServletRequest request, ClientsBusinessResourcesApi registeredClientsResourcesApi) {
        this.request = request;
        ///  RegisteredClients API parameters
        this.registeredClientsResourcesApi.getApiClient().setBasePath(PATH_PARTICIPANTS_API);
        this.registeredClientsResourcesApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));
    }

    public void businessEntityValidate(Object objectData, Object referenceId) {
        this.businessEntityObjectParamsValidate(objectData, referenceId);
        this.businessEntityIfExistsValidate(objectData, referenceId);
        this.businessEntityStatusValidate(objectData, referenceId);
        this.businessEntityExpirationValidate(objectData, referenceId);
        this.businessEntitySecurityScopesValidate(objectData, referenceId);

        if (!listResponseErrors.isEmpty()) {
            throw new UnprocessedEntityException(new Gson().toJson(listResponseErrors));
        }
    }

    public void businessEntityObjectParamsValidate(Object objectData, Object referenceId) {
        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Business Entity")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the BusinessEntity document")
                        .build());
                if (EXECUTE_THROW_IMMEDIATELY) {
                    throw new UnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
            } else if (objectData instanceof String) {
                registeredClientName = (String) objectData;
            } else if (objectData instanceof CreateConsent) {
                registeredClientName = ((CreateConsent) objectData).getData().getBusinessEntity().getDocument().getIdentification();
            } else if (objectData instanceof CreateConsentData) {
                registeredClientName = ((CreateConsentData) objectData).getBusinessEntity().getDocument().getIdentification();
            } else if (objectData instanceof BusinessEntity) {
                registeredClientName = ((BusinessEntity) objectData).getDocument().getIdentification();
            } else if (objectData instanceof BusinessEntityDocument) {
                registeredClientName = ((BusinessEntityDocument) objectData).getIdentification();
            } else if (objectData instanceof BusinessEntityExtensions) {
                registeredClientName = ((BusinessEntityExtensions) objectData).getDocument().getIdentification();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("BusinessEntity verification error. Business Entity is mandatory and must inform the BusinessEntity document")
                    .build());
            if (EXECUTE_THROW_IMMEDIATELY) {
                throw new InternalErrorException(new Gson().toJson(listResponseErrors));
            }
        }

    }

    public void businessEntityIfExistsValidate(Object objectData, Object referenceId) {

        /// Search Registered Client
        try {
            registeredClientName = request.getUserPrincipal().getName();
            registeredClient = registeredClientsResourcesApi.getFindByClientId(registeredClientName);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Registered Client '" + registeredClientName + "' does not exist in the OFB registered client database")
                    .build());
            if (EXECUTE_THROW_IMMEDIATELY) {
                throw new UnprocessedEntityException(new Gson().toJson(listResponseErrors));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error (API not active) occurred while checking the requested Registered Client: '" + registeredClientName + "'")
                    .build());
            if (EXECUTE_THROW_IMMEDIATELY) {
                throw new InternalErrorException(new Gson().toJson(listResponseErrors));
            }
        }
    }

    public void businessEntityStatusValidate(Object objectData, Object referenceId) {
    }

    public void businessEntityExpirationValidate(Object objectData, Object referenceId) {
    }

    public void businessEntitySecurityScopesValidate(Object objectData, Object referenceId) {

        ///  Registered Client return validate
        if (registeredClient == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Registered Client '" + registeredClientName + "' does not exist in the OFB registered client database")
                    .build());
        } else {
            if (registeredClient.getStatus() != null && !registeredClient.getStatus().equals("ACTIVE")) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Business Entity")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Registered Client '" + registeredClientName +
                                "' (" + registeredClient.getClientName() + ") is not authorized. Current status is '" +
                                registeredClient.getStatus() + "' and is not valid at this time")
                        .build());
            }
            if (registeredClient.getSecurityScope() != null && !registeredClient.getSecurityScope().contains("client.ofb.read")) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Business Entity")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Registered Client '" + registeredClientName +
                                "' (" + registeredClient.getClientName() +
                                ") does not have scope/grant 'ofb.client.read'.")
                        .build());
            }
            if (registeredClient.getSecurityScope() != null && !registeredClient.getSecurityScope().contains("client.ofb.write")) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Business Entity")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Registered Client '" + registeredClientName +
                                "' (" + registeredClient.getClientName() +
                                ") does not have scope/grant 'ofb.client.write'.")
                        .build());
            }
        }

    }
}
