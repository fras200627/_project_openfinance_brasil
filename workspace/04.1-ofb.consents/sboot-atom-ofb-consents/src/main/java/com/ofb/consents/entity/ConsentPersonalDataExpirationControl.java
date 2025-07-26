package com.ofb.consents.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "ConsentPersonalDataExpirationControl")
@Table(schema = "OFB", name = "CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL")
@EqualsAndHashCode(of= "id")
@Data @Builder @ToString @AllArgsConstructor @RequiredArgsConstructor
public class ConsentPersonalDataExpirationControl {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SQ_OFB_SYSTEM")
    @SequenceGenerator(schema= "OFB", name= "SQ_OFB_SYSTEM", allocationSize = 1, sequenceName = "SQ_OFB_SYSTEM")
    @Column(name = "EXPIRATIONCONTROLID")
    private Long id;

    @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "CREATIONDATETIME")
    private Timestamp creationDatetime;

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

    @Column(name = "LOGGEDUSERIDENTIFICATION")
    private String loggedUserIdentification;

    @Column(name = "LOGGEDUSERDOCUMENTREL")
    private String loggedUserDocumentRel;

    @Column(name = "BUSINESSENTITYIDENTIFICATION")
    private String businessEntityIdentification;

    @Column(name = "BUSINESSENTITYDOCUMENTREL")
    private String businessEntityDocumentRel;

    @Column(name = "CREATE_AT")
    private Timestamp createAt;

    @Column(name = "MODIFY_AT")
    private Timestamp modifyAt;

    @Column(name = "USER_CODE")
    private String userCode;

}
