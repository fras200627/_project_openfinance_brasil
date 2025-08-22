package com.ofb.consents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsentPermissionAuthorisedView")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsentPermissionAuthorisedModel {

    @Id
    @Column(name = "ID")
    private Long   consentpermissionauthorisedid;

    @Column(name = "CUSTOMERCPFNUMBER")
    private String cpfnumber;

    @Column(name = "CUSTOMERNAME")
    private String customer;

    @Column(name = "PERSONALID")
    private String personalId;

    @Column(name = "CONSENTID")
    private String consentid;

    @Column(name = "CONSENTSTATUS")
    private String consentstatus;

    @Column(name = "EXPIRATIONDATETIME")
    private String expirationdatetime;

    @Column(name = "RESOURCETYPE")
    private String resourcetype;

    @Column(name = "RESOURCESTATUS")
    private String resourcestatus;

    @Column(name = "PERMISSIONID")
    private String permissionId;

    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "PERMISSIONCATEGORY")
    private String permissioncategory;

    @Column(name = "PERMISSIONCATEGORYGROUP")
    private String permissioncategorygroup;

    @Column(name = "PERMISSIONCATEGORYORDER")
    private String permissioncategoryorder;

}
