package com.ofb.accounts.camel.route;

import com.ofb.accounts.camel.processor.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class AccountDataRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-accounts}")
    private String OFB_ACCOUNTS_API_URL;

    @Override
    public void configure() {

//        errorHandler();

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:accountDataRoute")
            .routeId("accountDataRoute")
            .tracing()
            //.errorHandler()
            //.onException()
            .setProperty("OFB_ACCOUNTS_API_URL", constant(OFB_ACCOUNTS_API_URL))
            .choice()
                .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNTS_READ"))
                    .process(new AccountsGetAccountsProcessor())
                .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_READ"))
                    .process(new AccountsGetAccountIdProcessor())
                .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_BALANCES_READ"))
                    .process(new AccountsGetBalancesByAccountIdProcessor())
                .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_OVERDRAFT_LIMITS_READ"))
                    .process(new AccountsGetOverdraftLimitsByAccountIdProcessor())
                .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_TRANSACTIONS_READ"))
                    .process(new AccountsGetTransactionsByAccountIdProcessor())
                .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_TRANSACTIONS_CURRENT_READ"))
                    .process(new AccountsGetTransactionsCurrentByAccountIdProcessor())
                .end()
            .end();



    }
}