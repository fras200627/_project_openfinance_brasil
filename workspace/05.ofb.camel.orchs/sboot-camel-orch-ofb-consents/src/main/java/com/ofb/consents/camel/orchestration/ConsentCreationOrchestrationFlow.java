package com.ofb.consents.camel.orchestration;

import com.ofb.consents.camel.processor.ExchangeSettingsProcessor;
import com.ofb.consents.camel.processor.OrchestrationResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsentCreationOrchestrationFlow extends RouteBuilder {

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

        from("direct:consentCreationOrchestrationFlow")
                .routeId("consentCreationOrchestrationFlow")
                .tracing()
                .process(new ExchangeSettingsProcessor())
                .to("direct:authorizationRoute")
                .to("direct:resourcesRoute")
                .process(new OrchestrationResponseProcessor());
    }
}
