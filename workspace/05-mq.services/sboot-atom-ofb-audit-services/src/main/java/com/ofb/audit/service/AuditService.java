package com.ofb.audit.service;

import com.ofb.audit.model.AuditRecord;
import com.ofb.audit.entity.AuditEntity;
import com.ofb.audit.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Slf4j
@Service
public class AuditService {

    private final static String NAMEMODULE = "Audit";

    @Autowired private
    AuditRepository repository;

    public void save(AuditRecord record) {
        repository.saveAndFlush(new AuditEntity(record));
    }


}
