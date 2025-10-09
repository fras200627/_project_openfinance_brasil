package com.ofb.customers.service;

import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class CustomerGetPersonalFinancialRelationsService {

    @Autowired private HttpServletRequest httpServletRequest;
    @Autowired private CustomerGetPersonalDataService customerGetPersonalDataService;

    public ResponsePersonalCustomersFinancialRelation customersGetPersonalFinancialRelations(String customerDocument,
                                                                                             String personalId,
                                                                                             UUID xFapiInteractionId) {

        PersonalDataModel personalData = customerGetPersonalDataService.getPersonalData(customerDocument, personalId);

        /// Build Response
        List<EnumProductServiceType> productsServicesType = new ArrayList<>();
        productsServicesType.add(EnumProductServiceType.OUTROS);

        List<PersonalProcurator> procurators = new ArrayList<>();
        procurators.add(PersonalProcurator.builder()
                .civilName("none")
                .cpfNumber("none")
                .socialName("none")
                .type(EnumProcuratorsTypePersonal.REPRESENTANTE_LEGAL)
                .build());

        List<PersonalAccount> accounts = new ArrayList<>();
        accounts.add(PersonalAccount.builder()
                .branchCode("none")
                .checkDigit("none")
                .compeCode("none")
                .number("none")
                .subtype(PersonalAccount.SubtypeEnum.INDIVIDUAL)
                .type(EnumAccountTypeCustomers.CONTA_DEPOSITO_A_VISTA)
                .build());

        List<PortabilitiesReceived> portabilitiesReceived = new ArrayList<>();
        portabilitiesReceived.add(PortabilitiesReceived.builder()
                .employerCnpjCpf("none")
                .employerName("none")
                .paycheckBankDetainerCnpj("none")
                .paycheckBankDetainerIspb("none")
                .portabilityApprovalDate(LocalDate.now().toString())
                .build());

        List<PaychecksBankLink> paychecksBankLink = new ArrayList<>();
        paychecksBankLink.add(PaychecksBankLink.builder()
                .accountOpeningDate(LocalDate.now().toString())
                .employerCnpjCpf("none")
                .employerName("none")
                .paycheckBankCnpj("none")
                .paycheckBankIspb("none")
                .build());

        PersonalFinancialRelationData data = PersonalFinancialRelationData.builder()
                .accounts(accounts)
                .paychecksBankLink(paychecksBankLink)
                .portabilitiesReceived(portabilitiesReceived)
                .procurators(procurators)
                .productsServicesType(productsServicesType)
                .productsServicesTypeAdditionalInfo("none")
                .startDate(personalData.getLastUpdate())
                .updateDateTime(personalData.getLastUpdate())
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
