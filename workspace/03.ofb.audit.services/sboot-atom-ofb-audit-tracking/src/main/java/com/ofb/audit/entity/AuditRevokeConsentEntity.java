package com.ofb.audit.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name= "AuditRevokeConsent")
@Table(schema= "OFB", name= "OFB_AUDIT_REVOKED_CONSENTS")
//---------------------------------------------------
@Data @Builder @AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= "id")
public class AuditRevokeConsentEntity {

    @Id
    @Column(name= "ID")
    private Long id;

    @Column(name = "CREATEAT")
    private Timestamp createAt;

    @Column(name= "XTICKETID")
    private String xTicketId;

    @Column(name= "XFAPIINTERACTIONID")
    private String xFapiInteractionId;

    @Column(name="CONSENTID")
    private String consentId;

    @Column(name= "CONSENTREQUESTDATE")
    private Timestamp consentRequestDate;

    @Column(name= "CONSENTREVOKEDDATE")
    private Timestamp consentRevokedDate;

    @Column(name= "REASON")
    private String reason;

    @Column(name= "PAYLOAD")
    private String payload;
//
//    public AuditRevokeConsentEntity(MessageRevokeConsentTemplate request) {
//        this.id                 = (long) (Math.random() * 999999999 + 1);
//        this.createAt            = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
//        this.xTicketId          = request.getTicket() == null ? "not informed" : request.getTicket();
//        this.xFapiInteractionId = request.getXFapiInteraction() == null ? "not informed" : request.getXFapiInteraction();
//        this.consentId          = request.getConsentId() == null ? "not informed" : request.getConsentId();
//        this.consentRequestDate = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
//        this.consentRevokedDate  = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
//        this.reason             = request.getReason() == null ? "not informed" : request.getReason();
//        this.payload            = request.getObjectData() == null ? "not informed" : request.getObjectData();
//    }

}

