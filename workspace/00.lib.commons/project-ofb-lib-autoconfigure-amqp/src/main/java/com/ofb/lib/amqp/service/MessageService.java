package com.ofb.lib.amqp.service;

import com.ofb.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class MessageService {

    @Autowired private
    RabbitTemplate rabbitTemplate;

    public void sendMessageAuditTemplate(HttpServletRequest request) {
        //Post a message Audit in RabbitMQ
        rabbitTemplate.convertAndSend("tican.audit.ex","",
                new MessageAuditTemplate(
                        request.getAttribute("x-ticket-id").toString(),
                        request.getAttribute("x-fapi-interaction-id").toString(),
                        request.getAttribute("requestDateTime").toString(),
                        request.getAttribute("requestURI").toString(),
                        request.getAttribute("requestSource").toString(),
                        request.getAttribute("requestMethod").toString(),
                        request.getAttribute("requestUserName").toString(),
                        request.getAttribute("requestPayload").toString())
        );
    }

    public void sendMessageAuditTemplate(HttpServletRequest request, Object headerXticket, Object headerXfapi) {
        String ticket = "";
        if (request.getHeader("x-ticket-id") != null) {
            ticket = request.getHeader("x-ticket-id").toString();
        } else {
            if (headerXticket != null) {
                ticket = headerXticket.toString();
            } else {
                ticket = "none";
            }
        }

        String fapi = "";
        if (request.getHeader("x-fapi-interaction-id") != null) {
            fapi = request.getHeader("x-fapi-interaction-id").toString();
        } else {
            if (headerXfapi != null) {
                fapi = headerXfapi.toString();
            } else {
                fapi = "none";
            }
        }

        //Post a message Audit in RabbitMQ
        rabbitTemplate.convertAndSend("tican.audit.ex","",
                new MessageAuditTemplate(
                        ticket,
                        fapi,
                        request.getAttribute("requestDateTime").toString(),
                        request.getAttribute("requestURI").toString(),
                        request.getAttribute("requestSource").toString(),
                        request.getAttribute("requestMethod").toString(),
                        request.getAttribute("requestUserName").toString(),
                        request.getAttribute("requestPayload").toString())
        );
    }

    public void sendMessageAuditTemplate(ServerHttpRequest serverHttpRequest) {
        //Post a message Audit in RabbitMQ
        rabbitTemplate.convertAndSend("tican.audit.ex","",
                new MessageAuditTemplate(
                        serverHttpRequest.getHeaders().get("x-ticket-id").toString(),
                        serverHttpRequest.getHeaders().get("x-fapi-interaction-id").toString(),
                        "requestDateTime",
                        serverHttpRequest.getURI().getPath(),
                        "source",
                        serverHttpRequest.getMethod().toString(),
                        "requestUserName",
                        "requestPayload")
        );
    }

    public void sendMessageAuditTemplate(MessageAuditTemplate messageAuditTemplate) {

        //Post a message Audit in RabbitMQ
        rabbitTemplate.convertAndSend("tican.audit.ex", "", messageAuditTemplate);
    }


}
