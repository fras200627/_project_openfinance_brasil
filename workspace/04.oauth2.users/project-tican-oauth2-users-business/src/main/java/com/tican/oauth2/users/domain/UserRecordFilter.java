package com.tican.oauth2.users.domain;

import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Valid
public record UserRecordFilter(
        @Valid @NotNull
        UserPaginationSettings page_settings,

        @Nullable @Size(min= 3, max= 100)
        String name,

        @Nullable @Size(min= 8, max= 200)
        String email,

        @Nullable @Pattern(regexp = "ADMIN|CLIENT")
        String type,

        @Nullable @Pattern(regexp = "ENABLED|DISABLED|EXPIRED|LOCKED")
        String status
) {
}

