package com.ofb.consents.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ResourcePermissionsView")
@Table(schema = "OFB", name = "VW_RESOURCES_PERMISSIONS")
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public class ResourcePermissionsModel {

    @Id
    private String resourcepermissionid;
    private String resourcetypeid;
    private String resourcetype;
    private String resourcesummary;
    private String resourcestatus;
    private String permission;
    private String permissionid;
    private String permissioncategory;
    private String control;
    private String controldescription;
    private String permissiongrouping;
    private String permissioncategorygroup;
    private String qualifiedforpj;
    private String qualifiedforpf;
}
