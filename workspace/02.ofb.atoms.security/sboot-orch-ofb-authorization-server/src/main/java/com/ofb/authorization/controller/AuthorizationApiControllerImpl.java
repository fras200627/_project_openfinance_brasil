package com.ofb.authorization.controller;

import com.ofb.authorization.server.authorizations.handler.AuthorizationApiDelegate;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationValidate;
import com.ofb.authorization.service.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class AuthorizationApiControllerImpl implements AuthorizationApiDelegate {

    @Autowired private AccessTokenClaimsValidationService accessTokenClaimsValidationService;
    @Autowired private BusinessEntityValidationService businessEntityValidationService;
    @Autowired private LoggedUserValidationService loggedUserValidationService;
    @Autowired private ConsentValidationService consentValidationService;
    @Autowired private AuthorizationValidationService authorizationValidationService;

    @Override
    public ResponseEntity<ResponseAuthorizationValidate> accessTokenClaimsValidate(String authorization) {
        return new ResponseEntity<>(accessTokenClaimsValidationService.accessTokenClaimsValidate(authorization), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationValidate> authorizationValidate(String authorization) {
        return new ResponseEntity<>(authorizationValidationService.authorizationValidate(authorization), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationValidate> businessEntityValidate(String authorization) {
        return new ResponseEntity<>(businessEntityValidationService.businessEntityValidate(authorization), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationValidate> consentValidate(String authorization) {
        return new ResponseEntity<>(consentValidationService.consentValidate(authorization), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationValidate> loggedUserValidate(String authorization) {
        return new ResponseEntity<>(loggedUserValidationService.loggedUserValidate(authorization), HttpStatus.OK);
    }
}
