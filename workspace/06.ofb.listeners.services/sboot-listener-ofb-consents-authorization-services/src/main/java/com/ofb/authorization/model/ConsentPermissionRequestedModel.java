package com.ofb.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsentPermissionRequestedView")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsentPermissionRequestedModel {

    @Id
    private Long   consentpermissionrequestedid;
    private String cpfnumber;
    private String consentid;
    private String customer;
    private String consentstatus;
    private String consentexpiration;
    private String expirationdatetime;
    private String resourcetype;
    private String permission;
    private String permissioncategory;
    private String permissioncategorygroup;
    private String permissionid;
    private String permissioncategoryorder;

}
