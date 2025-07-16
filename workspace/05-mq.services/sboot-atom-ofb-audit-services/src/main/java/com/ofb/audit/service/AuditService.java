package com.ofb.audit.service;

import com.ofb.audit.model.AuditRecord;
import com.ofb.audit.entity.AuditEntity;
import com.ofb.audit.repository.AuditRepository;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    private AuditRepository repository;

    public void save(MessageAuditTemplate messageAuditTemplate) {
        repository.saveAndFlush(new AuditEntity(messageAuditTemplate));
    }

}
