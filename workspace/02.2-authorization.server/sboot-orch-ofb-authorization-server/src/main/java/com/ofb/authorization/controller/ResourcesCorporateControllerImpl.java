package com.ofb.authorization.controller;

import com.ofb.authorization.server.handler.CorporateApiDelegate;
import com.ofb.authorization.server.model.ResourcesAccountPermissions;
import com.ofb.authorization.server.model.ResourcesCustomerPermissions;
import com.ofb.authorization.service.ResourcesService;
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
public class ResourcesCorporateControllerImpl implements CorporateApiDelegate {

    @Autowired
    private ResourcesService resourcesService;

    @Override
    public ResponseEntity<ResourcesAccountPermissions> resourcesGetAccountPermissions(String authorization, String consentId) {
        return new ResponseEntity<>(resourcesService.resourcesGetAccountPermissions(
                authorization,
                consentId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResourcesCustomerPermissions> resourcesGetCustomerPermissions(String authorization, String consentId) {
        return new ResponseEntity<>(resourcesService.resourcesGetCustomerPermissions(
                authorization,
                consentId),
                HttpStatus.OK);
    }

}
