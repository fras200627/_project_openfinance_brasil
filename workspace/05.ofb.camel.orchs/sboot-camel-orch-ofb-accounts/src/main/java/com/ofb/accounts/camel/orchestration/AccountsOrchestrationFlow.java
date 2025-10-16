package com.ofb.accounts.camel.orchestration;

import com.ofb.accounts.camel.processor.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AccountsOrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        errorHandler();

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:accountsOrchestrationFlow")
                .routeId("accountsOrchestrationFlow")
                .tracing()
                //.errorHandler()
                //.onException()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:accountResourcesRoute")
                .to("direct:accountDataRoute")
                .choice()
                    .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_BALANCES_READ"))
                        .process(new ResponseAccountBalancesProcessor())
                    .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_OVERDRAFT_LIMITS_READ"))
                        .process(new ResponseAccountOverdraftLimitsProcessor())
                    .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_TRANSACTIONS_READ"))
                        .process(new ResponseAccountTransactionsProcessor())
                    .when(exchangeProperty("requestType").isEqualTo("GET_ACCOUNT_ID_TRANSACTIONS_CURRENT_READ"))
                        .process(new ResponseAccountTransactionsProcessor())
                    .otherwise()
                        .log("mapping not applicable")
                    .end()
                .end();
    }
}
