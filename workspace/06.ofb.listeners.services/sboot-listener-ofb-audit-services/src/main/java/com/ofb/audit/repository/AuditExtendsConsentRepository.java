package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditExtendsConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditExtendsConsentRepository extends JpaRepository<AuditExtendsConsentEntity, Long>,
                                         JpaSpecificationExecutor<AuditExtendsConsentEntity> {
}
