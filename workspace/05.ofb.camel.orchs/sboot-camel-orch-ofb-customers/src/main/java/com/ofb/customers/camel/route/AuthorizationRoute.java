package com.ofb.customers.camel.route;

import com.ofb.customers.camel.processor.AuthorizationProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class AuthorizationRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-authorization}")
    private String AUTHORIZATION_API_URL;

    @Override
    public void configure() {

//        errorHandler();

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:authorizationRoute")
                .routeId("authorizationRoute")
                .tracing()
                //.errorHandler()
                //.onException()
                .setProperty("AUTHORIZATION_API_URL", constant(AUTHORIZATION_API_URL))
                .process(new AuthorizationProcessor());
    }
}