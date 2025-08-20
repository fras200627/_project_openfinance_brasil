package com.ofb.accounts.service;

import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
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
public class AccountsGetAccountsService {

    @Autowired
    private AccountRequestValidation accountRequestValidation;

    public ResponseAccountList accountsGetAccounts(String authorization, EnumAccountType accountType) {

        ///
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = accountRequestValidation.validateRequest(authorization, accountId, "ACCOUNTS_READ");

        ///
        List<AccountData> accountDataList = new ArrayList<>();
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getPermissions().toString().contains("ACCOUNTS_READ")) {
                if (accountType == null || reg.getAccountType().equals(accountType.getValue())) {
                    accountDataList.add(AccountData.builder()
                            .accountId(reg.getResourceId())
                            .type(EnumAccountType.fromValue(reg.getAccountType()))
                            .branchCode(reg.getAccountBranchCode())
                            .checkDigit(reg.getAccountCheckDigit())
                            .brandName(reg.getAccountBrandName())
                            .companyCnpj(reg.getAccountCompanyCNPJ())
                            .compeCode(reg.getAccountCompeCode())
                            .number(reg.getAccountNumber())
                            .build());
                }
            }
        }

        Links links = null;
        if (accountDataList.size() != 0) {
            links = Links.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .last(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .build();
        }

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .totalPages(accountDataList.size() == 0 ? 0 : 1)
                .totalRecords(accountDataList.size())
                .build();

        ResponseAccountList responseAccountList = ResponseAccountList.builder()
                .data(accountDataList)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountList;
    }

}
