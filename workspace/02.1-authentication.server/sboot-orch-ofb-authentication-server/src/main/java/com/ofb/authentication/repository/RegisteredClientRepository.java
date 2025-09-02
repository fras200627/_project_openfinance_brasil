package com.ofb.authentication.repository;

import com.ofb.authentication.model.RegisteredClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegisteredClientRepository extends JpaRepository<RegisteredClientEntity, String> {
    Optional<RegisteredClientEntity>  findByClientId(String clientId);
}
