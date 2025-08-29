package com.ofb.customers.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.repository.PersonalDataRepository;
import com.ofb.customers.server.customers.model.*;
import com.ofb.customers.service.validation.RequestCustomerValidationService;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class CustomerGetPersonalFinancialRelationsService {

    @Autowired
    private RequestCustomerValidationService requestCustomerValidationService;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public ResponsePersonalCustomersFinancialRelation customersGetPersonalFinancialRelations(String authorization) {

        Gson gson = new Gson();
        String personalId = null;
        PersonalDataModel personalData = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        /// Request Validation and get customer resource
        List<ResourcesCustomerAuthorisedInner> resourcesCustomerAuthorisedInnerList = requestCustomerValidationService.validateRequest(
                authorization, "", "CUSTOMERS_PERSONAL_ADITTIONALINFO_READ");

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

        ///
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
