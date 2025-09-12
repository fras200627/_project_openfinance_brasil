package com.ofb.customers.controller;

import com.ofb.customers.server.customers.handler.PersonalApiDelegate;
import com.ofb.customers.server.customers.model.*;
import com.ofb.customers.service.CustomerGetPersonalFinancialRelationsService;
import com.ofb.customers.service.CustomerGetPersonalIdentificationsService;
import com.ofb.customers.service.CustomerGetPersonalQualificationsService;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
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

    @Autowired
    private CustomerGetPersonalIdentificationsService customerGetPersonalIdentificationsService;

    @Autowired
    private CustomerGetPersonalQualificationsService customerGetPersonalQualificationsService;

    @Autowired
    private CustomerGetPersonalFinancialRelationsService customerGetPersonalFinancialRelationsService;

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersIdentification> customersGetPersonalIdentifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String paginationKey) {
        return new ResponseEntity<>(customerGetPersonalIdentificationsService.customersGetPersonalIdentifications(authorization, page, pageSize),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersQualification> customersGetPersonalQualifications(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(customerGetPersonalQualificationsService.customersGetPersonalQualifications(authorization),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalCustomerData> customerIdentificationSummary(String customerDocument) {
        return new ResponseEntity<>(customerGetPersonalIdentificationsService.customersGetPersonalIdentificationSummary(customerDocument),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersFinancialRelation> customersGetPersonalFinancialRelations(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(customerGetPersonalFinancialRelationsService.customersGetPersonalFinancialRelations(authorization),
                HttpStatus.OK);
    }

}
