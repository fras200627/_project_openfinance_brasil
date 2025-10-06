package com.ofb.resources.camel.orchestration;

import com.ofb.resources.camel.processor.ExchangeSettingsProcessor;
import com.ofb.resources.camel.processor.OrchestrationResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ResourcesOrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:resourceOrchestrationFlow")
                .routeId("resourceOrchestrationFlow")
                .tracing()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:resourcesRoute")
                .process(new OrchestrationResponseProcessor());
    }
}
