package com.ofb.consents.repository.jpa;

import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public record ConsentsExpirationControlRecordFilter(
        @Valid @NotNull
        ConsentsExpirationControlPaginationSettings page_settings,

        @Nullable @Size(min= 1, max= 9999)
        String id,

        @Nullable @Size(min= 3, max= 256)
        String consentId
) {
}

