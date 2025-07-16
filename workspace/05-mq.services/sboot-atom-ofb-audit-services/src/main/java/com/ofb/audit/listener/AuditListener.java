package com.ofb.audit.listener;

import com.ofb.audit.model.AuditRecord;
import com.ofb.audit.service.AuditService;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditListener {

    @Value("${amqp.audit_services.queue}")
    private String AMQP_AUDIT_QUEUE;

    @Autowired
    private AuditService service;

    @RabbitListener(queues = "ofb.audit.queue")
    public void receiveMessage(@Payload MessageAuditTemplate messageAuditTemplate) {
        log.info("Message read in queue. processing.: " + messageAuditTemplate.getTicket());
        service.save(messageAuditTemplate);
        log.info("Message recorded successfully: " + messageAuditTemplate.getTicket());
    }

}
