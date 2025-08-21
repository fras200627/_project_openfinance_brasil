package com.ofb.customers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PersonalDataView")
@Table(schema = "OFB", name = "VW_PERSONAL_DATA")
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public class PersonalDataModel {

    @Id
    @Column(name = "PERSONALID")
    private String id;

    @Column(name = "CPFNUMBER")
    private String CPFNumber;

    @Column(name = "CIVILNAME")
    private String civilName;

    @Column(name = "SOCIALNAME")
    private String socialName;

    @Column(name = "BIRTHDATE")
    private String birthDate;

    @Column(name = "MARITALSTATUSCODE")
    private String maritalStatusCode;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DISTRICTNAME")
    private String districtName;

    @Column(name = "TOWNNAME")
    private String townName;

    @Column(name = "COUNTRYSUBDIVISION")
    private String countrySubDivision;

    @Column(name = "POSTCODE")
    private String postCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PHONETYPE")
    private String phoneType;

    @Column(name = "PHONEAREACODE")
    private String phoneAreaCode;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String eMail;
    
}
