package com.ofb.audit.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name= "Audit")
@Table(schema= "ofb", name= "ofb_audit")
//---------------------------------------------------
@Builder @AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= "id")
public class AuditEntity {
    
    @Id
    @Column(name= "ID")
    private Long id;

    @Column(name = "CREATEAT")
    private Timestamp createAt;

    @Column(name= "XTICKETID")
    private String xTicketId;

    @Column(name= "XFAPIINTERACTIONID")
    private String xFapiInteractionId;

    @Column(name="REQUESTTIME")
    private String requestTime;

    @Column(name= "REQUESTURI")
    private String requestUri;

    @Column(name= "REQUESTMETHOD")
    private String requestMethod;

    @Column(name= "REQUESTUSERNAME")
    private String requestUserName;

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

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUserName() {
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName) {
        this.requestUserName = requestUserName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}

