package com.ofb.resources.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ExchangeSettingsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.setProperty("Authorization", exchange.getMessage().getBody().toString());
    }

}
