package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentResponseErrorException;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.repository.data.*;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.consents.service.persistence.ConsentCancelService;
import com.ofb.consents.service.persistence.ConsentCreateService;
import com.ofb.consents.service.persistence.ConsentCreatePermissionsService;
import com.ofb.consents.service.persistence.ConsentAwaitingAuthorizationService;
import com.ofb.consents.service.validation.*;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class ConsentOrchestrationService {

    @Value("${app.consents.validate.check-if-consent-already-exists}")
    private boolean consentsValidateAlreadyExists;

    @Value("${app.consents.validate.execute-throw-immediately}")
    private boolean executeThrowImmediately;

    @Value("${app.consents.consent-id-urn-use}")
    private String consentIdUrnUse;

    @Value("${amqp.audit_services.audit_exchange}")
    private String queueConsentAdmin;

    @Autowired private ValidateBusinessEntityService validateBusinessEntityService;
    @Autowired private ValidateLoggedUserService validateLoggedUserService;
    @Autowired private ValidateConsentAlreadyExistsService validateConsentAlreadyExistsService;
    @Autowired private ValidateExpirationDatetimeService validateExpirationDatetimeService;
    @Autowired private ValidateGroupsAndPermissionsService validateGroupsAndPermissionsService;
    @Autowired private ValidatePermissionsRequestedService validatePermissionsRequestedService;
    @Autowired private ConsentCreateService consentCreateService;
    @Autowired private ConsentCancelService consentCancelService;
    @Autowired private ConsentCreatePermissionsService consentCreatePermissionsService;
    @Autowired private ConsentAwaitingAuthorizationService consentAwaitingAuthorizationService;
    @Autowired private ConsentPersonalRepository            consentRepositoryData;
    @Autowired private ConsentPersonalViewRepository        consentRepositoryView;

    @Autowired private RabbitTemplate rabbitTemplate;

    @SuppressWarnings("unchecked")
    public ResponseConsent buildNewConsent(CreateConsent createConsent) {

        String consentId = "urn:" + consentIdUrnUse + ":" + UUID.randomUUID().toString();

        ConsentPersonalData          consentCreated;
        ResponseValidateConsentModel responseValidate;
        List<ResponseConsentData.PermissionsEnum> permissionsResponse   = List.of();
        List<ResponseErrorErrorsInner>            overallResponseErrors = new ArrayList<ResponseErrorErrorsInner>();

        /// STEP 01 - Consent Validations ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = validateBusinessEntityService.validateBusinessEntityInformation(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        responseValidate = validateLoggedUserService.validateLoggedUserInformation(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        responseValidate = validateExpirationDatetimeService.validateExpirationDateInfo(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        if (consentsValidateAlreadyExists) {
            responseValidate = validateConsentAlreadyExistsService.validateConsentAlreadyExists(createConsent, consentId, executeThrowImmediately);
            overallResponseErrors.addAll(responseValidate.getResponseErrorsList());
        }

        responseValidate = validateGroupsAndPermissionsService.validateGroupsAndPermissionsRequested(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        if (responseValidate.isErrorsListed() == false) {
            responseValidate = validatePermissionsRequestedService.validateRequestedPermissionsExists(createConsent, consentId, executeThrowImmediately);
            overallResponseErrors.addAll(responseValidate.getResponseErrorsList());
        }

        if (!overallResponseErrors.isEmpty()) {
            throw new ConsentResponseErrorException(new Gson().toJson(overallResponseErrors));
        }
        /// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        ///  STEP 02 - Insert New Consent +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = consentCreateService.insertNewConsent(createConsent, consentId, executeThrowImmediately);
        if (responseValidate.isErrorsListed()) {
            throw new ConsentResponseErrorException(new Gson().toJson(responseValidate.getResponseErrorsList()));
        } else {
            consentCreated = (ConsentPersonalData) responseValidate.getObjectData();
        }
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        /// STEP 03.a - Insert Permissions ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            /// (a) - Insert Permissions
            responseValidate = consentCreatePermissionsService.insertConsentPermissions(createConsent, consentId, false);
            if (responseValidate.isErrorsListed()) {
                overallResponseErrors.addAll(responseValidate.getResponseErrorsList());
            }
            ///  (b) - Verify errors occurences in Insert Permissions and execute Consent Cancel if necessary
            if (responseValidate.isErrorsListed()) {
                responseValidate = consentCancelService.cancelConsent(consentCreated, consentId, executeThrowImmediately);
                if (responseValidate.isErrorsListed()) {
                    overallResponseErrors.addAll(responseValidate.getResponseErrorsList());
                    throw new ConsentResponseErrorException(new Gson().toJson(overallResponseErrors));
                }
            } else {
                permissionsResponse = (List<ResponseConsentData.PermissionsEnum>) responseValidate.getObjectData();
            }
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        /// STEP 04 - Update Status 'AWAITING_AUTHORISED' in Consent ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = consentAwaitingAuthorizationService.updateConsentToAwatingAuthorization(consentCreated, consentId, executeThrowImmediately);
        if (responseValidate.isErrorsListed()) {
            throw new ConsentResponseErrorException(new Gson().toJson(responseValidate.getResponseErrorsList()));
        }
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // STEP 05 - Build ResponseConsentData ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        ResponseConsentData responseConsentData;
        LinksConsents       links;
        Meta                meta;

        try {
            ConsentPersonalModel consentAccept = consentRepositoryView.findById(consentId).get();

            responseConsentData = new ResponseConsentData(
                    consentAccept.getConsentId(),
                    consentAccept.getCreationDatetime(),
                    ResponseConsentData.StatusEnum.fromValue(consentAccept.getStatus()),
                    consentAccept.getStatusUpdateDatetime(),
                    permissionsResponse);

            if (consentAccept.getExpirationDateInfo().equals("INDETERMINADO")) {
                responseConsentData.expirationDateTime("PRAZO INDETERMINADO");
            } else {
                responseConsentData.expirationDateTime(consentAccept.getExpirationDatetime());
            }

            links = LinksConsents.builder().self(URI.create("https://api.banco.com.br/open-banking/api/v1/consents/")).build();
            meta  = Meta.builder().requestDateTime(
                        consentAccept.getCreationDatetime())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ConsentResponseErrorException(new Gson().toJson(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent: Error in build ResponseConsentData.")
                    .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                    .detail("Consent Id is: " + consentId)
                    .build()));
        }
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // STEP 06 - Post a message consent to Authorization in RabbitMQ ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        try {
            rabbitTemplate.convertAndSend(queueConsentAdmin, "", responseConsentData, new CorrelationData(consentId));
        } catch (AmqpException e) {
            log.error(e.getMessage());
            overallResponseErrors = new ArrayList<>();
            overallResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent: Error in build ResponseConsentData.")
                    .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                    .detail("An error ocurred in send a message fro start authorization process")
                    .build());
            responseValidate = consentCancelService.cancelConsent(consentCreated, consentId, executeThrowImmediately);
            if (responseValidate.isErrorsListed()) {
                overallResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent: Error in build ResponseConsentData.")
                        .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                        .detail("An error ocurred in cancel consent process")
                        .build());
            }
            throw new ConsentResponseErrorException(new Gson().toJson(overallResponseErrors));
        }

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        return ResponseConsent.builder().data(responseConsentData)
                .links(links)
                .meta(meta)
                .build();

    }

}
