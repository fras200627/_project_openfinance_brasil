package com.ofb.batch.controller;

import com.ofb.batch.server.handler.ConsentsApiDelegate;
import com.ofb.batch.server.model.ResponseExecution;
import com.ofb.batch.service.JobLauncherService;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
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
public class ConsentsApiControllerImpl implements ConsentsApiDelegate {

    @Autowired private JobLauncherService service;

    @Override @CanSystemOFBAdmin
    public ResponseEntity<ResponseExecution> executeJobLauncher(String authorization) {
        return new ResponseEntity<>(service.executeJobLauncher(authorization), HttpStatus.OK);
    }
}
