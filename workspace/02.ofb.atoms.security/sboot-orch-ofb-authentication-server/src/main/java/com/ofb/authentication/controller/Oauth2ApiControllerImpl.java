package com.ofb.authentication.controller;

import com.ofb.authentication.server.handler.TokenApiDelegate;
import com.ofb.authentication.server.model.AccessTokenRequest;
import com.ofb.authentication.server.model.TokenResponseModelTemplate;
import com.ofb.authentication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Oauth2ApiControllerImpl implements TokenApiDelegate {

    private final AuthenticationService authenticationService;

    public Oauth2ApiControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<TokenResponseModelTemplate> postTokenClient() {
        return ResponseEntity.ok().body(authenticationService.generateClientToken());
    }

    @Override
    public ResponseEntity<TokenResponseModelTemplate> postAccessTokenConsents(AccessTokenRequest accessTokenRequest, UUID xFapiInteractionId) {
        return ResponseEntity.ok().body(authenticationService.generateAccessToken(accessTokenRequest));
    }

}
