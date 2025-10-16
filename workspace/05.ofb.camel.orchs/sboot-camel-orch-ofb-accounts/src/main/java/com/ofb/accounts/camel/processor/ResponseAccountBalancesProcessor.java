package com.ofb.accounts.camel.processor;

import com.ofb.accounts.camel.mapper.ResponseAccountBalancesMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseAccountBalancesProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.accounts.server.api.model.ResponseAccountBalances response =
        ResponseAccountBalancesMapper.INSTANCE.responseAccountBalances(
                (com.ofb.accounts.client.accounts.model.ResponseAccountBalances) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
