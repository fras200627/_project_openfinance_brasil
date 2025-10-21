package com.ofb.consents.camel.processor;

import com.ofb.consents.camel.mapper.ResponsePostConsentExtendsMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseConsentPostConsentExtendsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.consents.server.model.ResponseConsentExtensions response = ResponsePostConsentExtendsMapper.INSTANCE.responseConsentExtends(
            (com.ofb.consents.client.consents.model.ResponseConsentExtensions) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
