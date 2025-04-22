package com.tican.oauth2.users.domain;

import javax.validation.constraints.*;

public record UserRecordCreate(
        @NotNull @NotBlank @Size(min = 5, max = 20)
        String name,

        @NotNull @NotBlank @Size(min = 4, max = 255)
        String password,
        
        @NotNull @NotBlank @Email
        @Size(min= 8, max= 200)
        String email,

        @NotNull @Pattern(regexp = "ADMIN|CLIENT")
        String type
) {
}

