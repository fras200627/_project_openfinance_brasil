package com.ofb.consents.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PersonalDataView")
@Table(schema = "OFB", name = "VW_PERSONAL_DATA") //Materialized View
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public class PersonalDataModel {

    @Id
    private String cpfnumber;
    private String personalid;
    private String civilname;
    private String socialname;
    private String birthdate;
    private String maritalstatuscode;
    private String sex;
    private String address;
    private String districtname;
    private String townname;
    private String countrysubdivision;
    private String postcode;
    private String country;
    private String phonetype;
    private String phoneareacode;
    private String phonenumber;
    private String email;

}
