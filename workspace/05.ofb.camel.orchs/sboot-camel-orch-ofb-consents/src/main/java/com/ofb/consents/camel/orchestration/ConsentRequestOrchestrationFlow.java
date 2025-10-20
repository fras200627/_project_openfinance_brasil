package com.ofb.consents.camel.orchestration;

import com.ofb.consents.camel.processor.ExchangeSettingsProcessor;
import com.ofb.consents.camel.processor.ResponseConsentGetConsentExtensionsProcessor;
import com.ofb.consents.camel.processor.ResponseConsentGetConsentIdProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsentRequestOrchestrationFlow extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Consents OpenFinance Methods
        // ------------------------------------
        // consentsPostConsents
        // consentsPostConsentsConsentIdExtends
        //
        // consentsDeleteConsentsConsentId
        //
        // consentsGetConsentsConsentId
        // consentsGetConsentsConsentIdExtensions

        from("direct:consentRequestOrchestrationFlow")
                .routeId("consentRequestOrchestrationFlow")
                .tracing()
                .process(new ExchangeSettingsProcessor())
//                .to("direct:authorizationRoute")
                .to("direct:consentDataRoute")
                .choice()
                    .when(exchangeProperty("requestType").isEqualTo("GET_CONSENT"))
                        .process(new ResponseConsentGetConsentIdProcessor())
                    .when(exchangeProperty("requestType").isEqualTo("GET_CONSENT_EXTENSIONS"))
                        .process(new ResponseConsentGetConsentExtensionsProcessor())
                    .otherwise()
                        .log("mapping not applicable")
                    .end()
                .end();
    }
}
