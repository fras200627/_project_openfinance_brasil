package com.tican.oauth2.users.domain;

import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

public record UserRecordUpdate(
        @NotNull @Digits(integer=10, fraction=0)
        @Min(value = 1) @Max(value =  99999)
        Long id,

        @Nullable @Size(min= 3, max= 100)
        String name,

        @Nullable @Email
        @Size(min= 8, max= 200)
        String email,

        @Nullable @Pattern(regexp = "ADMIN|CLIENT")
        String type,
        
        @Nullable @Pattern(regexp = "ENABLED|DISABLED|EXPIRED|LOCKED")
        String status
) {
}

