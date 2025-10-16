package com.ofb.accounts.camel.processor;

import com.ofb.accounts.client.accounts.handler.AccountsApi;
import com.ofb.accounts.client.accounts.model.EnumCreditDebitIndicator;
import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class AccountsGetTransactionsCurrentByAccountIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        ///
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = resourcesAuthorisedList = (List<ResourcesAccountAuthorisedInner>) exchange.getMessage().getBody();

        ///
        AccountsApi accountsApi = new AccountsApi();
        accountsApi.getApiClient().setBasePath(exchange.getProperty("OFB_ACCOUNTS_API_URL").toString());
        accountsApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        accountsApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());

        ///
        com.ofb.accounts.client.accounts.model.ResponseAccountTransactions responseAccountTransactionsCurrent =
                accountsApi.accountsGetAccountsAccountIdTransactionsCurrent(resourcesAuthorisedList.get(0).getResourceId(),
                        UUID.fromString(exchange.getProperty("x-fapi-interaction-id").toString()),
                        Integer.valueOf(exchange.getProperty("page").toString()),
                        Integer.valueOf(exchange.getProperty("pageSize").toString()),
                        exchange.getProperty("fromBookingDate").toString(),
                        exchange.getProperty("toBookingDate").toString(),
                        exchange.getProperty("creditDebitIndicator").toString().equals("") ? null :
                        EnumCreditDebitIndicator.fromValue(exchange.getProperty("creditDebitIndicator").toString())
                );

        ///
        exchange.getMessage().setBody(responseAccountTransactionsCurrent);
    }

}
