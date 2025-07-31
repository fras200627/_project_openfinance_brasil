package com.ofb.accounts.controller;

import com.ofb.accounts.server.accounts.resources.handler.AccountsApiDelegate;
import com.ofb.accounts.server.accounts.resources.model.*;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class AccountsApiControllerImpl implements AccountsApiDelegate {
    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountList> accountsGetAccounts(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, EnumAccountType accountType, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccounts(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, accountType, paginationKey);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountId(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdBalances(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdOverdraftLimits(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, LocalDate fromBookingDate, LocalDate toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactions(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, LocalDate fromBookingDate, LocalDate toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactionsCurrent(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }
}
