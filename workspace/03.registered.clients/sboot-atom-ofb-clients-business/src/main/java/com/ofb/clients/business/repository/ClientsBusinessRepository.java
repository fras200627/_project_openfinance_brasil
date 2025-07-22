package com.ofb.clients.business.repository;

import com.ofb.clients.business.entity.ClientsBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ClientsBusinessRepository extends JpaRepository<ClientsBusinessEntity, String>,
                                                           JpaSpecificationExecutor<ClientsBusinessEntity> {
    @Query("""
            SELECT t FROM Oauth2RegisteredClientEntity t
            WHERE LOWER(TRIM(t.clientId)) = LOWER(TRIM(:clientId))
            """)
    ClientsBusinessEntity findByClientId(String clientId);

    @Query("""
            SELECT t FROM Oauth2RegisteredClientEntity t
            WHERE LOWER(TRIM(t.clientName)) = LOWER(TRIM(:clientName))
            """)
    ClientsBusinessEntity findByClientName(String clientName);
}
