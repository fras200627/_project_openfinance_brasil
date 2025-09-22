package com.ofb.customers.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.repository.PersonalDataRepository;
import com.ofb.customers.server.customers.model.*;
import com.ofb.customers.service.validation.RequestCustomerValidationService;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class CustomerGetPersonalIdentificationsService {

    @Autowired
    private RequestCustomerValidationService requestCustomerValidationService;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public PersonalDataModel getCustomerData(String authorization) {

        Gson gson = new Gson();
        String personalId = "";
        PersonalDataModel personalData = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        try {
            personalData = personalDataRepository.findById(personalId).get();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        return personalData;

    }

    public ResponsePersonalCustomersIdentification customersGetPersonalIdentifications(String authorization,
                                                                                       Integer page,
                                                                                       Integer pageSize) {

        Gson gson = new Gson();
        String personalId = null;
        PersonalDataModel personalData = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        /// Request Validation and get customer resource
        List<ResourcesCustomerAuthorisedInner> resourcesCustomerAuthorisedInnerList = requestCustomerValidationService.validateRequest(
                authorization, "", "CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ");

        try {
            personalId = resourcesCustomerAuthorisedInnerList.get(0).getResourceId();
            personalData = personalDataRepository.findById(personalId).get();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (personalData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATION.getValue())
                    .detail("An error occurred in request: Customer not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        ///
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

        List<String> companiesCnpj = new ArrayList<>();
        companiesCnpj.add("none");

        List<PersonalIdentificationDataFiliationInner> filiation = new ArrayList<>();
        filiation.add(PersonalIdentificationDataFiliationInner.builder()
                .civilName("Mae")
                .socialName("Mae")
                .type(EnumFiliationType.MAE)
                .build());
        filiation.add(PersonalIdentificationDataFiliationInner.builder()
                .civilName("Pai")
                .socialName("Pai")
                .type(EnumFiliationType.PAI)
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
        List<Nationality> nationality = new ArrayList<>();
        nationality.add(Nationality.builder()
                .documents(documents)
                .otherNationalitiesInfo("none")
                .build());

        List<PersonalOtherDocument> otherDocuments = new ArrayList<>();
        otherDocuments.add(PersonalOtherDocument.builder()
                .additionalInfo("none")
                .checkDigit("none")
                .expirationDate("none")
                .number("none")
                .type(EnumPersonalOtherDocumentType.CNH)
                .typeAdditionalInfo("none")
                .build());

        List<PersonalIdentificationData> personalIdentificationData = new ArrayList<>();
        personalIdentificationData.add(PersonalIdentificationData.builder()
                .personalId(personalData.getId())
                .personalStatus(personalData.getStatus())
                .birthDate(personalData.getBirthDate())
                .brandName("none")
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
                                .expirationDate("none")
                                .issueDate("none")
                                .number("none")
                                .build())
                        .build())
                .filiation(filiation)
                .hasBrazilianNationality(true)
                .maritalStatusAdditionalInfo("none")
                .maritalStatusCode(EnumMaritalStatusCode.fromValue(personalData.getMaritalStatusCode()))
                .nationality(nationality)
                .otherDocuments(otherDocuments)
                .sex(EnumSex.fromValue(personalData.getSex()))
                .socialName(personalData.getSocialName())
                .updateDateTime(personalData.getLastUpdate())
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


    public ResponsePersonalCustomerData customersGetPersonalIdentificationSummary(String customerDocument) {

        Gson gson = new Gson();
        PersonalDataModel personalData = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        try {
            personalData = personalDataRepository.findByDocument(customerDocument);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (personalData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An error occurred in request: Customer not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

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
