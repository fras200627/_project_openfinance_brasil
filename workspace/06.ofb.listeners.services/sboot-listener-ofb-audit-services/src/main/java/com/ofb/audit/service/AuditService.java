package com.ofb.audit.service;

import com.ofb.audit.entity.*;
import com.ofb.audit.repository.*;
import com.ofb.lib.amqp.model.*;
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

    @Autowired
    private AuditRevokedConsentRepository revokedConsentRepository;

    @Autowired
    private AuditExtendsConsentRepository auditConsentsExtends;

    public void saveMessageAudit(MessageAuditTemplate message) {
        repositoryAudit.saveAndFlush(new AuditEntity(message));
    }

    public void saveMessageAuditCancellationConsent(MessageCancelConsentTemplate message) {
        repositoryCancellation.saveAndFlush(new AuditCancellationConsentEntity(message));
    }

    public void saveMessageAuditAuthorizationConsent(MessageAuthorisedConsentTemplate message) {
        repositoryAuthorization.saveAndFlush(new AuditAuthorizationConsentEntity(message));
    }

    public void saveMessageAuditRevokedConsent(MessageRevokeConsentTemplate message) {
        revokedConsentRepository.saveAndFlush(new AuditRevokeConsentEntity(message));
    }

    public void saveMessageAuditExtendsConsent(MessageExtendsConsentTemplate message) {
        auditConsentsExtends.saveAndFlush(new AuditExtendsConsentEntity(message));
    }

}
