package com.ofb.audit.controller;

import com.ofb.audit.server.handler.AuditApiDelegate;
import com.ofb.audit.server.model.OAuth2ClientResponse;
import com.ofb.audit.server.model.OAuth2ClientsPageable;
import com.ofb.audit.service.AuditService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class AuditApiControllerImpl implements AuditApiDelegate {

    @Autowired private
    AuditService service;

    @Override
    public ResponseEntity<List<OAuth2ClientResponse>> getFindAll() {
        return AuditApiDelegate.super.getFindAll();
    }

    @Override
    public ResponseEntity<OAuth2ClientsPageable> getFindByFilters(Long pageNumber, Long pageSize, String pageSortField, String pageSortOrder, String registeredId, String clientId, String clientName, String securityScope) {
        return AuditApiDelegate.super.getFindByFilters(pageNumber, pageSize, pageSortField, pageSortOrder, registeredId, clientId, clientName, securityScope);
    }

    @Override
    public ResponseEntity<OAuth2ClientResponse> getFindById(String registeredId) {
        return AuditApiDelegate.super.getFindById(registeredId);
    }

    @Override
    public ResponseEntity<OAuth2ClientsPageable> getFindByPageable(Long pageNumber, Long pageSize, String pageSortField, String pageSortOrder) {
        return AuditApiDelegate.super.getFindByPageable(pageNumber, pageSize, pageSortField, pageSortOrder);
    }

}
