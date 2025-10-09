package com.ofb.customers.service;

import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service @Slf4j
public class CustomerGetPersonalQualificationsService {

    @Autowired private HttpServletRequest httpServletRequest;
    @Autowired private CustomerGetPersonalDataService customerGetPersonalDataService;

    public ResponsePersonalCustomersQualification customersGetPersonalQualifications(String customerDocument,
                                                                                     String personalId,
                                                                                     UUID xFapiInteractionId) {

        PersonalDataModel personalData = customerGetPersonalDataService.getPersonalData(customerDocument, personalId);

        /// Build Response
        PersonalQualificationData data = PersonalQualificationData.builder()
                .companyCnpj("none")
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
                .updateDateTime(personalData.getLastUpdate())
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
