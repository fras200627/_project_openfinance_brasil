package com.tican.oauth2.users.repository;

import com.tican.oauth2.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    
    //UserDetails findByName(String name);
    
    @Query("""
            SELECT t FROM UserEntity t
            WHERE LOWER(t.name) = LOWER(:name)
            """)
    UserEntity findByUserName(String name);

    @Query("""
            SELECT t FROM UserEntity t
            WHERE LOWER(t.name) = LOWER(:name)
            and t.id != :id
            """)
    UserEntity findByUserName(Long id, String name);
    
    @Query("""
            SELECT t FROM UserEntity t
            WHERE LOWER(t.email) = LOWER(:email)
            and t.id != :id
            """)
    UserEntity findByEmail(Long id, String email);

    @Query("""
            SELECT t FROM UserEntity t
            WHERE LOWER(t.email) = LOWER(:email)
            """)
    UserEntity findByEmail(String email);
    
}
