package com.ofb.consents.service.validation;

import com.google.gson.Gson;
import com.ofb.consents.client.clients.business.resources.handler.ClientsBusinessResourcesApi;
import com.ofb.consents.client.clients.business.resources.model.OAuth2ClientResponse;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.server.consents.resources.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;



@Service @Slf4j
public class ValidateBusinessEntityService {

    @Value("${app.paths.clients.registered-clients}")
    private String pathRegisteredClients;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ClientsBusinessResourcesApi registeredClientsResourcesApi;

    /**
     * Validates a BusinessEntity for consent information
     * https://openfinancebrasil.atlassian.net/wiki/spaces/OF/pages/219480491/Orienta+es+-+DC+Consentimento
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the validate function must contain valid 'Document' information for the BusinessEntity requesting consent creation.
     * <p> The objectData can be:
     * <p> - String -->> passing the 'Document' directly
     * <p> - CreateConsent Object -->> where CreateConsent.getData().getBusiness().getDocument().getIdentification() is valid.
     * <p> - CreateConsentData Object -->> where CreateConsentData().getBusiness().getDocument().getIdentification() is valid.
     * <p> - BusinessEntity Object -->> where BusinessEntity.getDocument().getIdentification() is valid. * <p> - BusinessEntityDocument object -->> where BusinessEntityDocument().getIdentification() is valid.
     * <br>
     * @param referenceId "Optional (may be null)"
     * <p>The referenceId parameter is used to set a 'Key' value, if needed. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b></p>
     * <br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt method execution
     * throwing a 'ConsentResponseErrorException' exception
     * <br>
     * @return ResponseValidateConsentModel
     * <p>Returns ObjectResponse with results for validation
     * <p><b>Note</b></p>
     * <p>in the 'objectData' field, returns an OAuth2ClientResponse object
     * <br>
     * @throws ConsentUnprocessedEntityException
     * <p>if an error occurs while trying to invoke the method<p></p>
     */
    public ResponseValidateConsentModel validateBusinessEntityInformation(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        ///  RegisteredClients API parameters
        registeredClientsResourcesApi.getApiClient().setBasePath(pathRegisteredClients);
        registeredClientsResourcesApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));

        /// Registered Client validate steps
        String registeredClientName                       = "";
        OAuth2ClientResponse registeredClient             = new OAuth2ClientResponse();
        List<ResponseErrorErrorsInner> listResponseErrors = new ArrayList<>();

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent Business Entity")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the BusinessEntity document")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (objectData instanceof String) {
                registeredClientName = (String) objectData;
            } else if (objectData instanceof CreateConsent) {
                registeredClientName = ((CreateConsent) objectData).getData().getBusinessEntity().getDocument().getIdentification();
            } else if (objectData instanceof CreateConsentData) {
                registeredClientName = ((CreateConsentData) objectData).getBusinessEntity().getDocument().getIdentification();
            } else if (objectData instanceof BusinessEntity) {
                registeredClientName = ((BusinessEntity) objectData).getDocument().getIdentification();
            }else if (objectData instanceof BusinessEntityDocument) {
                registeredClientName = ((BusinessEntityDocument) objectData).getIdentification();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent Business Entity error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("BusinessEntity verification error. Business Entity is mandatory and must inform the BusinessEntity document")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .objectException(e)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }

        /// Search Registered Client
        try {
            registeredClientName = request.getUserPrincipal().getName();
            registeredClient = registeredClientsResourcesApi.getFindByClientId(registeredClientName);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent Business Entity")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Registered Client '" + registeredClientName + "' does not exist in the OFB registered client database")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent Business Entity error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error (API not active) occurred while checking the requested Registered Client: '" + registeredClientName + "'")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .objectException(e)
                    .build();
        }

        ///  Registered Client return validate
        if (registeredClient == null) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent Business Entity")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Registered Client '" + registeredClientName + "' does not exist in the OFB registered client database")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .build();
        } else {
            if (registeredClient.getStatus() != null && !registeredClient.getStatus().equals("ACTIVE")) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent Business Entity")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Registered Client '" + registeredClientName +
                                "' (" + registeredClient.getClientName() + ") is not authorized. Current status is '" +
                                registeredClient.getStatus() + "' and is not valid at this time")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
            }
            if (registeredClient.getSecurityScope() != null && !registeredClient.getSecurityScope().contains("client.ofb.read")) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent Business Entity")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Registered Client '" + registeredClientName +
                                "' (" + registeredClient.getClientName() +
                                ") does not have scope/grant 'ofb.client.read'.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
            }
            if (registeredClient.getSecurityScope() != null && !registeredClient.getSecurityScope().contains("client.ofb.write")) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent Business Entity")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Registered Client '" + registeredClientName +
                                "' (" + registeredClient.getClientName() +
                                ") does not have scope/grant 'ofb.client.write'.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
            }
        }

        return ResponseValidateConsentModel.builder()
                .errorsListed(!listResponseErrors.isEmpty() ? true : false)
                .objectData(registeredClientName)
                .responseErrorsList(listResponseErrors)
                .build();
    }

}
