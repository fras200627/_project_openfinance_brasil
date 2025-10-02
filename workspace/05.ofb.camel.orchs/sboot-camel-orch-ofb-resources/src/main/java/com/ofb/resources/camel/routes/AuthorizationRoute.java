package com.ofb.resources.camel.routes;

import com.ofb.resources.camel.processors.AuthorizationRouteProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class AuthorizationRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-authorization}")
    private String OFB_PATH_AUTHORIZATION;

    @Override
    public void configure() {

        from("direct:authorizationRoute")
                .tracing()
                .setHeader("OFB_PATH_AUTHORIZATION", constant(OFB_PATH_AUTHORIZATION))
                .process(new AuthorizationRouteProcessor());
    }

}