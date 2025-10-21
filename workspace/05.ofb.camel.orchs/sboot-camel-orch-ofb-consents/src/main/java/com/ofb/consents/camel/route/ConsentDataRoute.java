package com.ofb.consents.camel.route;

import com.ofb.consents.camel.processor.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class ConsentDataRoute extends RouteBuilder {

    @Value("${app.paths.clients.ofb-consents}")
    private String OFB_CONSENTS_API_URL;

    @Override
    public void configure() {

        from("direct:consentDataRoute")
            .routeId("consentDataRoute")
            .tracing()
            //.errorHandler()
            //.onException()
            .setProperty("OFB_CONSENTS_API_URL", constant(OFB_CONSENTS_API_URL))
            .choice()
                .when(exchangeProperty("requestType").isEqualTo("GET_CONSENT"))
                    .process(new ConsentsGetConsentIdProcessor())
                .when(exchangeProperty("requestType").isEqualTo("GET_CONSENT_EXTENSIONS"))
                    .process(new ConsentsGetConsentExtensionsProcessor())
                .when(exchangeProperty("requestType").isEqualTo("POST_CONSENTS_EXTENDS"))
                    .process(new ConsentsPostConsentExtendsProcessor())
                .when(exchangeProperty("requestType").isEqualTo("POST_CONSENTS"))
                    .process(new ConsentsPostConsentProcessor())
                .when(exchangeProperty("requestType").isEqualTo("DELETE_CONSENTS"))
                    .process(new ConsentsDeleteConsentIdProcessor())
                .end()
            .end();



    }
}