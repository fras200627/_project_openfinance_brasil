package com.ofb.resources.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

public class CamelTest extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:/temp/apache_camel/entrada?delete=true")
            .routeId("integracao-arquivo")
            .log("Processando o arquivo: ${file:name}")
            .to("file:/temp/apache_camel/saida?fileName=${date:now:HHmmss}_${file:name}")
            .end();
    }
}
