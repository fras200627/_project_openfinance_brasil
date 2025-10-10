package com.ofb.customers.camel.processor;

import com.ofb.customers.camel.mapper.ResponseCustomersQualificationsMapper;
import com.ofb.customers.server.api.model.ResponsePersonalCustomersQualification;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseCustomerQualificationsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.customers.server.api.model.ResponsePersonalCustomersQualification response =
        ResponseCustomersQualificationsMapper.INSTANCE.responseCustommerQualifications(
        (com.ofb.customers.client.customers.model.ResponsePersonalCustomersQualification) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
