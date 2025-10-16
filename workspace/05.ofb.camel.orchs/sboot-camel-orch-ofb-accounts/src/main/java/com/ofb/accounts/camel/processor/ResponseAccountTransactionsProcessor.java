package com.ofb.accounts.camel.processor;

import com.ofb.accounts.camel.mapper.ResponseAccountOverdraftLimitsMapper;
import com.ofb.accounts.camel.mapper.ResponseAccountTransactionsMapper;
import com.ofb.accounts.server.api.model.ResponseAccountOverdraftLimits;
import com.ofb.accounts.server.api.model.ResponseAccountTransactions;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseAccountTransactionsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ResponseAccountTransactions response =
        ResponseAccountTransactionsMapper.INSTANCE.responseAccountTransactions(
            (com.ofb.accounts.client.accounts.model.ResponseAccountTransactions) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
