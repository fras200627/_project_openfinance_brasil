package com.ofb.customers.service;

import com.google.gson.Gson;
import com.ofb.customers.client.authorization.handler.AuthorizationValidateApi;
import com.ofb.customers.client.authorization.model.ResponseAuthorizationData;
import com.ofb.customers.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.customers.client.authorization.model.ResultStatus;
import com.ofb.customers.client.resources.handler.ResourcesCorporateApi;
import com.ofb.customers.client.resources.model.ConsentCompleteIdentification;
import com.ofb.customers.client.resources.model.ConsentIdentification;
import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.client.resources.model.ResourcesCustomerPermissions;
import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.repository.PersonalDataRepository;
import com.ofb.customers.server.customers.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class CustomerGetPersonalQualificationsService {

    private final static String PERMISSION_REQUIRED = "CUSTOMERS_PERSONAL_ADITTIONALINFO_READ";

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Value("${app.paths.clients.ofb-authorization}")
    private String OFB_PATH_AUTHORIZATION;

    @Autowired private ResourcesCorporateApi resourcesCorporateApi;
    @Autowired private AuthorizationValidateApi authorizationValidateApi;
    @Autowired private PersonalDataRepository personalDataRepository;

    private com.google.gson.Gson gson = new Gson();
    private String consentId;
    private List<ResponseErrorsInnerTemplate>   listResponseErrors;
    private ConsentIdentification consentIdentification;
    private ResponseAuthorizationData responseAuthorizationData;

    public ResponsePersonalCustomersQualification customersGetPersonalQualifications(String accessToken) {

        authorizationValidateApi.getApiClient().setBasePath(OFB_PATH_AUTHORIZATION);
        authorizationValidateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        resourcesCorporateApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
        resourcesCorporateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        consentId                   = "";
        listResponseErrors          = new ArrayList<>();
        consentIdentification       = new ConsentIdentification();
        responseAuthorizationData   = new ResponseAuthorizationData();

        /// Authorize AccessToken
        try {
            responseAuthorizationData = authorizationValidateApi.authorizationValidate();
            consentId = responseAuthorizationData.getData().getResultStatus().getConsentId();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            for (ResultErrorsErrorsInner reg : responseAuthorizationData.getData().getResultErrors().getErrors()) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title(reg.getTitle())
                        .code(reg.getCode())
                        .detail(reg.getDetail().replaceAll("\\\\", ""))
                        .build());
            }
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Get All Customer Resources
        ResourcesCustomerPermissions responseCustomerPermissions = null;
        ConsentCompleteIdentification consentIdentification = null;
        List<ResourcesCustomerAuthorisedInner> resourcesAuthorisedList = new ArrayList<>();

        try {
            responseCustomerPermissions = resourcesCorporateApi.resourcesGetCustomerPermissions(accessToken, consentId);
            consentIdentification = responseCustomerPermissions.getData().getConsentCompleteIdentification();
            resourcesAuthorisedList = responseCustomerPermissions.getData().getResourcesAuthorised();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Customer request error (in ResourcesAPI")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Customer request error (in ResourcesAPI")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error (in ResourcesAPI")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        boolean customerExists = false;
        boolean permissionExists = false;
        for (ResourcesCustomerAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(consentIdentification.getPersonalId())) {
                customerExists = true;
                if (reg.getPermissions().toString().contains(PERMISSION_REQUIRED)) {
                    permissionExists = true;
                    break;
                }
            }
        }

        if (!customerExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The request information(s) for " +
                            "the consentId [" + consentId + "] reported in the AccessToken " +
                            "is not authorized in that consent.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!permissionExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The customer information(s) request for the consentId (" + consentId + ") " +
                            "reported in the AccessToken has [" +  resourcesAuthorisedList.size() + "] " +
                            "Authorised ResourceAccount, but none have the permission = [" + PERMISSION_REQUIRED + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        PersonalDataModel personalData = null;

        try {
            personalData = personalDataRepository.findById(consentIdentification.getPersonalId()).get();
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
