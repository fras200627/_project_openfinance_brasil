package com.ofb.consents.controller;

import com.ofb.consents.server.handler.ConsentsApiDelegate;
import com.ofb.consents.server.model.*;
import com.ofb.consents.service.*;
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

    @Autowired private ConsentPostService consentPostService;
    @Autowired private ConsentGetService consentGetService;
    @Autowired private ConsentRevokeService consentRevokeService;
    @Autowired private ConsentGetExtensionsService consentGetExtensionsService;
    @Autowired private ConsentPostExtendsService consentPostExtendsService;

    @Override
    public ResponseEntity<ResponseConsent> consentsPostConsents(CreateConsent createConsent, UUID xFapiInteractionId) {
        return new ResponseEntity<>(consentPostService.consentsPostConsents(createConsent),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseConsentRead> consentsGetConsentsConsentId(String consentId,
                                                                            UUID xFapiInteractionId) {
        return new ResponseEntity<>(consentGetService.consentsGetConsentsConsentId(consentId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> consentsDeleteConsentsConsentId(String consentId, UUID xFapiInteractionId) {
        consentRevokeService.consentsDeleteConsentsConsentId(consentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ResponseConsentReadExtensions> consentsGetConsentsConsentIdExtensions(String consentId,
                                                                                                UUID xFapiInteractionId,
                                                                                                Integer page,
                                                                                                Integer pageSize) {
        return new ResponseEntity<>(consentGetExtensionsService.consentsGetConsentsConsentIdExtensions(
                consentId,
                xFapiInteractionId,
                page,
                pageSize),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseConsentExtensions> consentsPostConsentsConsentIdExtends(String consentId,
                                                                                          CreateConsentExtensions createConsentExtensions,
                                                                                          UUID xFapiInteractionId) {
        return new ResponseEntity<>(consentPostExtendsService.consentsPostConsentsConsentIdExtends(
                consentId,
                xFapiInteractionId,
                createConsentExtensions),
                HttpStatus.CREATED);
    }

}
