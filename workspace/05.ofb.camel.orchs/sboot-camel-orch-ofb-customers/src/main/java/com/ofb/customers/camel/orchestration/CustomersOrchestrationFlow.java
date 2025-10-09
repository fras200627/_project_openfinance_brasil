package com.ofb.customers.camel.orchestration;

import com.ofb.customers.camel.processor.ExchangeSettingsProcessor;
import com.ofb.customers.camel.processor.OrchestrationResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomersOrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:customersOrchestrationFlow")
                .routeId("customersOrchestrationFlow")
                .tracing()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:customerResourcesRoute")
                .to("direct:customerDataRoute")
                .process(new OrchestrationResponseProcessor());
    }
}
