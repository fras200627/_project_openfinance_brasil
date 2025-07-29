package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.entity.ConsentPersonalDataExpirationControl;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.model.RespponseExpirationDatetimeModel;
import com.ofb.consents.repository.data.ConsentPersonalExpiirationControlRepository;
import com.ofb.consents.repository.data.ConsentPersonalRepository;
import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.consents.service.validation.ValidateBusinessEntityService;
import com.ofb.consents.service.validation.ValidateExpirationDatetimeService;
import com.ofb.consents.service.validation.ValidateLoggedUserService;
import com.ofb.lib.amqp.model.MessageExtendsConsentModel;
import com.ofb.lib.amqp.model.MessageRevokeConsentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service @Slf4j
public class ConsentPostExtendsService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Value("${app.consents.validate.execute-throw-immediately}")
    private boolean executeThrowImmediately;

    @Autowired private ConsentPersonalRepository consentsRepository;
    @Autowired private ValidateBusinessEntityService validateBusinessEntityService;
    @Autowired private ValidateLoggedUserService validateLoggedUserService;
    @Autowired private ValidateExpirationDatetimeService validateExpirationDatetimeService;
    @Autowired private ConsentPersonalExpiirationControlRepository consentsExpirationControlRepository;
    @Autowired private ConsentGetService consentGetService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.audit.consents-extends.routing-key}")
    private String AUDIT_CONSENTS_EXTENDS_ROUTING_KEY;

    public ResponseConsentExtensions consentsPostConsentsConsentIdExtends(String consentId,
                                                                              String authorization,
                                                                              UUID xFapiInteractionId,
                                                                              String xFapiCustomerIpAddress,
                                                                              String xCustomerUserAgent,
                                                                              CreateConsentExtensions createConsentExtensions) {

        ConsentPersonalData consentRequested;
        List<ResponseErrorErrorsInner> listError = new ArrayList<>();

        try {
            consentRequested = consentsRepository.findById(consentId).get();
        } catch (NoSuchElementException e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent POST EXTENDS request error")
                    .code(ConsentResponseEnum.CodeEnum.CONSENT_NOT_FOUND.getValue())
                    .detail("The informed consentId does not exist")
                    .build());
            throw new ConsentBadRequestException(new Gson().toJson(listError));
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent POST EXTENDS request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        if (!consentRequested.getStatus().equals("AUTHORISED")) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent POST EXTENDS request error")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Consent cannot be extensions for ExpirationDateTime. Actual Status is [" + consentRequested.getStatus() + "].")
                    .build());
            throw new ConsentUnprocessedEntityException(new Gson().toJson(listError));
        }

        ResponseValidateConsentModel responseValidate;
        List<ResponseErrorErrorsInner> overallResponseErrors = new ArrayList<ResponseErrorErrorsInner>();


        /// Consent Validations ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        responseValidate = validateBusinessEntityService.validateBusinessEntityInformation(createConsentExtensions.getData().getBusinessEntity(), consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        responseValidate = validateLoggedUserService.validateLoggedUserInformation(createConsentExtensions.getData().getLoggedUser(), consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        // No caso de criação ou renovação de consentimentos com prazo indeterminado, a receptora não deve
        // enviar o atributo expirationDateTime. Para prazos determinados o campo deve ser enviado.
        responseValidate = validateExpirationDatetimeService.validateExpirationDateInfo(createConsentExtensions.getData().getExpirationDateTime(), consentId, executeThrowImmediately);
        overallResponseErrors.addAll(responseValidate.getResponseErrorsList());

        if (!overallResponseErrors.isEmpty()) {
            throw new ConsentUnprocessedEntityException(new Gson().toJson(overallResponseErrors));
        }

        RespponseExpirationDatetimeModel respponseExpirationDatetimeModel = (RespponseExpirationDatetimeModel)
                                                                            responseValidate.getObjectData();

        Timestamp timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));

        timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        ConsentPersonalDataExpirationControl consentExpirationControl = consentsExpirationControlRepository.saveAndFlush(ConsentPersonalDataExpirationControl.builder()
                .consentId(consentId)
                .requestDatetime(timestampThisOperation)
                .previusExpirationDatetime(consentRequested.getExpirationDatetime())
                .xFapiCustomerIpAddress(xFapiCustomerIpAddress)
                .xCustomerAgent(xCustomerUserAgent)
                .loggedUserIdentification(createConsentExtensions.getData().getLoggedUser().getDocument().getIdentification())
                .loggedUserDocumentRel(createConsentExtensions.getData().getLoggedUser().getDocument().getRel())
                .businessEntityIdentification(createConsentExtensions.getData().getBusinessEntity().getDocument().getIdentification())
                .businessEntityDocumentRel(createConsentExtensions.getData().getBusinessEntity().getDocument().getRel())
                .expirationDatetime(respponseExpirationDatetimeModel.getExpirationDateTimeStamp())
                .expirationDatetimeRequested(respponseExpirationDatetimeModel.getExpirationDateTimeRequested())
                .expirationDatetimeAdjusted(respponseExpirationDatetimeModel.getExpirationDateTimeAdjusted())
                .expirationInMonths(respponseExpirationDatetimeModel.getExpirationInMonths())
                .expirationDateInfo(respponseExpirationDatetimeModel.getExpirationDateInfo())
                .createAt(timestampThisOperation)
                .modifyAt(timestampThisOperation)
                .userCode("ConsentsServiceAPI")
                .build());

        consentRequested.setExpirationDatetime(respponseExpirationDatetimeModel.getExpirationDateTimeStamp());
        consentRequested.setExpirationDateInfo(respponseExpirationDatetimeModel.getExpirationDateInfo());
        consentRequested.setCreateAt(timestampThisOperation);
        consentRequested.setModifyAt(timestampThisOperation);
        consentRequested.setUserCode("ConsentsServiceAPI");
        consentsRepository.saveAndFlush(consentRequested);

        try {
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, AUDIT_CONSENTS_EXTENDS_ROUTING_KEY,
                    MessageExtendsConsentModel.builder()
                            .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                            .consentId(consentId)
                            .correlationId(consentId)
                            .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                            .ticket(httpServletRequest.getHeader("x-ticket-id"))
                            .objectData(new Gson().toJson(consentExpirationControl))
                            .build(),
                    new CorrelationData(consentId));
        } catch (Exception e) {
            log.error("The extends process audit message was not sent. x-ticke-id [" +
                    httpServletRequest.getHeader("x-ticket-id") + "], x-fapi-interaction-tid [" +
                    httpServletRequest.getHeader("x-fapi-interaction-id") + "]. " +
                    "Error [" + e.getMessage() + "]");
            throw new RuntimeException(e);
        }

        ResponseConsentRead responseConsentRead = consentGetService.consentsGetConsentsConsentId(consentId, authorization, xFapiInteractionId);

        List<ResponseConsentExtensionsData.PermissionsEnum> permissions = new ArrayList<>();
        for (ResponseConsentReadData.PermissionsEnum permission : responseConsentRead.getData().getPermissions().stream().distinct().toList()) {
            permissions.add(ResponseConsentExtensionsData.PermissionsEnum.fromValue(permission.getValue()));
        }

        ResponseConsentExtensionsData data = ResponseConsentExtensionsData.builder()
                .consentId(responseConsentRead.getData().getConsentId())
                .creationDateTime(responseConsentRead.getData().getCreationDateTime())
                .expirationDateTime(responseConsentRead.getData().getExpirationDateTime())
                .permissions(permissions)
                .status(ResponseConsentExtensionsData.StatusEnum.fromValue(responseConsentRead.getData().getStatus().getValue()))
                .statusUpdateDateTime(responseConsentRead.getData().getStatusUpdateDateTime())
                .build();

        LinksConsents links = LinksConsents.builder().self(URI.create("https://api.banco.com.br/open-banking/api/v1/resource")).build();
        Meta meta = Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build();

        ResponseConsentExtensions responseConsentReadData = ResponseConsentExtensions.builder()
                .data(data)
                .meta(meta)
                .links(links)
                .build();

        return responseConsentReadData;
    }

}
