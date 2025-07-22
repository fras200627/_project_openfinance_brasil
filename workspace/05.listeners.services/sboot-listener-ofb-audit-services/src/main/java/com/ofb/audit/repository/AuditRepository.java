package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRepository extends JpaRepository<AuditEntity, Long>,
                                         JpaSpecificationExecutor<AuditEntity> {
}
