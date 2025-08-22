package com.ofb.customers.controller;

import com.ofb.customers.server.customers.handler.BusinessApiDelegate;
import com.ofb.customers.server.customers.model.ResponseBusinessCustomersFinancialRelation;
import com.ofb.customers.server.customers.model.ResponseBusinessCustomersIdentification;
import com.ofb.customers.server.customers.model.ResponseBusinessCustomersQualification;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class BusinessApiControllerImpl implements BusinessApiDelegate {


    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseBusinessCustomersFinancialRelation> customersGetBusinessFinancialRelations(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return BusinessApiDelegate.super.customersGetBusinessFinancialRelations(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseBusinessCustomersIdentification> customersGetBusinessIdentifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String paginationKey) {
        return BusinessApiDelegate.super.customersGetBusinessIdentifications(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, paginationKey);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseBusinessCustomersQualification> customersGetBusinessQualifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return BusinessApiDelegate.super.customersGetBusinessQualifications(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }
}
