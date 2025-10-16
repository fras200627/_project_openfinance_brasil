package com.ofb.accounts.service;

import com.ofb.accounts.model.AccountPersonalDataModel;
import com.ofb.accounts.model.AccountTransactionDataModel;
import com.ofb.accounts.repository.AccountTransactionsDataRepository;
import com.ofb.accounts.repository.AccountTransactionsPaginationSettings;
import com.ofb.accounts.repository.AccountTransactionsRecordFilter;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service @Slf4j
public class AccountsGetTransactionsByAccountIdService {

    @Autowired
    private AccountsGetAccountDataByIdService accountsGetAccountDataByIdService;

    @Autowired
    private AccountTransactionsDataRepository accountTransactionsDataRepository;

    private DateTimeFormatter PARSER1 = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT);

    public ResponseAccountTransactions accountsGetAccountsAccountIdTransactions(String accountId,
                                                                                Integer page, Integer pageSize,
                                                                                String fromBookingDate, String toBookingDate,
                                                                                EnumCreditDebitIndicator creditDebitIndicator,
                                                                                Boolean isTransactionCurrent) {

        ///
        AccountPersonalDataModel accountPersonalData = accountsGetAccountDataByIdService.getAccountDataById(accountId);

        ///  Date Range validate
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
