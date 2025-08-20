package com.ofb.accounts.service;

import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.model.AccountPersonalDataModel;
import com.ofb.accounts.repository.AccountPersonalDataRepository;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.accounts.service.validation.AccountRequestValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service @Slf4j
public class AccountsGetBalancesByAccountIdService {

    @Autowired
    private AccountRequestValidation accountRequestValidation;

    @Autowired
    private AccountPersonalDataRepository accountPersonalRepository;

    public ResponseAccountBalances accountsGetAccountsAccountIdBalances(String authorization, String accountId) {

        ///
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = accountRequestValidation.validateRequest(authorization, accountId, "ACCOUNTS_BALANCES_READ");

        ///
        AccountBalancesData accountBalancesData = null;
        AccountBalancesDataAutomaticallyInvestedAmount accountBalancesDataAutomaticallyInvestedAmount = null;
        AccountBalancesDataAvailableAmount accountBalancesDataAvailableAmount = null;
        AccountBalancesDataBlockedAmount accountBalancesDataBlockedAmount = null;

        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(accountId)) {
                if (reg.getPermissions().toString().contains("ACCOUNTS_BALANCES_READ")) {
                    accountBalancesDataAutomaticallyInvestedAmount = AccountBalancesDataAutomaticallyInvestedAmount.builder()
                                    .amount("0")
                                    .currency(reg.getAccountCurrency())
                                    .build();

                    accountBalancesDataAvailableAmount =  AccountBalancesDataAvailableAmount.builder()
                                    .amount("0")
                                    .currency(reg.getAccountCurrency())
                                    .build();

                    accountBalancesDataBlockedAmount = AccountBalancesDataBlockedAmount.builder()
                                    .amount("0")
                                    .currency(reg.getAccountCurrency())
                                    .build();
                    break;
                }
            }
        }

        AccountPersonalDataModel accountPersonalData = accountPersonalRepository.findAccountByAccountId(accountId);
        accountBalancesDataAutomaticallyInvestedAmount.amount(accountPersonalData.getAutomaticallyInvestedAmount());
        accountBalancesDataAvailableAmount.amount(accountPersonalData.getAvailableAmount());
        accountBalancesDataBlockedAmount.amount(accountPersonalData.getBlockedAmount());
        accountBalancesData = AccountBalancesData.builder()
                .automaticallyInvestedAmount(accountBalancesDataAutomaticallyInvestedAmount)
                .availableAmount(accountBalancesDataAvailableAmount)
                .blockedAmount(accountBalancesDataBlockedAmount)
                .updateDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .build();

        Links links = Links.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .last(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
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
