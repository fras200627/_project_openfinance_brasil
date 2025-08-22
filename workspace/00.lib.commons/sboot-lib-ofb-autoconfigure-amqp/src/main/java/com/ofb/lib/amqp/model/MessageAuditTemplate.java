package com.ofb.lib.amqp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data @Builder @RequiredArgsConstructor @AllArgsConstructor
public class MessageAuditTemplate implements Serializable {
    private String ticket;
    private String interactionId;
    private String requestTime;
    private String requestUri;
    private String requestSource;
    private String requestMethod;
    private String requestUserName;
    private String payload;
}
