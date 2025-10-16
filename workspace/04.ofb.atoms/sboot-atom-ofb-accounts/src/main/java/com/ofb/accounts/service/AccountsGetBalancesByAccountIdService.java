package com.ofb.accounts.service;

import com.ofb.accounts.model.AccountPersonalDataModel;
import com.ofb.accounts.server.accounts.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service @Slf4j
public class AccountsGetBalancesByAccountIdService {

    @Autowired
    private AccountsGetAccountDataByIdService accountsGetAccountDataByIdService;

    public ResponseAccountBalances accountsGetAccountsAccountIdBalances(String accountId) {

        ///
        AccountPersonalDataModel accountPersonalData = accountsGetAccountDataByIdService.getAccountDataById(accountId);

        ///
        AccountBalancesData accountBalancesData = AccountBalancesData.builder()
                .automaticallyInvestedAmount(AccountBalancesDataAutomaticallyInvestedAmount.builder()
                        .amount(accountPersonalData.getAutomaticallyInvestedAmount())
                        .currency(accountPersonalData.getCurrency())
                        .build())
                .availableAmount(AccountBalancesDataAvailableAmount.builder()
                        .amount(accountPersonalData.getAvailableAmount())
                        .currency(accountPersonalData.getCurrency())
                        .build())
                .blockedAmount(AccountBalancesDataBlockedAmount.builder()
                        .amount(accountPersonalData.getBlockedAmount())
                        .currency(accountPersonalData.getCurrency())
                        .build())
                .updateDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        Links links = Links.builder()
                .self(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/balances").toString())
                .first(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/balances").toString())
                .last(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/balances").toString())
                .next(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/balances").toString())
                .prev(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/balances").toString())
                .build();

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .totalPages(1)
                .totalRecords(1)
                .build();

        ResponseAccountBalances responseAccountBalances = ResponseAccountBalances.builder()
                .data(accountBalancesData)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountBalances;
    }

}
