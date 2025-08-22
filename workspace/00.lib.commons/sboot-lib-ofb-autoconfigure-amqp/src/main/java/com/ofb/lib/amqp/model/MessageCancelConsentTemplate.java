package com.ofb.lib.amqp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data @Builder @AllArgsConstructor @RequiredArgsConstructor
public class MessageCancelConsentTemplate implements Serializable {
    private String sendMessageDatetime;
    private String correlationId;
    private String xFapiInteraction;
    private String ticket;
    private String consentId;
    private String reason;
    private String objectRequest;
    private String objectData;
    private String objectException;
}
