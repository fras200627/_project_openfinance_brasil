package com.ofb.accounts.controller;

import com.ofb.accounts.camel.model.RequestOrchParams;
import com.ofb.accounts.camel.model.RequestParam;
import com.ofb.accounts.server.api.handler.AccountsApiDelegate;
import com.ofb.accounts.server.api.model.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityScheme(type    = SecuritySchemeType.HTTP,
                name    = "bearerAuth",
                scheme  = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class AccountsApiControllerImpl implements AccountsApiDelegate {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public ResponseEntity<ResponseAccountList> accountsGetAccounts(String authorization,
                                                                   UUID xFapiInteractionId,
                                                                   String xFapiAuthDate,
                                                                   String xFapiCustomerIpAddress,
                                                                   String xCustomerUserAgent,
                                                                   Integer page,
                                                                   Integer pageSize,
                                                                   EnumAccountType accountType,
                                                                   String paginationKey) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_ACCOUNTS_READ").build());
        requestParams.add(RequestParam.builder().paramName("page")
                .paramValue(page).build());
        requestParams.add(RequestParam.builder().paramName("pageSize")
                .paramValue(pageSize).build());
        requestParams.add(RequestParam.builder().paramName("accountType")
                .paramValue(accountType.getValue()).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:accountsOrchestrationFlow",
                requestOrchParams, ResponseAccountList.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String authorization,
                                                                                      UUID xFapiInteractionId,
                                                                                      String accountId,
                                                                                      String xFapiAuthDate,
                                                                                      String xFapiCustomerIpAddress,
                                                                                      String xCustomerUserAgent) {
        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_ACCOUNT_ID_READ").build());
        requestParams.add(RequestParam.builder().paramName("accountId")
                .paramValue(accountId).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:accountsOrchestrationFlow",
                requestOrchParams, ResponseAccountIdentification.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String authorization,
                                                                                        UUID xFapiInteractionId,
                                                                                        String accountId,
                                                                                        String xFapiAuthDate,
                                                                                        String xFapiCustomerIpAddress,
                                                                                        String xCustomerUserAgent) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_ACCOUNT_ID_BALANCES_READ").build());
        requestParams.add(RequestParam.builder().paramName("accountId")
                .paramValue(accountId).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:accountsOrchestrationFlow",
                requestOrchParams, ResponseAccountBalances.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String authorization,
                                                                                                      UUID xFapiInteractionId,
                                                                                                      String accountId,
                                                                                                      String xFapiAuthDate,
                                                                                                      String xFapiCustomerIpAddress,
                                                                                                      String xCustomerUserAgent) {
        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_ACCOUNT_ID_OVERDRAFT_LIMITS_READ").build());
        requestParams.add(RequestParam.builder().paramName("accountId")
                .paramValue(accountId).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:accountsOrchestrationFlow",
                requestOrchParams, ResponseAccountOverdraftLimits.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String authorization,
                                                                                                UUID xFapiInteractionId,
                                                                                                String accountId,
                                                                                                String xFapiAuthDate,
                                                                                                String xFapiCustomerIpAddress,
                                                                                                String xCustomerUserAgent,
                                                                                                Integer page,
                                                                                                Integer pageSize,
                                                                                                String fromBookingDate,
                                                                                                String toBookingDate,
                                                                                                EnumCreditDebitIndicator creditDebitIndicator,
                                                                                                String paginationKey) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_ACCOUNT_ID_TRANSACTIONS_READ").build());
        requestParams.add(RequestParam.builder().paramName("page")
                .paramValue(page).build());
        requestParams.add(RequestParam.builder().paramName("pageSize")
                .paramValue(pageSize).build());
        requestParams.add(RequestParam.builder().paramName("accountId")
                .paramValue(accountId).build());
        requestParams.add(RequestParam.builder().paramName("fromBookingDate")
                .paramValue(fromBookingDate).build());
        requestParams.add(RequestParam.builder().paramName("toBookingDate")
                .paramValue(toBookingDate).build());
        requestParams.add(RequestParam.builder().paramName("creditDebitIndicator")
                .paramValue(creditDebitIndicator.getValue()).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:accountsOrchestrationFlow",
                requestOrchParams, ResponseAccountTransactions.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String authorization,
                                                                                                       UUID xFapiInteractionId,
                                                                                                       String accountId,
                                                                                                       String xFapiAuthDate,
                                                                                                       String xFapiCustomerIpAddress,
                                                                                                       String xCustomerUserAgent,
                                                                                                       Integer page,
                                                                                                       Integer pageSize,
                                                                                                       String fromBookingDate,
                                                                                                       String toBookingDate,
                                                                                                       EnumCreditDebitIndicator creditDebitIndicator,
                                                                                                       String paginationKey) {
        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_ACCOUNT_ID_TRANSACTIONS_CURRENT_READ").build());
        requestParams.add(RequestParam.builder().paramName("page")
                .paramValue(page).build());
        requestParams.add(RequestParam.builder().paramName("pageSize")
                .paramValue(pageSize).build());
        requestParams.add(RequestParam.builder().paramName("accountId")
                .paramValue(accountId).build());
        requestParams.add(RequestParam.builder().paramName("fromBookingDate")
                .paramValue(fromBookingDate).build());
        requestParams.add(RequestParam.builder().paramName("toBookingDate")
                .paramValue(toBookingDate).build());
        requestParams.add(RequestParam.builder().paramName("creditDebitIndicator")
                .paramValue(creditDebitIndicator.getValue()).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:accountsOrchestrationFlow",
                requestOrchParams, ResponseAccountTransactions.class),
                HttpStatus.OK);

    }
}
