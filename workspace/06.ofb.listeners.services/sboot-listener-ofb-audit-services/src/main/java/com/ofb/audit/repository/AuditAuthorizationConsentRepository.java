package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditAuthorizationConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditAuthorizationConsentRepository extends JpaRepository<AuditAuthorizationConsentEntity, Long>,
                                         JpaSpecificationExecutor<AuditAuthorizationConsentEntity> {
}
