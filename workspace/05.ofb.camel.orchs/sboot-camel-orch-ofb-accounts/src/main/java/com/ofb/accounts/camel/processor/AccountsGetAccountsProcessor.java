package com.ofb.accounts.camel.processor;

import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.server.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AccountsGetAccountsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = resourcesAuthorisedList = (List<ResourcesAccountAuthorisedInner>) exchange.getMessage().getBody();

        ///
        List<AccountData> accountDataList = new ArrayList<>();
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getPermissions().toString().contains("ACCOUNTS_READ")) {
                if (exchange.getProperty("accountType").toString() == null ||
                    reg.getAccountType().equals(exchange.getProperty("accountType").toString())) {
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

        exchange.getMessage().setBody(responseAccountList);
    }

}
