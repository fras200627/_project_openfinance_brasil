package com.ofb.consents.controller;

import com.ofb.consents.server.healthchecks.handler.HealthApiDelegate;
import com.ofb.consents.server.healthchecks.model.Code200Template;
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
