package com.ofb.accounts.camel.processor;

import com.ofb.accounts.camel.mapper.ResponseAccountBalancesMapper;
import com.ofb.accounts.camel.mapper.ResponseAccountOverdraftLimitsMapper;
import com.ofb.accounts.server.api.model.ResponseAccountOverdraftLimits;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseAccountOverdraftLimitsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.accounts.server.api.model.ResponseAccountOverdraftLimits response =
        ResponseAccountOverdraftLimitsMapper.INSTANCE.responseAccountOverdraftLimits(
            (com.ofb.accounts.client.accounts.model.ResponseAccountOverdraftLimits) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
