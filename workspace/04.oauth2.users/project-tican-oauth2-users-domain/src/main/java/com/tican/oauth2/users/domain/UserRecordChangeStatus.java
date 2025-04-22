package com.tican.oauth2.users.domain;

import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

public record UserRecordChangeStatus(
        @NotNull @Digits(integer=10, fraction=0)
        @Min(value = 1) @Max(value =  99999)
        Long id,

        @Nullable @Pattern(regexp = "ENABLED|DISABLED|EXPIRED|LOCKED")
        String status
) {
}

