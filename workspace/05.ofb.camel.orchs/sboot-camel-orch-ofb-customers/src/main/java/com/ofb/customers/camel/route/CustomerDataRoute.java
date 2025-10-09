package com.ofb.customers.camel.route;

import com.ofb.customers.camel.processor.CustomersPersonalFinancialsProcessor;
import com.ofb.customers.camel.processor.CustomersPersonalIdentificationsProcessor;
import com.ofb.customers.camel.processor.CustomersPersonalQualificationsProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class CustomerDataRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-customers}")
    private String OFB_CUSTOMERS_API_URL;

    @Override
    public void configure() {

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:customerDataRoute")
            .routeId("customerDataRoute")
            .tracing()
            .setProperty("OFB_CUSTOMERS_API_URL", constant(OFB_CUSTOMERS_API_URL))
            .choice()
                .when(exchangeProperty("requestType").isEqualTo("IDENTIFICATIONS_READ"))
                    .process(new CustomersPersonalIdentificationsProcessor())
                .when(exchangeProperty("requestType").isEqualTo("ADITTIONALINFO_READ"))
                    .process(new CustomersPersonalQualificationsProcessor())
                .when(exchangeProperty("requestType").isEqualTo("FINANCIAL_READ"))
                    .process(new CustomersPersonalFinancialsProcessor())
                .end()
            .end();
    }
}