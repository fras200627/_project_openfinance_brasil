package com.ofb.resources.controller;

import com.ofb.resources.server.api.handler.ResourcesApiDelegate;
import com.ofb.resources.server.api.model.ResponseResourceList;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class ResourcesAPIControllerImpl implements ResourcesApiDelegate {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public ResponseEntity<ResponseResourceList> resourcesGetResources(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize) {
        producerTemplate.sendBody("direct:integracao-arquivo", "Sample content"); // Trigger the Camel route with sample content

        return ResourcesApiDelegate.super.resourcesGetResources(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize);
    }
}
