package com.ofb.customers.camel.route;

import com.ofb.customers.camel.processor.CustomerResourcesProcessor;
import com.ofb.customers.camel.processor.CustomersPersonalFinancialsProcessor;
import com.ofb.customers.camel.processor.CustomersPersonalIdentificationsProcessor;
import com.ofb.customers.camel.processor.CustomersPersonalQualificationsProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class CustomerResourcesRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_RESOURCES_API_URL;

    @Override
    public void configure() {

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:customerResourcesRoute")
            .routeId("customerResourcesRoute")
            .tracing()
            .setProperty("OFB_RESOURCES_API_URL", constant(OFB_RESOURCES_API_URL))
            .process(new CustomerResourcesProcessor());
    }
}