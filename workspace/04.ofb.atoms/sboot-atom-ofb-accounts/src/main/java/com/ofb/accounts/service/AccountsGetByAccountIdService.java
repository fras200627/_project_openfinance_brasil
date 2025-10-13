package com.ofb.accounts.service;

import com.google.gson.Gson;
import com.ofb.accounts.client.resources.handler.ResourcesApi;
import com.ofb.accounts.client.resources.model.ConsentIdentification;
import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.client.resources.model.ResourcesAccountPermissions;
import com.ofb.accounts.model.AccountPersonalDataModel;
import com.ofb.accounts.repository.AccountPersonalDataRepository;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class AccountsGetByAccountIdService {

    @Autowired
    private AccountPersonalDataRepository accountPersonalRepository;

    public ResponseAccountIdentification accountsGetByAccountId(String accountId) {

        /// Build Response
        AccountPersonalDataModel accountPersonalData = accountPersonalRepository.findAccountByAccountId(accountId);
        AccountIdentificationData accountIdentificationData = AccountIdentificationData.builder()
                .branchCode(accountPersonalData.getBranchCode())
                .checkDigit(accountPersonalData.getAccountCheckDigit())
                .compeCode(accountPersonalData.getCompeCode())
                .currency(accountPersonalData.getCurrency())
                .number(accountPersonalData.getAccountNumber())
                .subtype(EnumAccountSubType.fromValue(accountPersonalData.getAccountSubType()))
                .type(EnumAccountType.fromValue(accountPersonalData.getAccountType()))
                .build();

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
