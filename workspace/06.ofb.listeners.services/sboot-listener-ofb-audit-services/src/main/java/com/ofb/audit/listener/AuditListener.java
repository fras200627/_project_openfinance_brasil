package com.ofb.audit.listener;

import com.ofb.audit.service.AuditService;
import com.ofb.lib.amqp.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditListener {

    @Autowired private AuditService service;

    @RabbitListener(queues = "#{ofbAuditHttpRequestsQueue.name}")
    public void receiveMessageAuditTemplate(@Payload MessageAuditTemplate message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.saveMessageAudit(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

    @RabbitListener(queues = "#{ofbAuditConsentsCancellation.name}")
    public void receiveMessageAuditCancellationConsentsTemplate(@Payload MessageCancelConsentTemplate message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.saveMessageAuditCancellationConsent(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

    @RabbitListener(queues = "#{ofbAuditConsentsAuthorization.name}")
    public void receiveMessageAuditAuthorizationConsentsTemplate(@Payload MessageAuthorisedConsentTemplate message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.saveMessageAuditAuthorizationConsent(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

    @RabbitListener(queues = "#{ofbAuditConsentsRevoked.name}")
    public void receiveMessageAuditRevokeConsentsTemplate(@Payload MessageRevokeConsentTemplate message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.saveMessageAuditRevokedConsent(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

    @RabbitListener(queues = "#{ofbAuditConsentsExtends.name}")
    public void receiveMessageAuditExtendsConsentsTemplate(@Payload MessageExtendsConsentTemplate message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.saveMessageAuditExtendsConsent(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

}
