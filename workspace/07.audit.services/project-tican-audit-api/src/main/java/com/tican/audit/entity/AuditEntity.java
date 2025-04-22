package com.tican.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name= "Audit")
@Table(schema= "TICAN2", name= "TB_AUDIT_2")
@Data @Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuditEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_TICKET")
    @SequenceGenerator(schema= "TICAN2", name= "SEQ_TICKET", allocationSize = 1, sequenceName = "SEQ_AUDIT")
    @Column(name= "ID")
    private Long id;

    @Column(name= "TICKET", length = 9, nullable = false, columnDefinition="REQUEST X_TICKET_ID")
    private String ticket;

    @Column(name="INTERACTION_ID", length = 36, nullable = false, columnDefinition="REQUEST X_FAPI_INTERACTION_ID")
    private String interactionId;

    @Column(name="REQUEST_TIME", nullable = false, columnDefinition="REQUEST DATE-TIME")
    private String requestTime;

    @Column(name= "REQUEST_SOURCE", length = 100, nullable = false, columnDefinition="COMPONENT SOURCE")
    private String requestSource;

    @Column(name= "REQUEST_URI", length = 400, nullable = false, columnDefinition="REQUEST URI")
    private String requestUri;

    @Column(name= "REQUEST_METHOD", length = 100, nullable = false, columnDefinition="REQUEST METHOD")
    private String requestMethod;

    @Column(name= "REQUEST_USER_NAME", length = 100, nullable = false, columnDefinition="REQUEST USER NAME")
    private String requestUserName;

    @Column(name= "PAYLOAD", length = 4000, nullable = false, columnDefinition="PAYLOAD")
    private String payload;
    
}

