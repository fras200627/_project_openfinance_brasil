package com.ofb.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    private Long   consentpermissionauthorisedid;
    private String cpfnumber;
    private String customer;
    private String consentid;
    private String consentstatus;
    private String expirationdatetime;
    private String resourcetype;
    private String resourcestatus;
    private String permission;
    private String permissioncategory;
    private String permissioncategorygroup;
    private String permissionid;
    private String permissioncategoryorder;

}
