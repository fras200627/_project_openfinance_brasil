package com.ofb.customers.camel.orchestration;

import com.ofb.customers.camel.processor.ExchangeSettingsProcessor;
import com.ofb.customers.camel.processor.ResponseCustomerFinancialsProcessor;
import com.ofb.customers.camel.processor.ResponseCustomerIdentificationsProcessor;
import com.ofb.customers.camel.processor.ResponseCustomerQualificationsProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.support.AsyncProcessorHelper.process;

@Component
public class CustomersOrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        errorHandler();

//        onException(BadRequestException.class)
//                .continued(true)
//                .useOriginalMessage()
//                .end();

        from("direct:customersOrchestrationFlow")
                .routeId("customersOrchestrationFlow")
                .tracing()
                //.errorHandler()
                //.onException()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:customerResourcesRoute")
                .to("direct:customerDataRoute")
                .choice()
                    .when(exchangeProperty("requestType").isEqualTo("IDENTIFICATION_READ"))
                        .process(new ResponseCustomerIdentificationsProcessor())
                    .when(exchangeProperty("requestType").isEqualTo("QUALIFICATION_READ"))
                        .process(new ResponseCustomerQualificationsProcessor())
                    .when(exchangeProperty("requestType").isEqualTo("FINANCIAL_READ"))
                        .process(new ResponseCustomerFinancialsProcessor())
                    .end()
                .end();
    }
}
