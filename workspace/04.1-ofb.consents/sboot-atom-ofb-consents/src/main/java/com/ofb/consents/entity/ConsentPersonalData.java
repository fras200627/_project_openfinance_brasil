package com.ofb.consents.entity;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "ConsentPersonalData")
@Table(schema = "OFB", name = "CONSENTS_PERSONAL_DATA")
@Data @Builder @ToString @AllArgsConstructor @RequiredArgsConstructor
public class ConsentPersonalData {

    @Id @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "CREATIONDATETIME")
    private Timestamp creationDatetime;

    @Column(name = "CONSENTSTATUSID")
    private Long consentStatusId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ACCESSTOKENAUTHORISED")
    private String accessTokenAuthorised;

    @Column(name = "STATUSUPDATEDATETIME")
    private Timestamp statusUpdateDatetime;

    @Column(name = "EXPIRATIONDATETIME")
    private Timestamp expirationDatetime;

    @Column(name = "EXPIRATIONDATETIMEREQUESTED")
    private Timestamp expirationDatetimeRequested;

    @Column(name = "EXPIRATIONDATETIMEADJUSTED")
    private Timestamp expirationDatetimeAdjusted;

    @Column(name = "EXPIRATIONINMONTHS")
    private Long expirationInMonths;

    @Column(name = "EXPIRATIONDATEINFO")
    private String expirationDateInfo;

    @Column(name = "PERSONALID")
    private String personalId;

    @Column(name = "LOGGEDUSERIDENTIFICATION")
    private String loggedUserIdentification;

    @Column(name = "LOGGEDUSERDOCUMENTREL")
    private String loggedUserDocumentRel;

    @Column(name = "BUSINESSENTITYIDENTIFICATION")
    private String businessEntityIdentification;

    @Column(name = "BUSINESSENTITYDOCUMENTREL")
    private String businessEntityDocumentRel;

    @Column(name = "AWAITINGAUTHBY")
    private String awaitingAuthBy;

    @Column(name = "AWAITINGAUTHSTART")
    private Timestamp awaitingAuthStart;

    @Column(name = "AWAITINGAUTHEND")
    private Timestamp awaitingAuthEnd;

    @Column(name = "AWAITINGAUTHADDITIONALINFO")
    private String awaitingAuthAdditionalInfo;

    @Column(name = "AUTHORISEDBY")
    private String authorisedBy;

    @Column(name = "AUTHORISEDSTART")
    private Timestamp authorisedStart;

    @Column(name = "AUTHORISEDEND")
    private Timestamp authorisedEnd;

    @Column(name = "AUTHORISEDADDITIONALINFO")
    private String authorisedAdditionalInfo;

    @Column(name = "REJECTEDBY")
    private String rejectedBy;

    @Column(name = "REJECTEDCODE")
    private String rejectedCode;

    @Column(name = "REJECTEDREASON")
    private String rejectedReason;

    @Column(name = "REJECTEDADDITIONALINFO")
    private String rejectedAdditionalInfo;

    @Column(name = "REJECTEDSTARTDATETIME")
    private Timestamp rejectedStartDatetime;

    @Column(name = "REJECTEDENDDATETIME")
    private Timestamp rejectedEndDatetime;

    @Column(name = "CANCELLEDBY")
    private String cancelledBy;

    @Column(name = "CANCELLEDREASON")
    private String cancelledReason;

    @Column(name = "CANCELLEDADDITIONALINFO")
    private String cancelledAdditionalInfo;

    @Column(name = "CREATE_AT")
    private Timestamp createAt;

    @Column(name = "MODIFY_AT")
    private Timestamp modifyAt;

    @Column(name = "USER_CODE")
    private String userCode;

}
