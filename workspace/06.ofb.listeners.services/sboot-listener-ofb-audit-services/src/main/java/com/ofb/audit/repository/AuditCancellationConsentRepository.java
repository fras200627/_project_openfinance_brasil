package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditCancellationConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditCancellationConsentRepository extends JpaRepository<AuditCancellationConsentEntity, Long>,
                                         JpaSpecificationExecutor<AuditCancellationConsentEntity> {
}
