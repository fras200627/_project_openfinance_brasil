package com.tican.audit.listener;

import com.tican.audit.api.server.model.TicketResponse;
import com.tican.audit.service.AuditService;
import com.tican.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditServicesListener {

    @Value("${amqp.audit_services.audit_queue}")
    private String AMQP_AUDIT_QUEUE;
    
    @Autowired private
    AuditService service;

    @RabbitListener(queues = "tican.audit.queue")
    public void receiveMessage(@Payload MessageAuditTemplate messageAuditTemplate) {
        service.saveMessageQueue(messageAuditTemplate);
    }

}
