package com.ofb.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsentsResourcesAuthorisedAccountsView")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_ACCOUNTS")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsentsResourcesAuthorisedAccountsModel {

    @Id
    private Long   consentpermissionauthorisedid;
    private String cpfnumber;
    private String customer;
    private String consentid;
    private String consentstatus;
    private String resourcetype;
    private String resourcesummary;
    private String resourcestatus;
    private Long   permissionid;
    private String permission;
    private String permissioncategoryid;
    private String permissioneventgroupid;
    private String permissioncategoryorder;
    private String accountid;
    private String accountstatus;
    private String accounttype;
    private String branchcode;
    private String accountnumber;
    private String accountcheckdigit;

}
