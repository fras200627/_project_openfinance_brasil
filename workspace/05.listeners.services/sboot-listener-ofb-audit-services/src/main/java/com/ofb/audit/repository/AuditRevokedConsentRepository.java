package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditRevokedConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRevokedConsentRepository extends JpaRepository<AuditRevokedConsentEntity, Long>,
                                         JpaSpecificationExecutor<AuditRevokedConsentEntity> {
}
