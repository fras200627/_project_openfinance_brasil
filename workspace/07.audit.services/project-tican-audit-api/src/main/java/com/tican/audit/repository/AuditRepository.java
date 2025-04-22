package com.tican.audit.repository;

import com.tican.audit.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AuditRepository extends JpaRepository<AuditEntity, Long>,
                                         JpaSpecificationExecutor<AuditEntity> {

    @Query("""
           SELECT t 
           FROM 
              Audit t 
           WHERE 
              t.ticket = (TRIM(:ticket))
           ORDER BY
              t.ticket, t.requestTime
           """)
    List<AuditEntity> findByTicketNumber(String ticket);

}
