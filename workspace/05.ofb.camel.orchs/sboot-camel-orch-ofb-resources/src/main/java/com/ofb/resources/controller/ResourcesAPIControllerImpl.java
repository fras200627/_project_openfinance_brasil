package com.ofb.resources.controller;

import com.ofb.resources.camel.model.RequestOrchParams;
import com.ofb.resources.camel.model.RequestParam;
import com.ofb.resources.server.api.handler.ResourcesApiDelegate;
import com.ofb.resources.server.api.model.ResponseResourceList;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityScheme(type    = SecuritySchemeType.HTTP,
                name    = "bearerAuth",
                scheme  = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class ResourcesAPIControllerImpl implements ResourcesApiDelegate {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public ResponseEntity<ResponseResourceList> resourcesGetResources(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:resourceOrchestrationFlow", requestOrchParams, ResponseResourceList.class),
                HttpStatus.OK);
    }

}
