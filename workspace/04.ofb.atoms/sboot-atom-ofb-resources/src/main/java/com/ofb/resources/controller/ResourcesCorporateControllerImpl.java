package com.ofb.resources.controller;

import com.ofb.resources.server.corporate.handler.CorporateApiDelegate;
import com.ofb.resources.server.corporate.model.ResourcesAccountPermissions;
import com.ofb.resources.server.corporate.model.ResourcesCustomerPermissions;
import com.ofb.resources.service.ResourcesCorporateService;
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
    private ResourcesCorporateService resourcesCorporateService;

    @Override
    public ResponseEntity<ResourcesAccountPermissions> resourcesGetAccountPermissions(String authorization, String consentId) {
        return new ResponseEntity<>(resourcesCorporateService.resourcesGetAccountPermissions(
                authorization,
                consentId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResourcesCustomerPermissions> resourcesGetCustomerPermissions(String authorization, String consentId) {
        return new ResponseEntity<>(resourcesCorporateService.resourcesGetCustomerPermissions(
                authorization,
                consentId),
                HttpStatus.OK);
    }

}
