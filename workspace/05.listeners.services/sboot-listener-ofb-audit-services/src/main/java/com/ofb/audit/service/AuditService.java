package com.ofb.audit.service;

import com.ofb.audit.entity.AuditAuthorizationConsentEntity;
import com.ofb.audit.entity.AuditCancellationConsentEntity;
import com.ofb.audit.entity.AuditEntity;
import com.ofb.audit.repository.AuditAuthorizationConsentRepository;
import com.ofb.audit.repository.AuditCancellationConsentRepository;
import com.ofb.audit.repository.AuditRepository;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import com.ofb.lib.amqp.model.MessageAuthorizeConsentModel;
import com.ofb.lib.amqp.model.MessageCancellationConsentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class AuditService {

    @Autowired
    private AuditRepository repositoryAudit;

    @Autowired
    private AuditCancellationConsentRepository repositoryCancellation;

    @Autowired
    private AuditAuthorizationConsentRepository repositoryAuthorization;

    public void saveMessageAudit(MessageAuditTemplate message) {
        repositoryAudit.saveAndFlush(new AuditEntity(message));
    }

    public void saveMessageAuditCancellationConsent(MessageCancellationConsentModel message) {
        repositoryCancellation.saveAndFlush(new AuditCancellationConsentEntity(message));
    }

    public void saveMessageAuditAuthorizationConsent(MessageAuthorizeConsentModel message) {
        repositoryAuthorization.saveAndFlush(new AuditAuthorizationConsentEntity(message));
    }

}
