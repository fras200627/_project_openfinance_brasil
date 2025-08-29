package com.ofb.consents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "ConsentPersonalExpirationControlView")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL") //Materialized View
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public class ConsentPersonalExpirationControlModel {

    @Id
    @Column(name = "EXPIRATIONCONTROLID")
    private String id;

    @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "CREATIONDATETIME")
    private String creationDatetime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REQUESTDATETIME")
    private String requestDatetime;

    @Column(name = "PREVIUSEXPIRATIONDATETIME")
    private String previusExpirationDatetime;

    @Column(name = "XFAPICUSTOMERIPADDRESS")
    private String xFapiCustomerIdAddress;

    @Column(name = "XCUSTOMERUSERAGENT")
    private String xCustomerUserAgent;

    @Column(name = "EXPIRATIONDATETIME")
    private String expirationDatetime;

    @Column(name = "EXPIRATIONINMONTHS")
    private Long expirationInMonths;

    @Column(name = "EXPIRATIONDATEINFO")
    private String expirationDateInfo;

    @Column(name = "LOGGEDUSERIDENTIFICATION")
    private String loggedUserIdentification;

    @Column(name = "LOGGEDUSERDOCUMENTREL")
    private String loggedUserDocumentRel;

    @Column(name = "BUSINESSENTITYIDENTIFICATION")
    private String businessEntityIdentification;

    @Column(name = "BUSINESSENTITYDOCUMENTREL")
    private String businessEntityDocumentRel;

    @Column(name = "CREATEAT")
    private Timestamp createAt;

}
