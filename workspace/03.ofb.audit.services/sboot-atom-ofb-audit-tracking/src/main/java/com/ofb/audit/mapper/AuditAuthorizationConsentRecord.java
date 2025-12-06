package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditAuthorizationConsentEntity;

public record AuditAuthorizationConsentRecord(
        String id,
        String createAt,
        String xTicketId,
        String xFapiInteractionId,
        String consentId,
        String consentRequestDate,
        String consentApprovedDate,
        String payload
) {
    public AuditAuthorizationConsentRecord(AuditAuthorizationConsentEntity request) {
        this(String.valueOf(request.getId()),
                request.getCreateAt().toString(),
                request.getXTicketId(),
                request.getXFapiInteractionId(),
                request.getConsentId(),
                request.getConsentRequestDate().toString(),
                request.getConsentApprovedDate().toString(),
                request.getPayload()
        );
    }
}
