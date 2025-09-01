package com.ofb.customers.controller;

import com.ofb.customers.server.customers.handler.PersonalApiDelegate;
import com.ofb.customers.server.customers.model.ResponsePersonalCustomersFinancialRelation;
import com.ofb.customers.server.customers.model.ResponsePersonalCustomersIdentification;
import com.ofb.customers.server.customers.model.ResponsePersonalCustomersQualification;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class PersonalApiControllerImpl implements PersonalApiDelegate {

    @Override
    public ResponseEntity<ResponsePersonalCustomersFinancialRelation> customersGetPersonalFinancialRelations(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return PersonalApiDelegate.super.customersGetPersonalFinancialRelations(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override
    public ResponseEntity<ResponsePersonalCustomersIdentification> customersGetPersonalIdentifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String paginationKey) {
        return PersonalApiDelegate.super.customersGetPersonalIdentifications(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, paginationKey);
    }

    @Override
    public ResponseEntity<ResponsePersonalCustomersQualification> customersGetPersonalQualifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return PersonalApiDelegate.super.customersGetPersonalQualifications(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }
}
