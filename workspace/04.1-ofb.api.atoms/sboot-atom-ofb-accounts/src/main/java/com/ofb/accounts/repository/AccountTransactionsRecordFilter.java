package com.ofb.accounts.repository;

import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public record AccountTransactionsRecordFilter(
        @Valid @NotNull
        AccountTransactionsPaginationSettings page_settings,

        @Nullable @Size(min= 11, max= 15)
        String CPFNUmber,

        @Nullable @Size(min= 20, max= 256)
        String accountId,

        @Nullable @Size(min= 3, max= 20)
        String accountType,

        @Nullable @Size(min= 3, max= 20)
        String creditDebitType,

        @Nullable @Size(min= 20, max= 20)
        String fromTransactionDateTime,

        @Nullable @Size(min= 20, max= 20)
        String toTransactionDateTime
) {
}

