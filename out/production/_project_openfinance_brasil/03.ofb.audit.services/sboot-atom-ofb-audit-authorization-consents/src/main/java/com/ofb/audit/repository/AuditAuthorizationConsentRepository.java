package com.ofb.audit.repository;

import com.ofb.audit.entity.AuditAuthorizationConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AuditAuthorizationConsentRepository extends JpaRepository<AuditAuthorizationConsentEntity, Long>,
                                                             JpaSpecificationExecutor<AuditAuthorizationConsentEntity> {

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
