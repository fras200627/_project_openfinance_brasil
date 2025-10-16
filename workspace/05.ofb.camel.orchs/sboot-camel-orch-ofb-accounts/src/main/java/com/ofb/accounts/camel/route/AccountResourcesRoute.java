package com.ofb.accounts.camel.route;

import com.ofb.accounts.camel.processor.AccountResourcesProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class AccountResourcesRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_RESOURCES_API_URL;

    @Override
    public void configure() {

//        errorHandler();

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end()

        from("direct:accountResourcesRoute")
            .routeId("accountResourcesRoute")
            .tracing()
            //.errorHandler()
            //.onException()
            .setProperty("OFB_RESOURCES_API_URL", constant(OFB_RESOURCES_API_URL))
            .process(new AccountResourcesProcessor());
    }
}