package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.repository.data.ConsentPersonalRepository;
import com.ofb.consents.repository.views.ConsentPermissionsAuthorisedlViewRepository;
import com.ofb.consents.repository.views.ConsentPersonalViewRepository;
import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.lib.amqp.model.MessageRevokeConsentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service @Slf4j
public class ConsentRevokeService {

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
    private RabbitTemplate rabbitTemplate;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.audit.consents-revoke.routing-key}")
    private String AUDIT_CONSENTS_REVOKED_ROUTING_KEY;

    public void consentsDeleteConsentsConsentId(String consentId) {

        List<ResponseErrorErrorsInner> listError = new ArrayList<>();
        ConsentPersonalModel consentRequested;

        try {
            consentRequested = consentsRepositoryView.findById(consentId).get();
        } catch (NoSuchElementException e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent DELETE request error")
                    .code(ConsentResponseEnum.CodeEnum.CONSENT_NOT_FOUND.getValue())
                    .detail("The informed consentId does not exist")
                    .build());
            throw new ConsentBadRequestException(new Gson().toJson(listError));
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent DELETE request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        if (!consentRequested.getStatus().equals("AUTHORISED")) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent DELETE request error")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Consent cannot be REJECTED/REVOKED. Actual Status is [" + consentRequested.getStatus() + "].")
                    .build());
            throw new ConsentUnprocessedEntityException(new Gson().toJson(listError));
        }

        ConsentPersonalData consentCreated;
        try {
            consentCreated = consentPersonalRepository.findById(consentRequested.getConsentId()).get();
            consentCreated.setRejectedStartDatetime(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
            consentCreated.setConsentStatusId(84L);
            consentCreated.setStatus("REJECTED");
            consentCreated.setRejectedCode(ResponseConsentReadDataRejectionReason.CodeEnum.CUSTOMER_MANUALLY_REVOKED.getValue());
            consentCreated.setRejectedBy("USER");
            consentCreated.setRejectedReason("CUSTOMER_MANUALLY_REVOKED");
            consentCreated.setRejectedAdditionalInfo("x-ticket-id [" + httpServletRequest.getHeader("x-ticket-id") + "] " +
                                                     "x-fapi-interaction-id [" + httpServletRequest.getHeader("x-fapi-interaction-id") + "]");
            consentCreated.setModifyAt(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
            consentCreated.setUserCode("ConsentsServiceAPI");
            consentCreated.setRejectedEndDatetime(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
            consentCreated.setStatusUpdateDatetime(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
            consentCreated.setAccessTokenAuthorised("");
            consentPersonalRepository.saveAndFlush(consentCreated);
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent DELETE request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        try {
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, AUDIT_CONSENTS_REVOKED_ROUTING_KEY,
                    MessageRevokeConsentModel.builder()
                            .sendMessageDatetime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                            .consentId(consentId)
                            .correlationId(consentId)
                            .xFapiInteraction(httpServletRequest.getHeader("x-fapi-interaction-id"))
                            .ticket(httpServletRequest.getHeader("x-ticket-id"))
                            .reason("CUSTOMER_MANUALLY_REVOKED")
                            .objectData(new Gson().toJson(consentCreated))
                            .build(),
                    new CorrelationData(consentId));
        } catch (Exception e) {
            log.error("The revocation process audit message was not sent. x-ticke-id [" +
                    httpServletRequest.getHeader("x-ticket-id") + "], x-fapi-interaction-tid [" +
                    httpServletRequest.getHeader("x-fapi-interaction-id") + "]. " +
                    "Error [" + e.getMessage() + "]");
            throw new RuntimeException(e);
        }

    }

}
