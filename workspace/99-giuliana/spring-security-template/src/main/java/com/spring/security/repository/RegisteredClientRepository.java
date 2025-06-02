package com.spring.security.repository;

import com.spring.security.model.RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RegisteredClientRepository extends JpaRepository<RegisteredClient, String> {
    Optional<RegisteredClient>  findByClientId(String clientId);
}
