package com.ofb.resources.camel.routes;

import com.ofb.resources.service.AuthorizationService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class AuthorizationRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:authorizationRoute")
            .tracing()
            .bean(AuthorizationService.class);
    }

//    @Bean
//    public AuthorizationService authorizationService() {
//        return new AuthorizationService();
//    }

}