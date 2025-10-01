package com.ofb.resources.camel.routes;

import com.ofb.resources.service.ResourcesService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
class ResourcesRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:resourcesRoute")
            .tracing()
            .bean(ResourcesService.class);
    }

//    @Bean
//    public AuthorizationService authorizationService() {
//        return new AuthorizationService();
//    }

}