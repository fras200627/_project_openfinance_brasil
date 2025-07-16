package com.ofb.audit.entity;

import com.ofb.audit.model.AuditRecord;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

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

    @Column(name="REQUESTTIME", nullable = true, columnDefinition="REQUEST DATE-TIME")
    private String requestTime;

    @Column(name= "REQUESTURI", length = 400, nullable = false, columnDefinition="REQUEST URI")
    private String requestUri;

    @Column(name= "REQUESTMETHOD", length = 100, nullable = false, columnDefinition="REQUEST METHOD")
    private String requestMethod;

    @Column(name= "REQUESTUSERNAME", length = 100, nullable = false, columnDefinition="REQUEST USER NAME")
    private String requestUserName;

    @Column(name= "PAYLOAD", length = 4000, nullable = false, columnDefinition="PAYLOAD")
    private String payload;

    public AuditEntity(AuditRecord request) {
        this.id              = (long) (Math.random() * 999999999 + 1);
        this.createAt        = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        this.xTicketId          = "not informed";
        this.xFapiInteractionId = "not informed";
        this.requestTime     = request.request_time() == null ? "not informed" : request.request_time();
        this.requestUri      = request.request_uri() == null ? "not informed" : request.request_uri();
        this.requestMethod   = request.request_method() == null ? "not informed" : request.request_method();
        this.requestUserName = request.request_user_name() == null ? "not informed" : request.request_user_name();
        this.payload         = request.payload() == null ? "not infomrmed" : request.payload();
    }
}

