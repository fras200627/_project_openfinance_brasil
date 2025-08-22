package com.ofb.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AccountPersonalDataView")
@Table(schema = "OFB", name = "VW_ACCOUNT_PERSONAL_DATA")
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public class AccountPersonalDataModel {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CPFNUMBER")
    private String CPFNumber;

    @Column(name = "PERSONALID")
    private String personalID;

    @Column(name = "CIVILNAME")
    private String civilName;

    @Column(name = "ACCOUNTID")
    private String accountId;

    @Column(name = "ACCOUNTSTATUS")
    private String accountStatus;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "ACCOUNTSUBTYPE")
    private String accountSubType;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "BRANDNAME")
    private String brandName;

    @Column(name = "COMPANYCNPJ")
    private String companyCNPJ;

    @Column(name = "COMPECODE")
    private String compeCode;

    @Column(name = "BRANCHCODE")
    private String branchCode;

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNTCHECKDIGIT")
    private String accountCheckDigit;

    @Column(name = "UPDATEAMOUNTSDATETIME")
    private String updateAmountDateTime;

    @Column(name = "AVAILABLEAMOUNT")
    private String availableAmount;

    @Column(name = "BLOCKEDAMOUNT")
    private String blockedAmount;

    @Column(name = "AUTOMATICALLYINVESTEDAMOUNT")
    private String automaticallyInvestedAmount;

    @Column(name = "OVERDRAFTCONTRACTEDLIMIT")
    private String overdraftContractedLimit;

    @Column(name = "OVERDRAFTUSEDLIMIT")
    private String overdraftUsedLimit;

    @Column(name = "UNARRANGEDOVERDRAFTAMOUNT")
    private String unarrangedOverdraftAmount;

}
