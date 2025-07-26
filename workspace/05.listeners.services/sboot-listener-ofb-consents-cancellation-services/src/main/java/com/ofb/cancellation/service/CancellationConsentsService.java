package com.ofb.cancellation.service;

import com.ofb.cancellation.entity.ConsentPersonalData;
import com.ofb.cancellation.repository.ConsentPersonalRepository;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ofb.lib.amqp.model.MessageCancelConsentModel;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service
public class CancellationConsentsService {

    @Autowired
    private ConsentPersonalRepository repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.audit.consents-cancellation.routing-key}")
    private String AUDIT_CONSENTS_CANCELLATION_ROUTING_KEY;

    public void save(MessageCancelConsentModel cancellationConsent) {
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

        repository.saveAndFlush(consentCreated);

        rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, AUDIT_CONSENTS_CANCELLATION_ROUTING_KEY,
                cancellationConsent,
                new CorrelationData(cancellationConsent.getConsentId()));
    }

}
