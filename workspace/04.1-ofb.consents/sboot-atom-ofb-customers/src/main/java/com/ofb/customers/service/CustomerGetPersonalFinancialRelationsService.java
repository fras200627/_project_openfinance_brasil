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
public class CustomerGetPersonalFinancialRelationsService {

    @Autowired
    private CustomerRequestValidation customerRequestValidation;


    public ResponsePersonalCustomersFinancialRelation customersGetPersonalFinancialRelations(String authorization) {

        ///
        List<ResourcesCustomerAuthorisedInner> resourcesCustomerAuthorisedInnerList = customerRequestValidation.validateRequest(
                authorization, "", "CUSTOMERS_PERSONAL_ADITTIONALINFO_READ");

        String personalId = resourcesCustomerAuthorisedInnerList.get(0).getResourceId();

        ///
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
                .portabilityApprovalDate(LocalDate.now().toString())
                .build());

        List<PaychecksBankLink> paychecksBankLink = new ArrayList<>();
        paychecksBankLink.add(PaychecksBankLink.builder()
                .accountOpeningDate(LocalDate.now().toString())
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
                .startDate(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .updateDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        Links links = Links.builder()
                .self(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .first(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .last(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .next(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .prev(URI.create("https://api.banco.com.br/open-banking/personal/financial-relations").toString())
                .build();
        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        ResponsePersonalCustomersFinancialRelation responseCustomersFinancialRelation = ResponsePersonalCustomersFinancialRelation.builder()
                .data(data)
                .links(links)
                .meta(meta)
                .build();

        return responseCustomersFinancialRelation;
    }

}
