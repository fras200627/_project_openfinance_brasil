package com.ofb.consents.controller;

import com.ofb.consents.server.consents.resources.handler.ConsentsApiDelegate;

import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.consents.service.orchestration.*;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanClientOFBWrite;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class ConsentsAPIControllerImpl implements ConsentsApiDelegate {

    @Autowired private ConsentPostService   consentPostService;
    @Autowired private ConsentGetService    consentGetService;
    @Autowired private ConsentRevokeService consentRevokeService;
    @Autowired private ConsentGetExtensionsService consentGetExtensionsService;
    @Autowired private ConsentPostExtendsService consentPostExtendsService;

    @Override
    public ResponseEntity<ResponseConsent> consentsPostConsents(String authorization, UUID xFapiInteractionId, CreateConsent createConsent, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(consentPostService.consentsPostConsents(createConsent),
                HttpStatus.CREATED);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseConsentRead> consentsGetConsentsConsentId(String consentId, String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(consentGetService.consentsGetConsentsConsentId(consentId, authorization, xFapiInteractionId),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead @CanClientOFBWrite
    public ResponseEntity<Void> consentsDeleteConsentsConsentId(String consentId, String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        consentRevokeService.consentsDeleteConsentsConsentId(consentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseConsentReadExtensions> consentsGetConsentsConsentIdExtensions(String consentId, String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize) {
        return new ResponseEntity<>(consentGetExtensionsService.consentsGetConsentsConsentIdExtensions(consentId, authorization, xFapiInteractionId, page, pageSize),
                HttpStatus.OK);
    }





    @Override @CanSystemOFBAdmin @CanClientOFBRead @CanClientOFBWrite
    public ResponseEntity<ResponseConsentExtensions> consentsPostConsentsConsentIdExtends(String consentId, String authorization, String xFapiCustomerIpAddress, UUID xFapiInteractionId, String xCustomerUserAgent, CreateConsentExtensions createConsentExtensions, String xFapiAuthDate) {
        consentPostExtendsService.consentsPostConsentsConsentIdExtends(consentId);
        return ConsentsApiDelegate.super.consentsPostConsentsConsentIdExtends(consentId, authorization, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent, createConsentExtensions, xFapiAuthDate);
    }
}
