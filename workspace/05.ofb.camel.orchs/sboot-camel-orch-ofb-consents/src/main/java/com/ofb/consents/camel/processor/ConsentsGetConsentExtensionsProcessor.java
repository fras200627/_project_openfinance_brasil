package com.ofb.consents.camel.processor;

import com.ofb.consents.client.consents.handler.ConsentsApi;
import com.ofb.consents.client.consents.model.ResponseConsentReadExtensions;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ConsentsGetConsentExtensionsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        ///
        ConsentsApi consentsApi = new ConsentsApi();
        consentsApi.getApiClient().setBasePath(exchange.getProperty("OFB_CONSENTS_API_URL").toString());
        consentsApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        consentsApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());

        ///
        ResponseConsentReadExtensions responseConsentReadExtensions = consentsApi.consentsGetConsentsConsentIdExtensions(
                exchange.getProperty("consentId").toString(),
                UUID.fromString(exchange.getProperty("x-fapi-interaction-id").toString()),
                Integer.valueOf(exchange.getProperty("page").toString()),
                Integer.valueOf(exchange.getProperty("pageSize").toString()));

        ///
        exchange.getMessage().setBody(responseConsentReadExtensions);
    }

}
