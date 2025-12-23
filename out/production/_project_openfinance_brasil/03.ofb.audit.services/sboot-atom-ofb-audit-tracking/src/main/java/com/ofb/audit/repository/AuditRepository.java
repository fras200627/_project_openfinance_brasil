package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRepository extends JpaRepository<AuditEntity, Long>,
                                         JpaSpecificationExecutor<AuditEntity> {

//    @Query("""
//            SELECT t FROM Oauth2RegisteredClientEntity t
//            WHERE LOWER(TRIM(t.clientId)) = LOWER(TRIM(:clientId))
//            """)
//    ClientsBusinessEntity findByClientId(String clientId);
//
//    @Query("""
//            SELECT t FROM Oauth2RegisteredClientEntity t
//            WHERE LOWER(TRIM(t.clientName)) = LOWER(TRIM(:clientName))
//            """)
//    ClientsBusinessEntity findByClientName(String clientName);
//
//    @Query("""
//            SELECT t FROM Oauth2RegisteredClientEntity t
//            WHERE LOWER(TRIM(t.document)) = LOWER(TRIM(:clientDocument))
//            """)
//    ClientsBusinessEntity findByClientDocument(String clientDocument);

}
