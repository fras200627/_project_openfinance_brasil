package com.ofb.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConsentsResourcesAuthorisedCustomersView")
@Table(schema = "OFB", name = "VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_CUSTOMERS")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsentsResourcesAuthorisedCustomersModel {

    @Id
    private Long   consentpermissionauthorisedid;
    private String cpfnumber;
    private String personalid;
    private String customer;
    private String consentid;
    private String consentstatus;
    private String resourcetype;
    private String resourcesummary;
    private String resourcestatus;
    private Long   permissionid;
    private String permission;


}
