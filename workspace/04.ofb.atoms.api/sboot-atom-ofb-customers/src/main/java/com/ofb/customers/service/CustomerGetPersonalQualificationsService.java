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
    private RequestCustomerValidationService requestCustomerValidationService;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public ResponsePersonalCustomersQualification customersGetPersonalQualifications(String authorization) {

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

        if (personalData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in request: Customer not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        ///
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
