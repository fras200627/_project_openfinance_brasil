package com.ofb.authorization.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PersonalAccountView")
@Table(schema = "OFB", name = "VW_ACCOUNT_PERSONAL_DATA")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonalAccountsModel {

    @Id
    private String accountid;
    private String cpfnumber;
    private String personalid;
    private String civilname;
    private String accountstatus;
    private String accounttype;
    private String accountsubtype;
    private String currency;
    private String brandname;
    private String companycnpj;
    private String compecode;
    private String branchcode;
    private String accountnumber;
    private String accountcheckdigit;
    private String updateamountsdatetime;
    private String availableamount;
    private String blockedamount;
    private String automaticallyinvestedamount;
    private String overdraftcontractedlimit;
    private String overdraftusedlimit;
    private String unarrangedoverdraftamount;
    
}
