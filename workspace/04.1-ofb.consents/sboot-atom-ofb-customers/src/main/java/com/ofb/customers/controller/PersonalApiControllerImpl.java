package com.ofb.customers.controller;

import com.ofb.customers.server.customers.handler.PersonalApiDelegate;
import com.ofb.customers.server.customers.model.*;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class PersonalApiControllerImpl implements PersonalApiDelegate {


    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersIdentification> customersGetPersonalIdentifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String paginationKey) {

        List<PersonalPostalAddress> postalAddresses = new ArrayList<>();
        postalAddresses.add(PersonalPostalAddress.builder()
                .additionalInfo("x")
                .address("x")
                .country("x")
                .countryCode("x")
                .countrySubDivision(EnumCountrySubDivision.SP)
                .districtName("x")
                .geographicCoordinates(GeographicCoordinates.builder()
                        .latitude("x")
                        .longitude("x")
                        .build())
                .ibgeTownCode("x")
                .isMain(true)
                .postCode("x")
                .townName("x")
                .build());

        List<CustomerPhone> phones = new ArrayList<>();
        phones.add(CustomerPhone.builder()
                .additionalInfo("x")
                .areaCode("x")
                .countryCallingCode("x")
                .isMain(true)
                .number("x")
                .phoneExtension("x")
                .type(EnumCustomerPhoneType.MOVEL)
                .build());

        List<CustomerEmail> emails = new ArrayList<>();
        emails.add(CustomerEmail.builder()
                .email("x")
                .isMain(true)
                .build());

        List<String> companiesCnpj = new ArrayList<>();
        companiesCnpj.add("x");

        List<PersonalIdentificationDataFiliationInner> filiation = new ArrayList<>();
        filiation.add(PersonalIdentificationDataFiliationInner.builder()
                .civilName("x")
                .socialName("x")
                .type(EnumFiliationType.MAE)
                .build());

        List<NationalityOtherDocument> documents = new ArrayList<>();
        documents.add(NationalityOtherDocument.builder()
                .additionalInfo("x")
                .country("x")
                .expirationDate(LocalDate.now())
                .issueDate(LocalDate.now())
                .number("x")
                .type("x")
                .build());
        List<Nationality> nationality = new ArrayList<>();
        nationality.add(Nationality.builder()
                .documents(documents)
                .otherNationalitiesInfo("x")
                .build());

        List<PersonalOtherDocument> otherDocuments = new ArrayList<>();
        otherDocuments.add(PersonalOtherDocument.builder()
                .additionalInfo("x")
                .checkDigit("x")
                .expirationDate(LocalDate.now())
                .number("x")
                .type(EnumPersonalOtherDocumentType.CNH)
                .typeAdditionalInfo("x")
                .build());

        List<PersonalIdentificationData> personalIdentificationData = new ArrayList<>();
        personalIdentificationData.add(PersonalIdentificationData.builder()
                .personalId("x")
                .birthDate(LocalDate.now())
                .brandName("x")
                .civilName("x")
                .companiesCnpj(companiesCnpj)
                .contacts(PersonalContacts.builder()
                        .emails(emails)
                        .phones(phones)
                        .postalAddresses(postalAddresses)
                        .build())
                .documents(PersonalDocument.builder()
                        .cpfNumber("00000000099")
                        .passport(PersonalPassport.builder()
                                .country("x")
                                .expirationDate(LocalDate.now())
                                .issueDate(LocalDate.now())
                                .number("x")
                                .build())
                        .build())
                .filiation(filiation)
                .hasBrazilianNationality(true)
                .maritalStatusAdditionalInfo("x")
                .maritalStatusCode(EnumMaritalStatusCode.CASADO)
                .nationality(nationality)
                .otherDocuments(otherDocuments)
                .sex(EnumSex.MASCULINO)
                .socialName("x")
                .updateDateTime(OffsetDateTime.now())
                .build());

        Links links = Links.builder()
               .self(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
               .first(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
               .last(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
               .next(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
               .prev(URI.create("https://api.banco.com.br/open-banking/personal/identifications").toString())
               .build();
        Meta meta = Meta.builder()
               .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
               .build();

        ResponsePersonalCustomersIdentification responseCustomerIndentification = ResponsePersonalCustomersIdentification.builder()
                .data(personalIdentificationData)
                .links(links)
                .meta(meta)
                .build();


        return new ResponseEntity<>(responseCustomerIndentification, HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersQualification> customersGetPersonalQualifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {

        PersonalQualificationData data = PersonalQualificationData.builder()
                .companyCnpj("000000000000199")
                .informedIncome(InformedIncome.builder()
                        .amount(InformedIncomeAmount.builder()
                                .amount("0.0000")
                                .currency("BRL")
                                .build())
                        .date(LocalDate.now())
                        .frequency(EnumInformedIncomeFrequency.ANUAL)
                        .build())
                .informedPatrimony(PersonalInformedPatrimony.builder()
                        .amount(InformedPatrimonyAmount.builder()
                                .amount("0.0000")
                                .currency("BRL")
                                .build())
                        .year(new BigDecimal("2025"))
                        .build())
                .occupationCode(EnumOccupationMainCodeType.OUTRO)
                .occupationDescription("X")
                .updateDateTime(OffsetDateTime.now())
                .build();

        Links links = Links.builder()
                .self(URI.create("https://api.banco.com.br/open-banking/personal/qualificagtions").toString())
                .first(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .last(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .next(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .prev(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .build();
        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                .build();

        ResponsePersonalCustomersQualification responseCustomersQualification = ResponsePersonalCustomersQualification.builder()
                .data(data)
                .links(links)
                .meta(meta)
                .build();

        return new ResponseEntity<>(responseCustomersQualification, HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersFinancialRelation> customersGetPersonalFinancialRelations(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {

        List<EnumProductServiceType> productsServicesType = new ArrayList<>();
        productsServicesType.add(EnumProductServiceType.CARTAO_CREDITO);

        List<PersonalProcurator> procurators = new ArrayList<>();
        procurators.add(PersonalProcurator.builder()
                .civilName("x")
                .cpfNumber("x")
                .socialName("x")
                .type(EnumProcuratorsTypePersonal.REPRESENTANTE_LEGAL)
                .build());

        List<PersonalAccount> accounts = new ArrayList<>();
        accounts.add(PersonalAccount.builder()
                .branchCode("x")
                .checkDigit("x")
                .compeCode("x")
                .number("x")
                .subtype(PersonalAccount.SubtypeEnum.CONJUNTA_SIMPLES)
                .type(EnumAccountTypeCustomers.CONTA_DEPOSITO_A_VISTA)
                .build());

        List<PortabilitiesReceived> portabilitiesReceived = new ArrayList<>();
        portabilitiesReceived.add(PortabilitiesReceived.builder()
                .employerCnpjCpf("x")
                .employerName("x")
                .paycheckBankDetainerCnpj("x")
                .paycheckBankDetainerIspb("x")
                .portabilityApprovalDate(LocalDate.now())
                .build());

        List<PaychecksBankLink> paychecksBankLink = new ArrayList<>();
        paychecksBankLink.add(PaychecksBankLink.builder()
                .accountOpeningDate(LocalDate.now())
                .employerCnpjCpf("x")
                .employerName("x")
                .paycheckBankCnpj("x")
                .paycheckBankIspb("x")
                .build());

        PersonalFinancialRelationData data = PersonalFinancialRelationData.builder()
                .accounts(accounts)
                .paychecksBankLink(paychecksBankLink)
                .portabilitiesReceived(portabilitiesReceived)
                .procurators(procurators)
                .productsServicesType(productsServicesType)
                .productsServicesTypeAdditionalInfo("x")
                .startDate(OffsetDateTime.now())
                .updateDateTime(OffsetDateTime.now())
                .build();

        Links links = Links.builder()
                .self(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .first(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .last(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .next(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .prev(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .build();
        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                .build();

        ResponsePersonalCustomersFinancialRelation responseCustomersFinancialRelation = ResponsePersonalCustomersFinancialRelation.builder()
                .data(data)
                .links(links)
                .meta(meta)
                .build();

        return new ResponseEntity<>(responseCustomersFinancialRelation, HttpStatus.OK);
    }

}
