package com.tican.audit.controller;

import com.tican.audit.api.server.handler.AuditApiDelegate;
import com.tican.audit.api.server.model.*;
import com.tican.audit.service.AuditService;
import com.tican.lib.security.profiles.CanReadUsers;
import com.tican.lib.security.profiles.CanRootUsers;
import com.tican.lib.security.profiles.CanWriteUsers;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
@SecurityScheme(type = SecuritySchemeType.HTTP,
                name = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class AuditApiControllerImpl implements AuditApiDelegate {

    @Autowired
    private AuditService service;

    @Override
    @CanReadUsers
    public ResponseEntity<List<TicketResponse>> getAllTickets(String pageSortField,
                                                            String pageSortOrder) {
        return new ResponseEntity<>(service.findAllTicketRecord(pageSortField, pageSortOrder),
                                    HttpStatus.OK);
    }

    @Override
    @CanReadUsers
    public ResponseEntity<List<TicketResponse>> getTicketByNumber(@Valid Long ticket) {
        return new ResponseEntity<>(service.findByTicketNumber(ticket.toString()),
                                    HttpStatus.OK);
    }

    @Override
    @CanReadUsers
    public ResponseEntity<TicketRecordPageable> getTicketsByPageable(@Valid Long pageNumber,
                                                                     @Valid Long pageSize,
                                                                     @Valid String pageSortField,
                                                                     @Valid String pageSortOrder) {

        return new ResponseEntity<>(service.findTicketRecordByPageable(pageNumber,
                                                                       pageSize,
                                                                       pageSortField,
                                                                       pageSortOrder),
                                    HttpStatus.OK);
    }

}
