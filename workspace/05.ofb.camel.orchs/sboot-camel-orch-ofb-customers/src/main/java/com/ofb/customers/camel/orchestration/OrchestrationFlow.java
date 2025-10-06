package com.ofb.customers.camel.orchestration;

import com.ofb.customers.camel.processor.ExchangeSettingsProcessor;
import com.ofb.customers.camel.processor.OrchestrationResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:orchestrationFlow")
                .routeId("resourceOrchestrationFlow")
                .tracing()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:resourcesRoute")
                .process(new OrchestrationResponseProcessor());
    }
}
