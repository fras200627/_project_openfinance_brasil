package com.ofb.resources.camel.routes;

import com.ofb.resources.camel.processors.ExchangeSettingsProcessor;
import com.ofb.resources.camel.processors.OrchestrationResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:orchestrationFlow")
                .routeId("orchestrationFlow")
                .tracing()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:resourcesRoute")
                .process(new OrchestrationResponseProcessor());
    }
}
