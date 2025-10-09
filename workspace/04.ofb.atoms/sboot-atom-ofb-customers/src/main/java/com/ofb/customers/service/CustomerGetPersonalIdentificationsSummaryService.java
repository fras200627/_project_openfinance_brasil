package com.ofb.customers.service;

import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class CustomerGetPersonalIdentificationsSummaryService {

    @Autowired private HttpServletRequest httpServletRequest;
    @Autowired private CustomerGetPersonalDataService customerGetPersonalDataService;

    public ResponsePersonalCustomerData customersGetPersonalIdentificationSummary(String customerDocument,
                                                                                  String personalId,
                                                                                  UUID xFapiInteractionId) {

        PersonalDataModel personalData = customerGetPersonalDataService.getPersonalData(customerDocument, personalId);

        // Build response
        List<PersonalPostalAddress> postalAddresses = new ArrayList<>();
        postalAddresses.add(PersonalPostalAddress.builder()
                .additionalInfo("none")
                .address(personalData.getAddress())
                .country(personalData.getCountry())
                .countryCode("none")
                .countrySubDivision(EnumCountrySubDivision.fromValue(personalData.getCountrySubDivision()))
                .districtName(personalData.getDistrictName())
                .geographicCoordinates(GeographicCoordinates.builder()
                        .latitude("none")
                        .longitude("none")
                        .build())
                .ibgeTownCode("none")
                .isMain(true)
                .postCode(personalData.getPostCode())
                .townName(personalData.getTownName())
                .build());

        List<CustomerPhone> phones = new ArrayList<>();
        phones.add(CustomerPhone.builder()
                .additionalInfo("none")
                .areaCode(personalData.getPhoneAreaCode())
                .countryCallingCode("none")
                .isMain(true)
                .number(personalData.getPhoneNumber())
                .phoneExtension("none")
                .type(EnumCustomerPhoneType.fromValue(personalData.getPhoneType()))
                .build());

        List<CustomerEmail> emails = new ArrayList<>();
        emails.add(CustomerEmail.builder()
                .email(personalData.getEMail())
                .isMain(true)
                .build());

        List<NationalityOtherDocument> documents = new ArrayList<>();
        documents.add(NationalityOtherDocument.builder()
                .additionalInfo("none")
                .country(personalData.getCountry())
                .expirationDate("none")
                .issueDate("none")
                .number("none")
                .type("none")
                .build());

        List<PersonalCustomerData> data = new ArrayList<>();
        data.add(PersonalCustomerData.builder()
                .personalId(personalData.getId())
                .personalStatus(personalData.getStatus())
                .birthDate(personalData.getBirthDate())
                .civilName(personalData.getCivilName())
                .contacts(PersonalContacts.builder()
                        .emails(emails)
                        .phones(phones)
                        .postalAddresses(postalAddresses)
                        .build())
                .documents(PersonalDocument.builder()
                        .cpfNumber(personalData.getCPFNumber())
                        .build())
                .sex(EnumSex.fromValue(personalData.getSex()))
                .socialName(personalData.getSocialName())
                .updateDateTime(personalData.getLastUpdate())
                .build());

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        ResponsePersonalCustomerData responsePersonalCustomerData = ResponsePersonalCustomerData.builder()
                .data(data)
                .meta(meta)
                .build();

        return responsePersonalCustomerData;
    }

}
