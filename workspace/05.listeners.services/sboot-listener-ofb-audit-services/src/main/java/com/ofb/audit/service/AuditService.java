package com.ofb.audit.service;

import com.ofb.audit.entity.AuditAuthorizationConsentEntity;
import com.ofb.audit.entity.AuditCancellationConsentEntity;
import com.ofb.audit.entity.AuditEntity;
import com.ofb.audit.entity.AuditRevokedConsentEntity;
import com.ofb.audit.repository.AuditAuthorizationConsentRepository;
import com.ofb.audit.repository.AuditCancellationConsentRepository;
import com.ofb.audit.repository.AuditRepository;
import com.ofb.audit.repository.AuditRevokedConsentRepository;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import com.ofb.lib.amqp.model.MessageAuthorisedConsentModel;
import com.ofb.lib.amqp.model.MessageCancelConsentModel;
import com.ofb.lib.amqp.model.MessageRevokeConsentModel;
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

    public void saveMessageAudit(MessageAuditTemplate message) {
        repositoryAudit.saveAndFlush(new AuditEntity(message));
    }

    public void saveMessageAuditCancellationConsent(MessageCancelConsentModel message) {
        repositoryCancellation.saveAndFlush(new AuditCancellationConsentEntity(message));
    }

    public void saveMessageAuditAuthorizationConsent(MessageAuthorisedConsentModel message) {
        repositoryAuthorization.saveAndFlush(new AuditAuthorizationConsentEntity(message));
    }

    public void saveMessageAuditRevokedConsent(MessageRevokeConsentModel message) {
        revokedConsentRepository.saveAndFlush(new AuditRevokedConsentEntity(message));
    }

}
