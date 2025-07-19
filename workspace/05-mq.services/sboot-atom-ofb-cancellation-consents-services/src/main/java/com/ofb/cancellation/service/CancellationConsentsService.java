package com.ofb.cancellation.service;

import com.google.gson.Gson;
import com.ofb.cancellation.entity.ConsentPersonalData;
import com.ofb.cancellation.repository.ConsentPersonalRepository;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import com.ofb.lib.amqp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ofb.lib.amqp.model.MessageCancellationConsentModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class CancellationConsentsService {

    @Autowired
    private ConsentPersonalRepository repository;

    @Autowired private
    MessageService messageService;

    public void save(MessageCancellationConsentModel cancellationConsent) {
        Timestamp timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));

        ///
        ConsentPersonalData consentCreated = repository.findById(cancellationConsent.getConsentId()).get();

        consentCreated.setConsentId(consentCreated.getConsentId());
        consentCreated.setConsentStatusId(86L);
        consentCreated.setStatus("REQUEST_CANCELLED");
        consentCreated.setStatusUpdateDatetime(timestampThisOperation);
        consentCreated.setAuthorisedEnd(timestampThisOperation);
        consentCreated.setCancelledBy("ConsentsServiceAPI");
        consentCreated.setCancelledReason(cancellationConsent.getReason());
        consentCreated.setCancelledAdditionalInfo("x-ticket-id [" + cancellationConsent.getTicket() + "] " +
                                                  "x-fapi-interaction-id [" + cancellationConsent.getXFapiInteraction() + "] " +
                                                  "mq-correlation-id [" + cancellationConsent.getCorrelationId() + "]");
        consentCreated.setModifyAt(timestampThisOperation);
        consentCreated.setUserCode("SystemOFBListener");

        consentCreated = repository.saveAndFlush(consentCreated);

        /// Post a message Audit in RabbitMQ
        MessageAuditTemplate messageAuditTemplate = new MessageAuditTemplate();
        messageAuditTemplate.setTicket(cancellationConsent.getTicket());
        messageAuditTemplate.setInteractionId(cancellationConsent.getXFapiInteraction());
        messageAuditTemplate.setRequestMethod("CANCEL");
        messageAuditTemplate.setRequestSource("CONSENTS");
        messageAuditTemplate.setRequestTime(timestampThisOperation.toString());
        messageAuditTemplate.setRequestUri("cancellation-consents-services");
        messageAuditTemplate.setRequestUserName("SystemOFBListener");
        messageAuditTemplate.setPayload(new Gson().toJson(consentCreated));

        messageService.sendMessageAuditTemplate(messageAuditTemplate);

    }

}
