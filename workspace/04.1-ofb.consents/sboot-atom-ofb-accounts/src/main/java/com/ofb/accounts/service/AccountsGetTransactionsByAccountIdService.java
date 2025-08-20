package com.ofb.accounts.service;

import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.model.AccountTransactionDataModel;
import com.ofb.accounts.repository.AccountTransactionsDataRepository;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.accounts.service.validation.AccountRequestValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class AccountsGetTransactionsByAccountIdService {

    @Autowired
    private AccountRequestValidation accountRequestValidation;

    @Autowired
    private AccountTransactionsDataRepository accountTransactionsDataRepository;

    public ResponseAccountTransactions accountsGetAccountsAccountIdTransactions(String authorization, String accountId) {

        ///
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = accountRequestValidation.validateRequest(authorization, accountId, "ACCOUNTS_OVERDRAFT_LIMITS_READ");

        ///
        List<AccountTransactionDataModel> accountTransactionList = accountTransactionsDataRepository.findAccountByAccountId(accountId);
        List<AccountTransactionsData> accountTransactionsDataList = new ArrayList<>();
        for (AccountTransactionDataModel reg : accountTransactionList) {
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

        TransactionsLinks links = TransactionsLinks.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .build();

        MetaOnlyRequestDateTime meta = MetaOnlyRequestDateTime.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())                .build();

        ResponseAccountTransactions responseAccountTransactions = ResponseAccountTransactions.builder()
                .data(accountTransactionsDataList)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountTransactions;
    }

}
