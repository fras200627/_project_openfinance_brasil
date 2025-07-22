package com.tican.oauth2.clients.controller;

import com.tican.oauth2.clients.api.server.handler.HealthApiDelegate;
import com.tican.oauth2.clients.api.server.model.Code200Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HealthApiControllerImpl implements HealthApiDelegate {

    @Override
    public ResponseEntity<Code200Template> getAppHealth() {
        return new ResponseEntity<>(
                Code200Template.builder()
                .code(200)
                .message(System.getProperty("App.Title") + " is running!")
                .build(), HttpStatus.OK);
    }

}
