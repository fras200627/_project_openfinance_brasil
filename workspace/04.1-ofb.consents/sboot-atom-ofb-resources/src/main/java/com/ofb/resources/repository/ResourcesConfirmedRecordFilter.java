package com.ofb.resources.repository;

import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public record ResourcesConfirmedRecordFilter(
        @Valid @NotNull
        ResourcesConfirmedlPaginationSettings page_settings,

        @Nullable @Size(min= 1, max= 9999)
        String consentResourceId,

        @Nullable @Size(min= 3, max= 256)
        String consentId
) {
}

