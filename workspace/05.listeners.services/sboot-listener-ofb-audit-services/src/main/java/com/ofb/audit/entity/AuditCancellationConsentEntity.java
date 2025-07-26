package com.ofb.audit.entity;

import com.ofb.lib.amqp.model.MessageCancelConsentModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity(name= "AuditCancellationConsent")
@Table(schema= "OFB", name= "OFB_AUDIT_CANCELLATION_CONSENTS")
//---------------------------------------------------
@Data @Builder @AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= "id")
public class AuditCancellationConsentEntity {

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

    @Column(name= "CONSENTCANCELDATE")
    private Timestamp consentCancelDate;

    @Column(name= "REASON")
    private String reason;

    @Column(name= "PAYLOAD")
    private String payload;

    public AuditCancellationConsentEntity(MessageCancelConsentModel request) {
        this.id                 = (long) (Math.random() * 999999999 + 1);
        this.createAt            = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        this.xTicketId          = request.getTicket() == null ? "not informed" : request.getTicket();
        this.xFapiInteractionId = request.getXFapiInteraction() == null ? "not informed" : request.getXFapiInteraction();
        this.consentId          = request.getConsentId() == null ? "not informed" : request.getConsentId();
        this.consentRequestDate = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        this.consentCancelDate  = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        this.reason             = request.getReason() == null ? "not informed" : request.getReason();
        this.payload            = request.getObjectData() == null ? "not infomrmed" : request.getObjectData();
    }

}

