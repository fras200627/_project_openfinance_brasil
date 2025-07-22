package com.tican.oauth2.clients.domain;

import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public record OAuth2RegisteredClientsRecordFilter(
        @Valid @NotNull
        OAuth2RegisteredClientsPaginationSettings page_settings,

        @Nullable @Size(min= 1, max= 9999)
        String id,

        @Nullable @Size(min= 3, max= 100)
        String clientId,

        @Nullable @Size(min= 3, max= 100)
        String clientName,

        @Nullable @Size(min= 3, max= 100)
        String scopes
) {
}

