package com.ofb.participants.controller;

import com.ofb.participants.server.handler.BusinessApiDelegate;
import com.ofb.participants.server.model.OAuth2ClientResponse;
import com.ofb.participants.server.model.OAuth2ClientsPageable;
import com.ofb.participants.service.ClientsBusinessService;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
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
public class BusinessApiControllerImpl implements BusinessApiDelegate {

    @Autowired private
    ClientsBusinessService service;

    @Override @CanSystemOFBAdmin
    public ResponseEntity<List<OAuth2ClientResponse>> getFindAll() {
        return new ResponseEntity<>(service.findAll(),
                                    HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OAuth2ClientResponse> getFindByClientDocument(String clientDocument) {
        return new ResponseEntity<>(service.findByClientDocument(clientDocument),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientResponse> getFindById(String registeredId) {
        return new ResponseEntity<>(service.findById(registeredId),
                HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientResponse> getFindByClientId(String clientId) {
        return new ResponseEntity<>(service.findByClientId(clientId),
                                    HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientResponse> getFindByClientName(String clientName) {
        return new ResponseEntity<>(service.findByClientName(clientName),
                                    HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientsPageable> getFindByFilters(Long pageNumber,
                                                                  Long pageSize,
                                                                  String pageSortField,
                                                                  String pageSortOrder,
                                                                  String registeredId,
                                                                  String clientId,
                                                                  String clientName,
                                                                  String securityScope) {

        return new ResponseEntity<>(service.findByFilters(pageNumber,
                                                         pageSize,
                                                         pageSortField,
                                                         pageSortOrder,
                                                        registeredId,
                                                        clientId,
                                                        clientName,
                                                        securityScope),
                                    HttpStatus.OK);
    }

    @Override @CanSystemOFBAdmin
    public ResponseEntity<OAuth2ClientsPageable> getFindByPageable(Long pageNumber,
                                                                   Long pageSize,
                                                                   String pageSortField,
                                                                   String pageSortOrder) {

        return new ResponseEntity<>(service.findByPageable(pageNumber,
                                                           pageSize,
                                                           pageSortField,
                                                           pageSortOrder),
                                    HttpStatus.OK);
    }

}
