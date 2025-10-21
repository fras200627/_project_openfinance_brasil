package com.ofb.consents.camel.processor;

import com.ofb.consents.camel.mapper.ResponsePostConsentMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseConsentPostConsentProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.consents.server.model.ResponseConsent response = ResponsePostConsentMapper.INSTANCE.responseConsentExtends(
            (com.ofb.consents.client.consents.model.ResponseConsent) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
