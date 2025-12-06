package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditRevokedConsentEntity;

public record AuditRevokeConsentRecord(
        String id,
        String createAt,
        String xTicketId,
        String xFapiInteractionId,
        String consentId,
        String consentRequestDate,
        String consentRevokedDate,
        String reason,
        String payload
) {
    public AuditRevokeConsentRecord(AuditRevokedConsentEntity request) {
        this(String.valueOf(request.getId()),
                request.getCreateAt().toString(),
                request.getXTicketId(),
                request.getXFapiInteractionId(),
                request.getConsentId(),
                request.getConsentRequestDate().toString(),
                request.getConsentRevokedDate().toString(),
                request.getReason(),
                request.getPayload()
        );
    }
}
