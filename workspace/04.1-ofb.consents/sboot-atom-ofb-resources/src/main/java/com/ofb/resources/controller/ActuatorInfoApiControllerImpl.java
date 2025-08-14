package com.ofb.resources.controller;

import com.ofb.resources.server.health.checks.handler.ActuatorApiDelegate;
import com.ofb.resources.server.health.checks.model.Code200Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ActuatorInfoApiControllerImpl implements ActuatorApiDelegate {

    @Override
    public ResponseEntity<Code200Template> getAppInfo() {
        return new ResponseEntity<>(
                Code200Template.builder()
                .code(200)
                .message(System.getProperty("App.Title") + " - " +
                        System.getProperty("App.Description") + " [Build version: " +
                        System.getProperty("App.Build.Version") + " Build date: " +
                        System.getProperty("App.Build.Date") + " Contact name: " +
                        System.getProperty("App.Contact.Name") + "]"
                )
                .build(), HttpStatus.OK);
    }

}
