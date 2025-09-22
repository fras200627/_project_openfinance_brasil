package com.ofb.accounts.service;

import com.google.gson.Gson;
import com.ofb.accounts.client.authorization.handler.AuthorizationValidateApi;
import com.ofb.accounts.client.authorization.model.ResponseAuthorizationData;
import com.ofb.accounts.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.accounts.client.authorization.model.ResultStatus;
import com.ofb.accounts.client.resources.handler.ResourcesCorporateApi;
import com.ofb.accounts.client.resources.model.ConsentIdentification;
import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.client.resources.model.ResourcesAccountPermissions;
import com.ofb.accounts.model.AccountTransactionDataModel;
import com.ofb.accounts.repository.AccountPersonalDataRepository;
import com.ofb.accounts.repository.AccountTransactionsDataRepository;
import com.ofb.accounts.repository.AccountTransactionsPaginationSettings;
import com.ofb.accounts.repository.AccountTransactionsRecordFilter;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service @Slf4j
public class AccountsGetTransactionsByAccountIdService {

    private final static String PERMISSION_REQUIRED = "ACCOUNTS_OVERDRAFT_LIMITS_READ";

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Value("${app.paths.clients.ofb-authorization}")
    private String OFB_PATH_AUTHORIZATION;

    @Autowired private ResourcesCorporateApi resourcesCorporateApi;
    @Autowired private AuthorizationValidateApi authorizationValidateApi;
    @Autowired private AccountPersonalDataRepository accountPersonalRepository;
    @Autowired private AccountTransactionsDataRepository accountTransactionsDataRepository;

    private Gson gson = new Gson();
    private String consentId;
    private List<ResponseErrorsInnerTemplate>   listResponseErrors;
    private ResourcesAccountPermissions responseAccountPermissions;
    private ConsentIdentification consentIdentification;
    private List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList;
    private ResponseAuthorizationData responseAuthorizationData;

