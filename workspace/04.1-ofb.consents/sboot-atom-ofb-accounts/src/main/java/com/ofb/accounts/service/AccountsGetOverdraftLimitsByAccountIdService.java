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
public class AccountsGetOverdraftLimitsByAccountIdService {

    @Autowired
    private AccountRequestValidation accountRequestValidation;

    @Autowired
    private AccountPersonalDataRepository accountPersonalRepository;

    public ResponseAccountOverdraftLimits accountsGetAccountsAccountIdOverdraftLimits(String authorization, String accountId) {

        ///
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = accountRequestValidation.validateRequest(authorization, accountId, "ACCOUNTS_OVERDRAFT_LIMITS_READ");

        ///
        AccountOverdraftLimitsData accountOverdraftLimitsData = null;
        AccountOverdraftLimitsDataOverdraftContractedLimit overdraftContractedLimit = null;
        AccountOverdraftLimitsDataOverdraftUsedLimit overdraftUsedLimit = null;
        AccountOverdraftLimitsDataUnarrangedOverdraftAmount unarrangedOverdraftAmount = null;

        AccountPersonalDataModel accountPersonalData = accountPersonalRepository.findAccountByAccountId(accountId);
        overdraftContractedLimit.amount(accountPersonalData.getOverdraftContractedLimit());
        overdraftUsedLimit.amount(accountPersonalData.getOverdraftUsedLimit());
        unarrangedOverdraftAmount.amount(accountPersonalData.getUnarrangedOverdraftAmount());

        accountOverdraftLimitsData = AccountOverdraftLimitsData.builder()
                .overdraftContractedLimit(overdraftContractedLimit)
                .overdraftUsedLimit(overdraftUsedLimit)
                .unarrangedOverdraftAmount(unarrangedOverdraftAmount)
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

        ResponseAccountOverdraftLimits responseAccountOverdraftLimits = ResponseAccountOverdraftLimits.builder()
                .data(accountOverdraftLimitsData)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountOverdraftLimits;
    }

}
