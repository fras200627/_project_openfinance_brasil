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
public class AccountsGetOverdraftLimitsByAccountIdService {

    @Autowired
    private AccountsGetAccountDataByIdService accountsGetAccountDataByIdService;

    public ResponseAccountOverdraftLimits accountsGetAccountsAccountIdOverdraftLimits(String accountId) {

        ///
        AccountPersonalDataModel accountPersonalData = accountsGetAccountDataByIdService.getAccountDataById(accountId);

        ///
        AccountOverdraftLimitsData accountOverdraftLimitsData = AccountOverdraftLimitsData.builder()
                .overdraftContractedLimit(AccountOverdraftLimitsDataOverdraftContractedLimit.builder()
                        .amount(accountPersonalData.getOverdraftContractedLimit())
                        .currency(accountPersonalData.getCurrency())
                        .build())
                .overdraftUsedLimit(AccountOverdraftLimitsDataOverdraftUsedLimit.builder()
                        .amount(accountPersonalData.getOverdraftUsedLimit())
                        .currency(accountPersonalData.getCurrency())
                        .build())
                .unarrangedOverdraftAmount(AccountOverdraftLimitsDataUnarrangedOverdraftAmount.builder()
                        .amount(accountPersonalData.getUnarrangedOverdraftAmount())
                        .currency(accountPersonalData.getCurrency())
                        .build())
                .build();

        Links links = Links.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/overdraft-limits").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/overdraft-limits").toString())
                    .last(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/overdraft-limits").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/overdraft-limits").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/accounts/" + accountId + "/overdraft-limits").toString())
                    .build();

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .totalPages(1)
                .totalRecords(1)
                .build();

        ResponseAccountOverdraftLimits responseAccountOverdraftLimits = ResponseAccountOverdraftLimits.builder()
                .data(accountOverdraftLimitsData)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountOverdraftLimits;
    }

}