    public ResponseAccountTransactions accountsGetAccountsAccountIdTransactions(String accessToken, String accountId,
                                                                                Integer page, Integer pageSize,
                                                                                String fromBookingDate, String toBookingDate,
                                                                                EnumCreditDebitIndicator creditDebitIndicator,
                                                                                Boolean isTransactionCurrent) {

        authorizationValidateApi.getApiClient().setBasePath(OFB_PATH_AUTHORIZATION);
        authorizationValidateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        resourcesCorporateApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
        resourcesCorporateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        consentId                   = "";
        listResponseErrors          = new ArrayList<>();
        responseAccountPermissions  = new ResourcesAccountPermissions();
        consentIdentification       = new ConsentIdentification();
        resourcesAuthorisedList     = new ArrayList<>();
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

        /// Get All Accounts Resources
        try {
            responseAccountPermissions = resourcesCorporateApi.resourcesGetAccountPermissions(accessToken, consentId);
            consentIdentification   = responseAccountPermissions.getData().getConsentIdentification();
            resourcesAuthorisedList = responseAccountPermissions.getData().getResourcesAuthorised();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Account request error (in ResourcesAPI)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Account request error (in ResourcesAPI)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Checks if accounts exist for consentId and if required permission is granted
        boolean accountExists    = false;
        boolean permissionExists = false;
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(accountId)) {
                accountExists = true;
                if (reg.getPermissions().toString().contains(PERMISSION_REQUIRED)) {
                    permissionExists = true;
                    break;
                }
            }
        }
        if (!accountExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The requested account is not authorized in " +
                            "the consent informed by the AccessToken.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
        if (!permissionExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Requested account does not have the required permission.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        ///  Date Range validate
        DateTimeFormatter PARSER1 = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT);
        if ((isTransactionCurrent == true) || (fromBookingDate == null || fromBookingDate.isEmpty()) || (toBookingDate == null || toBookingDate.isEmpty())) {
            fromBookingDate = OffsetDateTime.now().minusDays(6).format(PARSER1);
            toBookingDate   = OffsetDateTime.now().format(PARSER1);
        }

        ///
        AccountTransactionsPaginationSettings page_settings = new AccountTransactionsPaginationSettings(page, pageSize, "transactionDateTime", "ASC");
        AccountTransactionsRecordFilter filter = new AccountTransactionsRecordFilter(page_settings,
                                                                                     null,
                                                                                     accountId,
                                                                                     null,
                                                                                     creditDebitIndicator == null ? null : creditDebitIndicator.getValue(),
                                                                                     fromBookingDate, toBookingDate);
        Specification<AccountTransactionDataModel> filterSpecs = this.buildFilter(filter);
        Pageable pageParams = AccountTransactionsPaginationSettings.PaginationSettingsTemplate(filter.page_settings(), "transactionDateTime");
        Page<AccountTransactionDataModel> accountTransactionList = accountTransactionsDataRepository.findAll(filterSpecs, pageParams);

        List<AccountTransactionsData> accountTransactionsDataList = new ArrayList<>();
        for (AccountTransactionDataModel reg : accountTransactionList.getContent()) {
            accountTransactionsDataList.add(AccountTransactionsData.builder()
                    .type(EnumTransactionTypes.fromValue(reg.getTransactionType()))
                    .transactionName(reg.getTransactionName())
                    .creditDebitType(EnumCreditDebitIndicator.fromValue(reg.getCreditDebitType()))
                    .partieBranchCode(reg.getBranchCode())
                    .partieCompeCode(reg.getCompeCode())
                    .partiePersonType(EnumPartiePersonType.fromValue("PESSOA_NATURAL"))
                    .transactionDateTime(reg.getTransactionDateTime())
                    .completedAuthorisedPaymentType(EnumCompletedAuthorisedPaymentIndicator.fromValue(reg.getCompleteAuthorisedPaymentType()))
                    .partieCheckDigit(reg.getAccountCheckDigit())
                    .partieCnpjCpf(reg.getCPFNUmber())
                    .partieNumber(reg.getAccountNumber())
                    .transactionAmount(AccountTransactionsDataAmount.builder()
                            .amount(reg.getTransactionAmount())
                            .currency(reg.getTransactionCurrency())
                            .build())
                    .transactionId(reg.getId())
                    .build());
        }

        TransactionsLinks links = null;
        if (accountTransactionList.getContent().size() != 0) {
            int pageFirst = 1;
            int pageNext = accountTransactionList.getTotalPages() - page == 0 ? accountTransactionList.getTotalPages() : page + 1;
            int pagePrevius = accountTransactionList.getTotalPages() - page == 0 ? accountTransactionList.getTotalPages() : page - 1;
            //int pageLast = accountTransactionList.getTotalPages();

            links = TransactionsLinks.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking//accounts/"  + accountId + "/transactions" + (isTransactionCurrent == false ? "" : "-current")).toString())
                    .first(URI.create("https://api.banco.com.br/open-banking//accounts/" + accountId + "/transactions" + (isTransactionCurrent == false ? "" : "-current") + "?page="  + pageFirst + "&page-size="   + pageSize).toString())
                    .next(URI.create("https://api.banco.com.br/open-banking//accounts/"  + accountId + "/transactions" + (isTransactionCurrent == false ? "" : "-current") + "?page=" + pageNext + "&page-size="    + pageSize).toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking//accounts/"  + accountId + "/transactions" + (isTransactionCurrent == false ? "" : "-current") + "?page="  + pagePrevius + "&page-size=" + pageSize).toString())
                    .build();
        }

        MetaOnlyRequestDateTime meta = MetaOnlyRequestDateTime.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        ResponseAccountTransactions responseAccountTransactions = ResponseAccountTransactions.builder()
                .data(accountTransactionsDataList)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountTransactions;
    }

    private Specification<AccountTransactionDataModel> buildFilter(AccountTransactionsRecordFilter filter) {
        Specification<AccountTransactionDataModel> specs = null;
        RequestFilterParams requestFilterParams = null;

        if (filter.accountId() != null && filter.accountId().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("accountId",
                    RequestFilterPredicatesEnum.EQUAL_STRING,
                    filter.accountId().trim().toUpperCase(),
                    null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.accountType() != null && filter.accountType().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("accountType",
                    RequestFilterPredicatesEnum.EQUAL_STRING,
                    filter.accountType().trim().toUpperCase(),
                    null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.CPFNUmber() != null && filter.CPFNUmber().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("CPFNumber",
                    RequestFilterPredicatesEnum.EQUAL_STRING,
                    filter.CPFNUmber().trim().toUpperCase(),
                    null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.creditDebitType() != null && filter.creditDebitType().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("creditDebitType",
                    RequestFilterPredicatesEnum.EQUAL_STRING,
                    filter.creditDebitType().trim().toUpperCase(),
                    null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.fromTransactionDateTime() != null && filter.fromTransactionDateTime().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("txDateTime",
                    RequestFilterPredicatesEnum.BETWEEN_DATES,
                    filter.fromTransactionDateTime().trim(),
                    filter.toTransactionDateTime().trim());
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }
        return specs;
    }

}
