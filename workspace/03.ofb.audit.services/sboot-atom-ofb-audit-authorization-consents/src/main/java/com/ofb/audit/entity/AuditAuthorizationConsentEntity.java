package com.ofb.audit.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name= "AuditAuthorizationConsent")
@Table(schema= "OFB", name= "OFB_AUDIT_AUTHORIZATION_CONSENTS")
//---------------------------------------------------
@Builder @AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= "id")
public class AuditAuthorizationConsentEntity {

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

    @Column(name= "CONSENTAPPROVEDDATE")
    private Timestamp consentApprovedDate;

    @Column(name= "PAYLOAD")
    private String payload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getxTicketId() {
        return xTicketId;
    }

    public void setxTicketId(String xTicketId) {
        this.xTicketId = xTicketId;
    }

    public String getxFapiInteractionId() {
        return xFapiInteractionId;
    }

    public void setxFapiInteractionId(String xFapiInteractionId) {
        this.xFapiInteractionId = xFapiInteractionId;
    }

    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public Timestamp getConsentRequestDate() {
        return consentRequestDate;
    }

    public void setConsentRequestDate(Timestamp consentRequestDate) {
        this.consentRequestDate = consentRequestDate;
    }

    public Timestamp getConsentApprovedDate() {
        return consentApprovedDate;
    }

    public void setConsentApprovedDate(Timestamp consentApprovedDate) {
        this.consentApprovedDate = consentApprovedDate;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}

