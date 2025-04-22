package com.tican.oauth2.users.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tican.oauth2.users.entity.UserEntity;

import java.util.Date;

public record UserRecord(
        Long id,
        String name,
        String email,
        String type,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        Date createdAt,
        String status
) {
    public UserRecord(UserEntity response) {
        this(response.getId(),
            response.getName(),
            response.getEmail(),
            response.getType(),
            response.getCreatedAt(), 
            response.getStatus());
    }
}

