package com.ofb.authorization.listener;

import com.ofb.authorization.service.AuthorizationConsentsService;
import com.ofb.lib.amqp.model.MessageAuthorisedConsentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizationConsentsListener {

    @Autowired
    private AuthorizationConsentsService service;

    @RabbitListener(queues = "#{ofbConsentsAuthorization.name}")
    public void receiveMessage(@Payload MessageAuthorisedConsentModel message) {
        log.info("Message read in queue. processing.: " + message.getTicket());
        service.save(message);
        log.info("Message recorded successfully: " + message.getTicket());
    }

}
