package com.ofb.resources.controller;

import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import com.ofb.resources.server.api.handler.ResourcesApiDelegate;
import com.ofb.resources.server.api.model.ResourcesAccountPermissions;
import com.ofb.resources.server.api.model.ResourcesCustomerPermissions;
import com.ofb.resources.server.api.model.ResponseResourceList;
import com.ofb.resources.service.ResourcesService;
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
public class ResourcesApiControllerImpl implements ResourcesApiDelegate {

    @Autowired
    private ResourcesService resourcesService;

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseResourceList> resourcesGetResources(Integer page, Integer pageSize) {
        return new ResponseEntity<>(resourcesService.resourcesGetResources(page, pageSize),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResourcesAccountPermissions> resourcesGetAccountPermissions(String consentId) {
        return new ResponseEntity<>(resourcesService.resourcesGetAccountPermissions(consentId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResourcesCustomerPermissions> resourcesGetCustomerPermissions(String consentId) {
        return new ResponseEntity<>(resourcesService.resourcesGetCustomerPermissions(consentId),
                HttpStatus.OK);
    }

}
