package com.ofb.accounts.camel.processor;

import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.client.accounts.handler.AccountsApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class AccountsGetAccountIdProcessor implements Processor {

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
        com.ofb.accounts.client.accounts.model.ResponseAccountIdentification responseAccountIdentification =
                accountsApi.accountsGetAccountsAccountId(resourcesAuthorisedList.get(0).getResourceId(),
                        UUID.fromString(exchange.getProperty("x-fapi-interaction-id").toString()));

        ///
        exchange.getMessage().setBody(responseAccountIdentification);
    }

}
