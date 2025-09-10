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

    @Id @Column(name = "ID")
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

    @Column(name = "CPFNUMBER")
    private String personalCpfNumber;

    @Column(name = "BIRTHDATE")
    private String personalBirthDate;

    @Column(name = "SEX")
    private String personalSex;

    @Column(name = "ADDRESS")
    private String personalAddress;

    @Column(name = "DISTRICTNAME")
    private String personalDistrictName;

    @Column(name = "TOWNNAME")
    private String personalTownName;

    @Column(name = "COUNTRYSUBDIVISION")
    private String personalCountrySubDivision;

    @Column(name = "POSTCODE")
    private String personalPostCode;

    @Column(name = "COUNTRY")
    private String personalCountry;

    @Column(name = "PHONETYPE")
    private String personalPhoneType;

    @Column(name = "PHONEAREACODE")
    private String personalPhoneAreaCode;

    @Column(name = "PHONENUMBER")
    private String personalPhoneNumber;

    @Column(name = "EMAIL")
    private String personalEmail;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "ACCOUNTSUBTYPE")
    private String accountSubType;

    @Column(name = "ACCOUNTSTATUS")
    private String accountStatus;

    @Column(name = "ACCOUNTBRANDNAME")
    private String accountBrandName;

    @Column(name = "ACCOUNTCOMPANYCNPJ")
    private String accountCompanyCNPJ;

    @Column(name = "ACCOUNTCOMPECODE")
    private String accountCompeCode;

    @Column(name = "ACCOUNTBRANCHCODE")
    private String accountBranchCode;

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNTCHECKDIGIT")
    private String accountCheckDigit;

    @Column(name = "CURRENCY")
    private String accountCurrency;

    @Column(name = "PERMISSIONID")
    private String permissionId;

    @Column(name = "PERMISSION")
    private String permission;

}
