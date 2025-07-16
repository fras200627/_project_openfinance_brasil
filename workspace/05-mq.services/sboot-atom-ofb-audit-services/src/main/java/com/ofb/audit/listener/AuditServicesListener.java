package com.ofb.audit.listener;

import com.ofb.audit.model.AuditRecord;
import com.ofb.audit.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditServicesListener {

    @Value("${amqp.audit_services.queue}")
    private String AMQP_AUDIT_QUEUE;
    
    @Autowired private
    AuditService service;

    @RabbitListener(queues = "ofb.audit.queue")
    public void receiveMessage(@Payload AuditRecord trackingRecord) {
        log.info("Receive Audit Record: " + trackingRecord.ticket());
        service.save(trackingRecord);
    }

}
