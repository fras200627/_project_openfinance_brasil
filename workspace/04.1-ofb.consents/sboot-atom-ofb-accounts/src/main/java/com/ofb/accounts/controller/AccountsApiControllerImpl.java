package com.ofb.accounts.controller;

import com.ofb.accounts.server.accounts.handler.AccountsApiDelegate;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.accounts.service.*;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private AccountsGetAccountsService accountsGetAccountsService;

    @Autowired
    private AccountsGetByAccountIdService accountsGetByAccountIdService;

    @Autowired
    private AccountsGetBalancesByAccountIdService accountsGetBalancesByAccountIdService;

    @Autowired
    private AccountsGetOverdraftLimitsByAccountIdService accountsGetOverdraftLimitsByAccountIdService;

    @Autowired
    private AccountsGetTransactionsByAccountIdService accountsGetTransactionsByAccountIdService;

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountList> accountsGetAccounts(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, EnumAccountType accountType, String paginationKey) {
        return new ResponseEntity<>(accountsGetAccountsService.accountsGetAccounts(authorization, accountType),
                                    HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(accountsGetByAccountIdService.accountsGetByAccountId(authorization, accountId),
                                    HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(accountsGetBalancesByAccountIdService.accountsGetAccountsAccountIdBalances(authorization, accountId),
                                    HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return new ResponseEntity<>(accountsGetOverdraftLimitsByAccountIdService.accountsGetAccountsAccountIdOverdraftLimits(authorization, accountId),
                HttpStatus.OK);
    }



    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String fromBookingDate, String toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return new ResponseEntity<>(accountsGetTransactionsByAccountIdService.accountsGetAccountsAccountIdTransactions(authorization, accountId),
                HttpStatus.OK);
    }













    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, String fromBookingDate, String toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactionsCurrent(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }

}
