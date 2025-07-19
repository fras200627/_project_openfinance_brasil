package com.ofb.audit.listener;

import com.ofb.audit.service.AuditService;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditListener {

//    @Value("${amqp.ofb.audit.http-requests.queue}")
//    private String AUDIT_HTTP_REQUESTS_QUEUE;

    @Autowired private AuditService service;

    @RabbitListener(queues = "#{ofbAuditHttpRequestsQueue.name}")
    public void receiveMessageAuditTemplate(@Payload MessageAuditTemplate message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.saveMessageAudit(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

//    @RabbitListener(queues = "ofb.audit.consents.cancellation.queue")
//    public void receiveMessageAuditCancellationConsentsTemplate(@Payload MessageCancellationConsentModel message) {
//        log.info("Message read in queue. processing.: " + message.getTicket());
//        service.saveMessageAuditCancellationConsent(message);
//        log.info("Message recorded successfully: " + message.getTicket());
//    }

//    @RabbitListener(queues = "ofb.audit.consents.authorization.queue")
//    public void receiveMessageAuditAuthorizationConsentsTemplate(@Payload MessageAuthorizeConsentModel message) {
//        log.info("Message read in queue. processing.: " + message.getTicket());
//        service.saveMessageAuditAuthorizationConsent(message);
//        log.info("Message recorded successfully: " + message.getTicket());
//    }

}
