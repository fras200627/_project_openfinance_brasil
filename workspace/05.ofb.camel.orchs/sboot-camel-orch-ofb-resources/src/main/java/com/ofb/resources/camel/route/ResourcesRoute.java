package com.ofb.resources.camel.route;

import com.ofb.resources.camel.processor.ResourcesRouteProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class ResourcesRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_RESOURCES_API_URL;

    @Override
    public void configure() {

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:resourcesRoute")
            .routeId("resourcesRoute")
            .tracing()
            .setProperty("OFB_RESOURCES_API_URL", constant(OFB_RESOURCES_API_URL))
            .process(new ResourcesRouteProcessor());
    }
}