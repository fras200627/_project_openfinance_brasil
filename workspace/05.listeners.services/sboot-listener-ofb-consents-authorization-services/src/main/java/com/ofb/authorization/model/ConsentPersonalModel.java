package com.ofb.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsentPersonalView")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA") //Materialized View
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public class ConsentPersonalModel {

    @Id
    @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "CREATIONDATETIME")
    private String creationDatetime;

    @Column(name = "CONSENTSTATUSID")
    private Long consentStatusId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STATUSUPDATEDATETIME")
    private String statusUpdateDatetime;

    @Column(name = "EXPIRATIONDATETIME")
    private String expirationDatetime;

    @Column(name = "EXPIRATIONDATEINFO")
    private String expirationDateInfo;

    @Column(name = "PERSONALID")
    private String personalId;

    @Column(name = "CIVILNAME")
    private String civilName;

    @Column(name = "CPFNUMBER")
    private String cpfNumber;

    @Column(name = "AWAITINGAUTHBY")
    private String awaitingAuthBy;

    @Column(name = "AWAITINGAUTHSTART")
    private String awaitingAuthStart;

    @Column(name = "AWAITINGAUTHEND")
    private String awaitingAuthEnd;

    @Column(name = "AWAITINGAUTHADDITIONALINFO")
    private String awaitingAuthAdditionalInfo;

    @Column(name = "AUTHORISEDBY")
    private String authorisedBy;

    @Column(name = "AUTHORISEDSTART")
    private String authorisedStart;

    @Column(name = "AUTHORISEDEND")
    private String authorisedEnd;

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
    private String rejectedStartDatetime;

    @Column(name = "REJECTEDENDDATETIME")
    private String rejectedEndDatetime;

}
