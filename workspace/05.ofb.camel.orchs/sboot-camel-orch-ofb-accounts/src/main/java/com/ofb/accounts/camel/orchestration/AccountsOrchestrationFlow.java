package com.ofb.accounts.camel.orchestration;

import com.ofb.accounts.camel.processor.ExchangeSettingsProcessor;
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
//                .to("direct:customerResourcesRoute")
//                .to("direct:customerDataRoute")
//                .choice()
//                    .when(exchangeProperty("requestType").isEqualTo("IDENTIFICATION_READ"))
//                        .process(new ResponseCustomerIdentificationsProcessor())
//                    .when(exchangeProperty("requestType").isEqualTo("QUALIFICATION_READ"))
//                        .process(new ResponseCustomerQualificationsProcessor())
//                    .when(exchangeProperty("requestType").isEqualTo("FINANCIAL_READ"))
//                        .process(new ResponseCustomerFinancialsProcessor())
//                    .end()
                .end();
    }
}
