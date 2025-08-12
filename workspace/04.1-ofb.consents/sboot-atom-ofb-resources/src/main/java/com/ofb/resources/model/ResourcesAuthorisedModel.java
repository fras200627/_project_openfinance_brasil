package com.ofb.resources.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsentPersonalDataResourcesAuthorised")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED")
@Data @Builder @ToString @AllArgsConstructor @RequiredArgsConstructor
public class ResourcesAuthorisedModel {

    @Id @Column(name = "CONSENTRESOURCEID")
    private String consentResourceId;

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

}
