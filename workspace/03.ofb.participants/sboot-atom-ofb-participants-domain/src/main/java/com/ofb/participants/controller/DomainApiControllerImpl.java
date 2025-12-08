package com.ofb.participants.controller;

import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import com.ofb.participants.server.handler.DomainApiDelegate;
import com.ofb.participants.server.model.ClientCreateRecord;
import com.ofb.participants.server.model.ClientUpdateRecord;
import com.ofb.participants.server.model.Code202Template;
import com.ofb.participants.server.model.OAuth2ClientResponse;
import com.ofb.participants.service.OAuth2RegisteredClientsService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
                name = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class DomainApiControllerImpl implements DomainApiDelegate {

    @Autowired private
    HttpServletRequest request;

    @Autowired private OAuth2RegisteredClientsService service;

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientResponse> createRegisteredClient(ClientCreateRecord clientCreateRecord) {
        OAuth2ClientResponse result = service.save(clientCreateRecord);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        return ResponseEntity
                .created(uriBuilder.path(request.getServletPath() + "/findById/{id}").buildAndExpand(result.getRegisteredId()).toUri())
                .body(result);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientResponse> updateRegisteredClient(ClientUpdateRecord clientUpdateRecord) {
        OAuth2ClientResponse result = service.replace(clientUpdateRecord);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        return ResponseEntity
                .created(uriBuilder.path(request.getServletPath() + "/findById/{id}").buildAndExpand(result.getRegisteredId()).toUri())
                .body(result);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<Code202Template> changeSecretRegisteredClient(String registeredId, String clientSecretOld, String clientSecretNew) {
        String result = service.changeClientSecret(registeredId, clientSecretOld, clientSecretNew);

        return ResponseEntity
                .accepted()
                .body(new Code202Template.Builder()
                        .code(202)
                        .message(result)
                        .build()
                );
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<Code202Template> changeStatusRegisteredClient(String registeredId, String status) {

        String result = service.changeStatus(registeredId, status);

        return ResponseEntity
                .accepted()
                .body(new Code202Template.Builder()
                        .code(202)
                        .message(result)
                        .build()
                );
    }

}
