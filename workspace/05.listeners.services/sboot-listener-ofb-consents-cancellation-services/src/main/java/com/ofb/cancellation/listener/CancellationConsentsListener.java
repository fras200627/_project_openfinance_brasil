package com.ofb.cancellation.listener;

import com.ofb.cancellation.service.CancellationConsentsService;
import com.ofb.lib.amqp.model.MessageCancelConsentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CancellationConsentsListener {

    @Autowired
    private CancellationConsentsService service;

    @RabbitListener(queues = "#{ofbConsentsCancellation.name}")
    public void receiveMessage(@Payload MessageCancelConsentModel message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.save(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

}
