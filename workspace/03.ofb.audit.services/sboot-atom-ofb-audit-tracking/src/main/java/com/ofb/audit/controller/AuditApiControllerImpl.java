package com.ofb.audit.controller;

import com.ofb.audit.server.handler.AuditApiDelegate;
import com.ofb.audit.server.model.AuditResponse;
import com.ofb.audit.server.model.AuditResponsePageable;
import com.ofb.audit.service.AuditService;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override @CanClientOFBRead
    public ResponseEntity<AuditResponse> getFindById(String auditId) {
        return new ResponseEntity<>(service.findById(auditId),
                HttpStatus.OK);
    }

    @Override @CanClientOFBRead
    public ResponseEntity<List<AuditResponse>> getFindAll() {
        return new ResponseEntity<>(service.findAll(),
                HttpStatus.OK);
    }










    @Override
    public ResponseEntity<AuditResponsePageable> getFindByFilters(Long pageNumber,
                                                                  Long pageSize,
                                                                  String pageSortField,
                                                                  String pageSortOrder,
                                                                  String auditId,
                                                                  String xTicketIdId,
                                                                  String xFapiInteractionId,
                                                                  String requestTimeName,
                                                                  String requestURI,
                                                                  String requestMethod,
                                                                  String requestUserName) {
        return AuditApiDelegate.super.getFindByFilters(pageNumber,
                pageSize, pageSortField, pageSortOrder, auditId,
                xTicketIdId, xFapiInteractionId, requestTimeName,
                requestURI, requestMethod, requestUserName);
    }



    @Override
    public ResponseEntity<AuditResponsePageable> getFindByPageable(Long pageNumber,
                                                                   Long pageSize,
                                                                   String pageSortField,
                                                                   String pageSortOrder) {
        return AuditApiDelegate.super.getFindByPageable(pageNumber, pageSize, pageSortField, pageSortOrder);
    }
}
