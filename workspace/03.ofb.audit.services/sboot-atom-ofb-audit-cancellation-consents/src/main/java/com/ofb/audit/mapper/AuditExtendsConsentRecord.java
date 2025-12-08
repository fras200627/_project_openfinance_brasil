package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditExtendsConsentEntity;

public record AuditExtendsConsentRecord(
        String id,
        String createAt,
        String xTicketId,
        String xFapiInteractionId,
        String consentId,
        String consentRequestDate,
        String consentExtendsDate,
        String payload
) {
    public AuditExtendsConsentRecord(AuditExtendsConsentEntity request) {
        this(String.valueOf(request.getId()),
                request.getCreateAt().toString(),
                request.getXTicketId(),
                request.getXFapiInteractionId(),
                request.getConsentId(),
                request.getConsentRequestDate().toString(),
                request.getConsentExtendsDate().toString(),
                request.getPayload()
        );
    }
}
