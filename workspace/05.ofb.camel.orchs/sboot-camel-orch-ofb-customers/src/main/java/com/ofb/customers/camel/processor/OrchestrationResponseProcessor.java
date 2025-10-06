package com.ofb.customers.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class OrchestrationResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
//        com.ofb.resources.client.resources.model.ResponseResourceList responseResources =
//                (com.ofb.resources.client.resources.model.ResponseResourceList) exchange.getMessage().getBody();
//
//        ResponseResourceList response = ResponseResourceList.builder()
//                .data(ResourcesResponseMapper.INSTANCE.responseListToResponseResourceListDataInner(responseResources.getData()))
//                .links(ResourcesResponseMapper.INSTANCE.responseLinksToResponseResourceLinks(responseResources.getLinks()))
//                .meta(ResourcesResponseMapper.INSTANCE.responseMetaToResponseMetaResource(responseResources.getMeta()))
//                .build();
//
//        exchange.getMessage().setBody(response);
    }

}
