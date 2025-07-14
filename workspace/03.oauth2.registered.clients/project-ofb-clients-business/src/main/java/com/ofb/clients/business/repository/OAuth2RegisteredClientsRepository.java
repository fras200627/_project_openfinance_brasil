package com.ofb.clients.business.repository;

import com.ofb.clients.business.entity.OAuth2RegisteredClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OAuth2RegisteredClientsRepository extends JpaRepository<OAuth2RegisteredClientsEntity, String>,
                                                           JpaSpecificationExecutor<OAuth2RegisteredClientsEntity> {
    @Query("""
            SELECT t FROM Oauth2RegisteredClientEntity t
            WHERE LOWER(TRIM(t.clientId)) = LOWER(TRIM(:clientId))
            """)
    OAuth2RegisteredClientsEntity findByClientId(String clientId);

    @Query("""
            SELECT t FROM Oauth2RegisteredClientEntity t
            WHERE LOWER(TRIM(t.clientName)) = LOWER(TRIM(:clientName))
            """)
    OAuth2RegisteredClientsEntity findByClientName(String clientName);
}
