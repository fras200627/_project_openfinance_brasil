package com.ofb.consents.camel.processor;

import com.ofb.consents.camel.mapper.ResponseGetConsentExtensionsMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseConsentGetConsentExtensionsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.consents.server.model.ResponseConsentReadExtensions response = ResponseGetConsentExtensionsMapper.INSTANCE.responseConsentExtensions(
            (com.ofb.consents.client.consents.model.ResponseConsentReadExtensions) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
