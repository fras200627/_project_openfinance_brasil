package com.tican.oauth2.users.repository;

import com.tican.oauth2.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserDetails findByName(String name);
    UserEntity  findByEmail(String email);

    @Query("""
            SELECT t FROM UserEntity t
            WHERE LOWER(t.name) = LOWER(:name)
            """)
    UserEntity findByUserName(String name);
}
