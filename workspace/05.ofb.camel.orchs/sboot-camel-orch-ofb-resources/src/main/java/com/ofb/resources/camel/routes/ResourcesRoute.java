package com.ofb.resources.camel.routes;

import com.ofb.resources.camel.processors.ResourcesRouteProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class ResourcesRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Override
    public void configure() {
        from("direct:resourcesRoute")
            .tracing()
            .setHeader("OFB_PATH_RESOURCES", constant(OFB_PATH_RESOURCES))
            .process(new ResourcesRouteProcessor());
    }
}