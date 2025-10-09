package com.ofb.authorization.controller;

import com.ofb.authorization.server.authorizations.handler.AuthorizationApiDelegate;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationData;
import com.ofb.authorization.server.authorizations.model.ResultStatus;
import com.ofb.authorization.service.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class AuthorizationApiControllerImpl implements AuthorizationApiDelegate {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired private AccessTokenClaimsValidationService   accessTokenClaimsValidationService;
    @Autowired private BusinessEntityValidationService      businessEntityValidationService;
    @Autowired private LoggedUserValidationService          loggedUserValidationService;
    @Autowired private ConsentValidationService             consentValidationService;
    @Autowired private AuthorizationValidationService       authorizationValidationService;

    @Override
    public ResponseEntity<ResponseAuthorizationData> authorizationValidate() {
        String authorization = httpServletRequest.getHeader("Authorization");
        ResponseAuthorizationData responseAuthorizationData = authorizationValidationService.authorizationValidate(authorization);
        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            return new ResponseEntity<>(responseAuthorizationData, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(responseAuthorizationData, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationData> accessTokenClaimsValidate() {
        String authorization = httpServletRequest.getHeader("Authorization");
        ResponseAuthorizationData responseAuthorizationData = accessTokenClaimsValidationService.accessTokenClaimsValidate(authorization);
        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            return new ResponseEntity<>(responseAuthorizationData, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(responseAuthorizationData, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationData> businessEntityValidate() {
        String authorization = httpServletRequest.getHeader("Authorization");
        ResponseAuthorizationData responseAuthorizationData = businessEntityValidationService.businessEntityValidate(authorization);
        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            return new ResponseEntity<>(responseAuthorizationData, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(responseAuthorizationData, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationData> consentValidate() {
        String authorization = httpServletRequest.getHeader("Authorization");
        ResponseAuthorizationData responseAuthorizationData = consentValidationService.consentValidate(authorization);
        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            return new ResponseEntity<>(responseAuthorizationData, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(responseAuthorizationData, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ResponseAuthorizationData> loggedUserValidate() {
        String authorization = httpServletRequest.getHeader("Authorization");
        ResponseAuthorizationData responseAuthorizationData = loggedUserValidationService.loggedUserValidate(authorization);
        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            return new ResponseEntity<>(responseAuthorizationData, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(responseAuthorizationData, HttpStatus.ACCEPTED);
    }
}
