package com.ofb.audit.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name= "Audit")
@Table(schema= "OFB", name= "OFB_AUDIT")
//---------------------------------------------------
@Data @Builder @AllArgsConstructor
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

//    public AuditEntity(AuditRecord request) {
//        this.id              = (long) (Math.random() * 999999999 + 1);
//        this.createAt        = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
//        this.xTicketId          = "not informed";
//        this.xFapiInteractionId = "not informed";
//        this.requestTime     = request.request_time() == null ? "not informed" : request.request_time();
//        this.requestUri      = request.request_uri() == null ? "not informed" : request.request_uri();
//        this.requestMethod   = request.request_method() == null ? "not informed" : request.request_method();
//        this.requestUserName = request.request_user_name() == null ? "not informed" : request.request_user_name();
//        this.payload         = request.payload() == null ? "not infomrmed" : request.payload();
//    }
//
//    public AuditEntity(MessageAuditTemplate request) {
//        this.id              = (long) (Math.random() * 999999999 + 1);
//        this.createAt        = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
//        this.xTicketId          = request.getTicket() == null ? "not informed" : request.getTicket();
//        this.xFapiInteractionId = request.getInteractionId() == null ? "not informed" : request.getInteractionId();
//        this.requestTime     = request.getRequestTime() == null ? "not informed" : request.getRequestTime();
//        this.requestUri      = request.getRequestUri() == null ? "not informed" : request.getRequestUri();
//        this.requestMethod   = request.getRequestMethod() == null ? "not informed" : request.getRequestMethod();
//        this.requestUserName = request.getRequestUserName() == null ? "not informed" : request.getRequestUserName();
//        this.payload         = request.getPayload() == null ? "not infomrmed" : request.getPayload();
//    }

}

