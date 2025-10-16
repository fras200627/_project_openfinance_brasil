package com.ofb.accounts.controller;

import com.ofb.accounts.server.accounts.handler.AccountsApiDelegate;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.accounts.service.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class AccountsApiControllerImpl implements AccountsApiDelegate {

    @Autowired
    private AccountsGetByAccountIdService accountsGetByAccountIdService;

    @Autowired
    private AccountsGetBalancesByAccountIdService accountsGetBalancesByAccountIdService;

    @Autowired
    private AccountsGetOverdraftLimitsByAccountIdService accountsGetOverdraftLimitsByAccountIdService;

    @Autowired
    private AccountsGetTransactionsByAccountIdService accountsGetTransactionsByAccountIdService;

    @Override
    public ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String accountId,
                                                                                      UUID xFapiInteractionId) {
        return new ResponseEntity<>(accountsGetByAccountIdService.accountsGetByAccountId(accountId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String accountId,
                                                                                        UUID xFapiInteractionId) {
        return new ResponseEntity<>(accountsGetBalancesByAccountIdService.accountsGetAccountsAccountIdBalances(accountId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String accountId,
                                                                                                      UUID xFapiInteractionId) {
        return new ResponseEntity<>(accountsGetOverdraftLimitsByAccountIdService.accountsGetAccountsAccountIdOverdraftLimits(accountId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String accountId,
                                                                                                UUID xFapiInteractionId,
                                                                                                Integer page,
                                                                                                Integer pageSize,
                                                                                                String fromBookingDate,
                                                                                                String toBookingDate,
                                                                                                EnumCreditDebitIndicator creditDebitIndicator) {
        return new ResponseEntity<>(accountsGetTransactionsByAccountIdService.accountsGetAccountsAccountIdTransactions(
                accountId,
                page, pageSize, fromBookingDate,
                toBookingDate, creditDebitIndicator,
                false),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String accountId,
                                                                                                       UUID xFapiInteractionId,
                                                                                                       Integer page,
                                                                                                       Integer pageSize,
                                                                                                       String fromBookingDate,
                                                                                                       String toBookingDate,
                                                                                                       EnumCreditDebitIndicator creditDebitIndicator) {
        return new ResponseEntity<>(accountsGetTransactionsByAccountIdService.accountsGetAccountsAccountIdTransactions(
                accountId,
                page, pageSize, fromBookingDate,
                toBookingDate, creditDebitIndicator,
                true),
                HttpStatus.OK);
    }

}
