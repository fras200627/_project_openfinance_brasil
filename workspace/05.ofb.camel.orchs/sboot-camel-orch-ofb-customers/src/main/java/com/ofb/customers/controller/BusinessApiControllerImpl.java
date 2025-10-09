package com.ofb.customers.controller;

import com.ofb.customers.server.api.handler.BusinessApiDelegate;
import com.ofb.customers.server.api.model.ResponseBusinessCustomersFinancialRelation;
import com.ofb.customers.server.api.model.ResponseBusinessCustomersIdentification;
import com.ofb.customers.server.api.model.ResponseBusinessCustomersQualification;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SecurityScheme(type    = SecuritySchemeType.HTTP,
        name    = "bearerAuth",
        scheme  = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class BusinessApiControllerImpl implements BusinessApiDelegate {
    @Override
    public ResponseEntity<ResponseBusinessCustomersFinancialRelation> customersGetBusinessFinancialRelations(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return BusinessApiDelegate.super.customersGetBusinessFinancialRelations(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override
    public ResponseEntity<ResponseBusinessCustomersIdentification> customersGetBusinessIdentifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String paginationKey) {
        return BusinessApiDelegate.super.customersGetBusinessIdentifications(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, paginationKey);
    }

    @Override
    public ResponseEntity<ResponseBusinessCustomersQualification> customersGetBusinessQualifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return BusinessApiDelegate.super.customersGetBusinessQualifications(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }
}
