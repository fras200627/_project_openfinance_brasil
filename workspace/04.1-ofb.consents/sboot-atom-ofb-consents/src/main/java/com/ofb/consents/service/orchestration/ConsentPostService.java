package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.repository.data.*;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.consents.service.persistence.ConsentCancelService;
import com.ofb.consents.service.persistence.ConsentCreateService;
import com.ofb.consents.service.persistence.ConsentCreatePermissionsService;
import com.ofb.consents.service.persistence.ConsentUpdateService;
import com.ofb.consents.service.validation.*;
import com.ofb.lib.amqp.model.MessageAuthorisedConsentModel;
import com.ofb.lib.amqp.model.MessageCancelConsentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class ConsentPostService {

    @Autowired private HttpServletRequest httpServletRequest;

    @Value("${app.consents.validate.check-if-consent-already-exists}")
    private boolean consentsValidateAlreadyExists;

    @Value("${app.consents.validate.execute-throw-immediately}")
    private boolean executeThrowImmediately;

    @Value("${app.consents.consent-id-urn-use}")
    private String consentIdUrnUse;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.consents.authorization.routing-key}")
    private String CONSENTS_AUTHORIZATION_ROUTING_KEY;

    @Value("${amqp.ofb.consents.cancellation.routing-key}")
    private String CONSENTS_CANCELLATION_ROUTING_KEY;

    @Autowired private ValidateBusinessEntityService        validateBusinessEntityService;
    @Autowired private ValidateLoggedUserService            validateLoggedUserService;
    @Autowired private ValidateConsentAlreadyExistsService  validateConsentAlreadyExistsService;
    @Autowired private ValidateExpirationDatetimeService    validateExpirationDatetimeService;
    @Autowired private ValidateGroupsAndPermissionsService  validateGroupsAndPermissionsService;
    @Autowired private ValidatePermissionsRequestedService  validatePermissionsRequestedService;
    @Autowired private ConsentCreateService                 consentCreateService;
    @Autowired private ConsentCancelService                 consentCancelService;
    @Autowired private ConsentCreatePermissionsService      consentCreatePermissionsService;
    @Autowired private ConsentUpdateService                 consentUpdateService;
    @Autowired private ConsentPersonalRepository            consentRepositoryData;
    @Autowired private ConsentPersonalViewRepository        consentRepositoryView;

    @Autowired private RabbitTemplate rabbitTemplate;

    /**
     *
     * @param createConsent
     * @return
     *
     * https://openfinancebrasil.atlassian.net/wiki/spaces/OF/pages/219480491/Orienta+es+-+DC+Consentimento
     */
    public ResponseConsent consentsPostConsents(CreateConsent createConsent) {

        String consentId = "urn:" + consentIdUrnUse + ":" + UUID.randomUUID().toString();

        ConsentPersonalData                       consentCreated;
        ResponseValidateConsentModel              responseValidate;
        List<ResponseConsentData.PermissionsEnum> permissionsResponse   = List.of();
        List<ResponseErrorErrorsInner>            overallResponseErrors = new ArrayList<ResponseErrorErrorsInner>();

        /// STEP 01 - Consent Validations ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = validateBusinessEntityService.validateBusinessEntityInformation(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        responseValidate = validateLoggedUserService.validateLoggedUserInformation(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        // No caso de criação ou renovação de consentimentos com prazo indeterminado, a receptora não deve
        // enviar o atributo expirationDateTime. Para prazos determinados o campo deve ser enviado.
        responseValidate = validateExpirationDatetimeService.validateExpirationDateInfo(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        if (consentsValidateAlreadyExists) {
            responseValidate = validateConsentAlreadyExistsService.validateConsentAlreadyExists(createConsent, consentId, executeThrowImmediately);
            overallResponseErrors.addAll(responseValidate.getResponseErrorsList());
        }

        responseValidate = validateGroupsAndPermissionsService.validateGroupsAndPermissionsRequested(createConsent, consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        if (responseValidate.isErrorsListed() == true) {
            // Caso a instiuição receptora envie permissões não existentes nos agrupamentos especificados na tabela,
            // a transmissora deve rejeitar o pedido da receptora dando retorno HTTP Status Code 400.
            throw new ConsentBadRequestException(new Gson().toJson(overallResponseErrors));
        } else {
            responseValidate = validatePermissionsRequestedService.validateRequestedPermissionsExists(createConsent, consentId, executeThrowImmediately);
            overallResponseErrors.addAll(responseValidate.getResponseErrorsList());
        }

        if (!overallResponseErrors.isEmpty()) {
            throw new ConsentUnprocessedEntityException(new Gson().toJson(overallResponseErrors));
        }

        ///  STEP 02 - Insert New Consent +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = consentCreateService.insertNewConsent(createConsent, consentId, executeThrowImmediately);
        if (responseValidate.isErrorsListed()) {
            throw new ConsentUnprocessedEntityException(new Gson().toJson(responseValidate.getResponseErrorsList()));
        } else {
            consentCreated = (ConsentPersonalData) responseValidate.getObjectData();
        }

        /// STEP 03 - Insert Consent Permissions ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = consentCreatePermissionsService.insertConsentPermissions(createConsent, consentId, executeThrowImmediately);
        if (responseValidate.isErrorsListed()) {
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, CONSENTS_CANCELLATION_ROUTING_KEY,
                    MessageCancelConsentModel.builder()
                    .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                    .consentId(consentId)
                    .correlationId(consentId)
                    .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                    .ticket(httpServletRequest.getHeader("x-ticket-id"))
                    .reason("Error in create consent permissions")
                    .objectData(new Gson().toJson(consentCreated))
                    .objectException(new Gson().toJson(responseValidate.getObjectException()))
                    .build(),
                    new CorrelationData(consentId));

            throw new ConsentUnprocessedEntityException(new Gson().toJson(responseValidate.getResponseErrorsList()));
        } else {
            permissionsResponse = (List<ResponseConsentData.PermissionsEnum>) responseValidate.getObjectData();
        }

        // STEP 04 - Build ResponseConsentData ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        ResponseConsentData responseConsentData;
        LinksConsents       links;
        Meta                meta;
        try {
            responseConsentData = new ResponseConsentData(
                    consentCreated.getConsentId(),
                    consentCreated.getCreateAt().toString(),
                    ResponseConsentData.StatusEnum.fromValue("AWAITING_AUTHORISATION"),
                    consentCreated.getStatusUpdateDatetime().toString(),
                    permissionsResponse);

            if (consentCreated.getExpirationDateInfo().equals("INDETERMINADO")) {
                responseConsentData.expirationDateTime("PRAZO INDETERMINADO");
            } else {
                responseConsentData.expirationDateTime(consentCreated.getExpirationDatetime().toString());
            }

            links = LinksConsents.builder().self(URI.create("https://api.banco.com.br/open-banking/api/v1/consents/" + consentId)).build();
            meta  = Meta.builder().requestDateTime(consentCreated.getCreateAt().toString()).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, CONSENTS_CANCELLATION_ROUTING_KEY,
                    MessageCancelConsentModel.builder()
                            .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z")
                            .consentId(consentId)
                            .correlationId(consentId)
                            .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                            .ticket(httpServletRequest.getHeader("x-ticket-id"))
                            .reason("Error in build responseConsentData")
                            .objectData(new Gson().toJson(consentCreated))
                            .objectException(e.getMessage())
                            .build(),
                    new CorrelationData(consentId));
            throw new ConsentInternalErrorException(new Gson().toJson(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("Error builder ResponseConsentData")
                    .build()));
        }

        // STEP 05 - Update Status Consent to 'AWAITING_AUTHORISED' and send message consent to Authorization in RabbitMQ +++++++++++
        responseValidate = consentUpdateService.updateConsentToAwaitingAuthorization(consentCreated, consentId, executeThrowImmediately);
        if (responseValidate.isErrorsListed()) {
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, CONSENTS_CANCELLATION_ROUTING_KEY,
                    MessageCancelConsentModel.builder()
                            .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z")
                            .consentId(consentId)
                            .correlationId(consentId)
                            .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                            .ticket(httpServletRequest.getHeader("x-ticket-id"))
                            .reason("Error in update status consent")
                            .objectData(new Gson().toJson(consentCreated))
                            .objectException(new Gson().toJson(responseValidate.getObjectException()))
                            .build(),
                    new CorrelationData(consentId));
            throw new ConsentUnprocessedEntityException(new Gson().toJson(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent update error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("Error updating consent status for AWAITING_AUTHORISATION")
                    .build()));
        }

        try {
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, CONSENTS_AUTHORIZATION_ROUTING_KEY,
                    MessageAuthorisedConsentModel.builder()
                            .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z")
                            .consentId(consentId)
                            .correlationId(consentId)
                            .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                            .ticket(httpServletRequest.getHeader("x-ticket-id"))
                            .bearerToken(httpServletRequest.getHeader("Authorization").replace("Bearer ", ""))
                            .objectData(new Gson().toJson(consentCreated))
                            .build(),
                    new CorrelationData(consentId));
        } catch (Exception e) {
            log.error(e.getMessage());
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, CONSENTS_CANCELLATION_ROUTING_KEY,
                    MessageCancelConsentModel.builder()
                            .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z")
                            .consentId(consentId)
                            .correlationId(consentId)
                            .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                            .ticket(httpServletRequest.getHeader("x-ticket-id"))
                            .reason("Error sending authorization process execution message")
                            .objectData(new Gson().toJson(consentCreated))
                            .objectException(e.getMessage())
                            .build(),
                    new CorrelationData(consentId));
            throw new ConsentInternalErrorException(new Gson().toJson(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent Authorization")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("Error sending authorization process execution message")
                    .build()));
        }

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        return ResponseConsent.builder().data(responseConsentData)
                .links(links)
                .meta(meta)
                .build();
    }

}
