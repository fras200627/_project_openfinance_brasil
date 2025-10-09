package com.ofb.customers.controller;

import com.ofb.customers.server.handler.PersonalApiDelegate;
import com.ofb.customers.server.model.*;
import com.ofb.customers.service.CustomerGetPersonalFinancialRelationsService;
import com.ofb.customers.service.CustomerGetPersonalIdentificationsService;
import com.ofb.customers.service.CustomerGetPersonalIdentificationsSummaryService;
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
    private CustomerGetPersonalIdentificationsSummaryService customerGetPersonalIdentificationsSummaryService;

    @Autowired
    private CustomerGetPersonalQualificationsService customerGetPersonalQualificationsService;

    @Autowired
    private CustomerGetPersonalFinancialRelationsService customerGetPersonalFinancialRelationsService;

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersIdentification> customersGetPersonalIdentifications(String customerDocument,
                                                                                                       String personalId,
                                                                                                       UUID xFapiInteractionId,
                                                                                                       Integer page, Integer pageSize) {
        return new ResponseEntity<>(customerGetPersonalIdentificationsService.customersGetPersonalIdentifications(
                customerDocument,
                personalId,
                xFapiInteractionId,
                page, pageSize),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersQualification> customersGetPersonalQualifications(String customerDocument,
                                                                                                     String personalId,
                                                                                                     UUID xFapiInteractionId) {
        return new ResponseEntity<>(customerGetPersonalQualificationsService.customersGetPersonalQualifications(
                customerDocument,
                personalId,
                xFapiInteractionId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalCustomerData> customerIdentificationSummary(String customerDocument,
                                                                                      String personalId,
                                                                                      UUID xFapiInteractionId) {
        return new ResponseEntity<>(customerGetPersonalIdentificationsSummaryService.customersGetPersonalIdentificationSummary(customerDocument,
                personalId,
                xFapiInteractionId),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponsePersonalCustomersFinancialRelation> customersGetPersonalFinancialRelations(String customerDocument,
                                                                                                             String personalId,
                                                                                                             UUID xFapiInteractionId) {
        return new ResponseEntity<>(customerGetPersonalFinancialRelationsService.customersGetPersonalFinancialRelations(
                customerDocument,
                personalId,
                xFapiInteractionId),
                HttpStatus.OK);
    }

}
