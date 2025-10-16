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
public class AccountsGetByAccountIdService {

    @Autowired
    private AccountsGetAccountDataByIdService accountsGetAccountDataByIdService;

    public ResponseAccountIdentification accountsGetByAccountId(String accountId) {

        ///
        AccountPersonalDataModel accountPersonalData = accountsGetAccountDataByIdService.getAccountDataById(accountId);

        ///
        AccountIdentificationData accountIdentificationData = new AccountIdentificationData();
        accountIdentificationData.branchCode(accountPersonalData.getBranchCode());
        accountIdentificationData.checkDigit(accountPersonalData.getAccountCheckDigit());
        accountIdentificationData.compeCode(accountPersonalData.getCompeCode());
        accountIdentificationData.currency(accountPersonalData.getCurrency());
        accountIdentificationData.number(accountPersonalData.getAccountNumber());
        accountIdentificationData.subtype(EnumAccountSubType.fromValue(accountPersonalData.getAccountSubType()));
        accountIdentificationData.type(EnumAccountType.fromValue(accountPersonalData.getAccountType()));

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
