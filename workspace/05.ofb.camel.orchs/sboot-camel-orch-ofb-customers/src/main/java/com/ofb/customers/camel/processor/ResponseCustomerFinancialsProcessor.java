package com.ofb.customers.camel.processor;

import com.ofb.customers.camel.mapper.ResponseCustomersFinancialsMapper;
import com.ofb.customers.camel.mapper.ResponseCustomersIdentificationsMapper;
import com.ofb.customers.client.customers.model.ResponsePersonalCustomersFinancialRelation;
import com.ofb.customers.client.customers.model.ResponsePersonalCustomersIdentification;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ResponseCustomerFinancialsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.customers.server.api.model.ResponsePersonalCustomersFinancialRelation response =
        ResponseCustomersFinancialsMapper.INSTANCE.responseCustommerFinancials(
                (ResponsePersonalCustomersFinancialRelation) exchange.getMessage().getBody());

        exchange.getMessage().setBody(response);
    }

}
