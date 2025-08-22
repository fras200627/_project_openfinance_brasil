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
import java.util.List;

@Service @Slf4j
public class AccountsGetByAccountIdService {

    @Autowired
    private AccountRequestValidation accountRequestValidation;

    public ResponseAccountIdentification accountsGetByAccountId(String authorization, String accountId) {

        ///
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = accountRequestValidation.validateRequest(authorization, accountId, "ACCOUNTS_READ");

        ///
        AccountIdentificationData accountIdentificationData = new AccountIdentificationData();
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(accountId)) {
                if (reg.getPermissions().toString().contains("ACCOUNTS_READ")) {
                    accountIdentificationData.branchCode(reg.getAccountBranchCode());
                    accountIdentificationData.checkDigit(reg.getAccountCheckDigit());
                    accountIdentificationData.compeCode(reg.getAccountCompeCode());
                    accountIdentificationData.currency(reg.getAccountCurrency());
                    accountIdentificationData.number(reg.getAccountNumber());
                    accountIdentificationData.subtype(EnumAccountSubType.fromValue(reg.getAccountSubType()));
                    accountIdentificationData.type(EnumAccountType.fromValue(reg.getAccountType()));
                    break;
                }
            }
        }

        LinksAccountId linksAccountId = LinksAccountId.builder()
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

        ResponseAccountIdentification responseAccountIdentification = ResponseAccountIdentification.builder()
                .data(accountIdentificationData)
                .links(linksAccountId)
                .meta(meta)
                .build();

        return responseAccountIdentification;
    }

}
