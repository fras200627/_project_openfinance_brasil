package com.ofb.authorization.controller;

import com.ofb.authorization.server.authorizations.handler.AuthorizationApiDelegate;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationValidate;
import com.ofb.authorization.service.AccessTokenValidationService;
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

    @Autowired
    private AccessTokenValidationService accessTokenValidationService;

    @Override
    public ResponseEntity<ResponseAuthorizationValidate> accessTokenValidate(String authorization) {
        return new ResponseEntity<>(accessTokenValidationService.accessTokenValidate(authorization), HttpStatus.OK);
    }
}
