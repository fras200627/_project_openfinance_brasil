package com.ofb.authentication.controller;

import com.ofb.authentication.server.handler.Oauth2ApiDelegate;
import com.ofb.authentication.server.model.TokenResponseModelTemplate;
import com.ofb.authentication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class Oauth2ApiControllerImpl implements Oauth2ApiDelegate {

    private final AuthenticationService authenticationService;


    public Oauth2ApiControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<TokenResponseModelTemplate> postOauth2Token() {
        return ResponseEntity.ok().body(authenticationService.generateClientToken());
    }

    @Override
    public ResponseEntity<TokenResponseModelTemplate> getAccessToken(@RequestHeader String consentId) {
        return ResponseEntity.ok().body(authenticationService.generateAccessToken(consentId));
    }

}
