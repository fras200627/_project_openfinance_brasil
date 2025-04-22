package com.tican.lib.amqp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageAuditTemplate {
    private String ticket;
    private String interactionId;
    private String requestTime;
    private String requestUri;
    private String requestSource;
    private String requestMethod;
    private String requestUserName;
    private String payload;
}
