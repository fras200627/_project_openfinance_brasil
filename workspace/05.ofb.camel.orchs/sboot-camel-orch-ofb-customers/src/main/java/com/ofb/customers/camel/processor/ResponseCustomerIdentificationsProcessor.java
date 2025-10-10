package com.ofb.customers.camel.processor;

import com.ofb.customers.camel.mapper.ResponseCustomersIdentificationsMapper;
import com.ofb.customers.client.customers.model.ResponsePersonalCustomersIdentification;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseCustomerIdentificationsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.customers.server.api.model.ResponsePersonalCustomersIdentification response =
        ResponseCustomersIdentificationsMapper.INSTANCE.responseCustommerIdentifications(
                (ResponsePersonalCustomersIdentification) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
