package com.ofb.customers.service;

import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.repository.PersonalDataRepository;
import com.ofb.customers.server.customers.model.*;
import com.ofb.customers.service.validation.CustomerRequestValidation;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.x509.sigi.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class CustomerGetPersonalIdentificationsService {

    @Autowired
    private CustomerRequestValidation customerRequestValidation;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public ResponsePersonalCustomersIdentification customersGetPersonalIdentifications(String authorization,
                                                                                       Integer page,
                                                                                       Integer pageSize) {

        ///
        List<ResourcesCustomerAuthorisedInner> resourcesCustomerAuthorisedInnerList = customerRequestValidation.validateRequest(
                authorization, "", "CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ");

        String personalId = resourcesCustomerAuthorisedInnerList.get(0).getResourceId();

        ///
        PersonalDataModel personalData = personalDataRepository.findById(personalId).get();

        List<PersonalPostalAddress> postalAddresses = new ArrayList<>();
        postalAddresses.add(PersonalPostalAddress.builder()
                .additionalInfo(null)
                .address(personalData.getAddress())
                .country(personalData.getCountry())
                .countryCode(personalData.getCountry())
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
                .additionalInfo("adittionalInfo")
                .areaCode(personalData.getPhoneAreaCode())
                .countryCallingCode(null)
                .isMain(true)
                .number(personalData.getPhoneNumber())
                .phoneExtension(null)
                .type(EnumCustomerPhoneType.MOVEL)
                .build());

        List<CustomerEmail> emails = new ArrayList<>();
        emails.add(CustomerEmail.builder()
                .email(personalData.getEMail())
                .isMain(true)
                .build());

        List<String> companiesCnpj = new ArrayList<>();
        companiesCnpj.add("companiesCNPJ");

        List<PersonalIdentificationDataFiliationInner> filiation = new ArrayList<>();
        filiation.add(PersonalIdentificationDataFiliationInner.builder()
                .civilName(personalData.getCivilName())
                .socialName(personalData.getSocialName())
                .type(EnumFiliationType.MAE)
                .build());

        List<NationalityOtherDocument> documents = new ArrayList<>();
        documents.add(NationalityOtherDocument.builder()
                .additionalInfo("none")
                .country(null)
                .expirationDate(null)
                .issueDate(null)
                .number(null)
                .type(null)
                .build());
        List<Nationality> nationality = new ArrayList<>();
        nationality.add(Nationality.builder()
                .documents(documents)
                .otherNationalitiesInfo(null)
                .build());

        List<PersonalOtherDocument> otherDocuments = new ArrayList<>();
        otherDocuments.add(PersonalOtherDocument.builder()
                .additionalInfo(null)
                .checkDigit(null)
                .expirationDate(null)
                .number(null)
                .type(EnumPersonalOtherDocumentType.CNH)
                .typeAdditionalInfo(null)
                .build());

        List<PersonalIdentificationData> personalIdentificationData = new ArrayList<>();
        personalIdentificationData.add(PersonalIdentificationData.builder()
                .personalId(personalData.getId())
                .birthDate(personalData.getBirthDate())
                .brandName(null)
                .civilName(personalData.getCivilName())
                .companiesCnpj(companiesCnpj)
                .contacts(PersonalContacts.builder()
                        .emails(emails)
                        .phones(phones)
                        .postalAddresses(postalAddresses)
                        .build())
                .documents(PersonalDocument.builder()
                        .cpfNumber(personalData.getCPFNumber())
                        .passport(PersonalPassport.builder()
                                .country("none")
                                .expirationDate(null)
                                .issueDate(null)
                                .number("none")
                                .build())
                        .build())
                .filiation(filiation)
                .hasBrazilianNationality(true)
                .maritalStatusAdditionalInfo(null)
                .maritalStatusCode(EnumMaritalStatusCode.CASADO)
                .nationality(nationality)
                .otherDocuments(otherDocuments)
                .sex(EnumSex.MASCULINO)
                .socialName(personalData.getSocialName())
                .updateDateTime(OffsetDateTime.now().toString())
                .build());

        Links links = Links.builder()
                .self(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
                .first(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
                .last(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
                .next(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
                .prev(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
                .build();
        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        ResponsePersonalCustomersIdentification responseCustomerIndentification = ResponsePersonalCustomersIdentification.builder()
                .data(personalIdentificationData)
                .links(links)
                .meta(meta)
                .build();


        return responseCustomerIndentification;

    }

}
