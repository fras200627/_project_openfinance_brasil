package com.ofb.customers.service;

import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.server.customers.model.*;
import com.ofb.customers.service.validation.CustomerRequestValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class CustomerGetPersonalQualificationsService {

    @Autowired
    private CustomerRequestValidation customerRequestValidation;


    public ResponsePersonalCustomersQualification customersGetPersonalQualifications(String authorization) {

        ///
        List<ResourcesCustomerAuthorisedInner> resourcesCustomerAuthorisedInnerList = customerRequestValidation.validateRequest(
                authorization, "", "CUSTOMERS_PERSONAL_ADITTIONALINFO_READ");

        String personalId = resourcesCustomerAuthorisedInnerList.get(0).getResourceId();

        ///
        PersonalQualificationData data = PersonalQualificationData.builder()
                .companyCnpj("000000000000199")
                .informedIncome(InformedIncome.builder()
                        .amount(InformedIncomeAmount.builder()
                                .amount("0.0000")
                                .currency("BRL")
                                .build())
                        .date(LocalDate.now().toString())
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
                .updateDateTime(OffsetDateTime.now().toString())
                .build();

        Links links = Links.builder()
                .self(URI.create("https://api.banco.com.br/open-banking/personal/qualificagtions").toString())
                .first(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .last(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .next(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .prev(URI.create("https://api.banco.com.br/open-banking/personal/qualifications").toString())
                .build();
        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        ResponsePersonalCustomersQualification responseCustomersQualification = ResponsePersonalCustomersQualification.builder()
                .data(data)
                .links(links)
                .meta(meta)
                .build();

        return responseCustomersQualification;
    }

}
