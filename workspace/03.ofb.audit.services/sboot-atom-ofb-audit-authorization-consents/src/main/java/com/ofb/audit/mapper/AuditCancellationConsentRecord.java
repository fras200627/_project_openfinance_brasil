package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditCancellationConsentEntity;

public record AuditCancellationConsentRecord(
        String id,
        String createAt,
        String xTicketId,
        String xFapiInteractionId,
        String consentId,
        String consentRequestDate,
        String consentCancelDate,
        String reason,
        String payload
) {
    public AuditCancellationConsentRecord(AuditCancellationConsentEntity request) {
        this(String.valueOf(request.getId()),
                request.getCreateAt().toString(),
                request.getXTicketId(),
                request.getXFapiInteractionId(),
                request.getConsentId(),
                request.getConsentRequestDate().toString(),
                request.getConsentCancelDate().toString(),
                request.getReason(),
                request.getPayload()
        );
    }
}
