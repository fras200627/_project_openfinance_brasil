package com.ofb.consents.camel.processor;

import com.ofb.consents.camel.mapper.ResponseConsentMapper;
import com.ofb.consents.server.model.ResponseConsent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseConsentGetConsentIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.consents.server.model.ResponseConsentRead response = ResponseConsentMapper.INSTANCE.responseConsentRead(
            (com.ofb.consents.client.consents.model.ResponseConsentRead) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
