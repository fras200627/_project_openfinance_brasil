package com.tican.oauth2.users.domain;

import javax.validation.constraints.*;

public record UserRecordChangePassword(
        @NotNull @Digits(integer=10, fraction=0)
        @Min(value = 1) @Max(value =  99999)
        Long id,

        @NotNull @NotBlank @Size(min = 4, max = 255)
        String password_old,

        @NotNull @NotBlank @Size(min = 4, max = 255)
        String password_new
) {
}

