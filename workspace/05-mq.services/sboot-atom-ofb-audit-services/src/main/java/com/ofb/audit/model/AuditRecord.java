package com.ofb.audit.model;

import com.ofb.audit.entity.AuditEntity;

public record AuditRecord(
        String ticket,
        String request_time,
        String request_uri,
        String request_method,
        String request_user_name,
        String payload
) {
    public AuditRecord(AuditEntity request) {
        this(String.valueOf(request.getId()),
                request.getRequestTime(),
                request.getRequestUri(),
                request.getRequestMethod(),
                request.getRequestUserName(),
                request.getPayload()
        );
    }
}
