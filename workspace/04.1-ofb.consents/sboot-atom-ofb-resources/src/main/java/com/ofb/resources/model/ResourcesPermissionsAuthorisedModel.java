package com.ofb.resources.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ResourcesPermissionsAuthorised")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_PERMISSIONS")
@Data @Builder @ToString @AllArgsConstructor @RequiredArgsConstructor
public class ResourcesPermissionsAuthorisedModel {

    @Id @Column(name = "CONSENTRESOURCEPERMISSIONID")
    private String id;

    @Column(name = "PERSONALCPF")
    private String personalCPF;

    @Column(name = "RESOURCEID")
    private String resourceId;

    @Column(name = "RESOURCETYPE")
    private String resourceType;

    @Column(name = "RESOURCEIDSUMMARY")
    private String resourceIdSummary;

    @Column(name = "RESOURCESTATUS")
    private String resourceStatus;

    @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "CONSENTDATECREATION")
    private String consentDateCreation;

    @Column(name = "CONSENTSTATUS")
    private String consentStatus;

    @Column(name = "CONSENTEXPIRATION")
    private String consentExpiration;

    @Column(name = "PERSONALID")
    private String personalId;

    @Column(name = "PERSONALNAME")
    private String personalName;

    @Column(name = "PERMISSIONID")
    private String permissionId;

    @Column(name = "PERMISSION")
    private String permission;

}
