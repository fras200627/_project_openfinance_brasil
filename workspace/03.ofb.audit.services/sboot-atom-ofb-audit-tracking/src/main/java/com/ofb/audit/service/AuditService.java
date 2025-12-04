package com.ofb.audit.service;

import com.ofb.audit.domain.AuditPaginationSettings;
import com.ofb.audit.domain.AuditRecord;
import com.ofb.audit.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class AuditService {

    private final static String NAMEMODULE = "Audit";

    @Autowired private
    AuditRepository repository;

    public AuditRecord findByTicketNumber(Long ticketNumber, Jwt jwt, HttpServletRequest httpRequest) {

        AuditRecord result;
        result = new AuditRecord(repository.findById(ticketNumber).get());
        return result;
    }

    public List<AuditRecord> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "ticket"))
                .stream()
                .map(AuditRecord::new)
                .toList();
    }

    public Page<AuditRecord> findByPageable(AuditPaginationSettings pageParams,
                                            Jwt jwt,
                                            HttpServletRequest httpRequest)  {

        return repository.findAll(AuditPaginationSettings
                        .PaginationSettingsTemplate(pageParams, "ticket"))
                        .map(AuditRecord::new);
    }
    
}
