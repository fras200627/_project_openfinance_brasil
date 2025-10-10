package com.ofb.customers.controller;

import com.ofb.customers.camel.model.RequestOrchParams;
import com.ofb.customers.camel.model.RequestParam;
import com.ofb.customers.server.api.handler.PersonalApiDelegate;
import com.ofb.customers.server.api.model.ResponsePersonalCustomersFinancialRelation;
import com.ofb.customers.server.api.model.ResponsePersonalCustomersIdentification;
import com.ofb.customers.server.api.model.ResponsePersonalCustomersQualification;
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
public class PersonalApiControllerImpl implements PersonalApiDelegate {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public ResponseEntity<ResponsePersonalCustomersFinancialRelation> customersGetPersonalFinancialRelations(String authorization,
                                                                                                             UUID xFapiInteractionId,
                                                                                                             String xFapiAuthDate,
                                                                                                             String xFapiCustomerIpAddress,
                                                                                                             String xCustomerUserAgent) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("FINANCIAL_READ").build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:customersOrchestrationFlow",
                requestOrchParams, ResponsePersonalCustomersFinancialRelation.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalCustomersIdentification> customersGetPersonalIdentifications(String authorization,
                                                                                                       UUID xFapiInteractionId,
                                                                                                       String xFapiAuthDate,
                                                                                                       String xFapiCustomerIpAddress,
                                                                                                       String xCustomerUserAgent,
                                                                                                       Integer page,
                                                                                                       Integer pageSize,
                                                                                                       String paginationKey) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("IDENTIFICATION_READ").build());
        requestParams.add(RequestParam.builder().paramName("page")
                .paramValue(page).build());
        requestParams.add(RequestParam.builder().paramName("pageSize")
                .paramValue(pageSize).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:customersOrchestrationFlow",
                requestOrchParams, ResponsePersonalCustomersIdentification.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalCustomersQualification> customersGetPersonalQualifications(String authorization,
                                                                                                     UUID xFapiInteractionId,
                                                                                                     String xFapiAuthDate,
                                                                                                     String xFapiCustomerIpAddress,
                                                                                                     String xCustomerUserAgent) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("QUALIFICATION_READ").build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:customersOrchestrationFlow",
                requestOrchParams, ResponsePersonalCustomersQualification.class),
                HttpStatus.OK);
    }
}
