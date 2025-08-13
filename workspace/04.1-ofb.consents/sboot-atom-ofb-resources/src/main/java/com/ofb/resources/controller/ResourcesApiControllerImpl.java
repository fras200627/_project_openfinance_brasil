package com.ofb.resources.controller;

import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import com.ofb.resources.server.handler.ResourcesApiDelegate;
import com.ofb.resources.server.model.*;
import com.ofb.resources.service.ResourcesService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
    public ResponseEntity<ResponseResourceList> resourcesGetResources(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize) {
        return new ResponseEntity<>(resourcesService.resourcesGetResources(
                                    authorization,
                                    xFapiInteractionId,
                                    xFapiAuthDate,
                                    xFapiCustomerIpAddress,
                                    xCustomerUserAgent,
                                    page, pageSize),
                                    HttpStatus.OK);
  }

    @Override
    public ResponseEntity<ResponseResourcePermissionsList> resourcesGetResourcesPermissions(String authorization, String consentId) {
        return new ResponseEntity<>(resourcesService.resourcesGetResourcesPermissions(
                                    authorization,
                                    consentId),
                                    HttpStatus.OK);
    }

}
