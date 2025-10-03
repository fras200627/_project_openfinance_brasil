package com.ofb.resources.camel.processors;

import com.ofb.resources.server.api.model.Links;
import com.ofb.resources.server.api.model.MetaResponse;
import com.ofb.resources.server.api.model.ResponseResourceList;
import com.ofb.resources.server.api.model.ResponseResourceListDataInner;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrchestrationResponseProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        com.ofb.resources.client.resources.model.ResponseResourceList responseResources =
                (com.ofb.resources.client.resources.model.ResponseResourceList) exchange.getMessage().getBody();

        ResponseResourceList response = new ResponseResourceList();
        List<@Valid ResponseResourceListDataInner> data = new ArrayList<>();
        ResponseResourceListDataInner responseResourceListDataInner = new ResponseResourceListDataInner();

        for (com.ofb.resources.client.resources.model.ResponseResourceListDataInner reg : responseResources.getData()) {
            ResponseResourceListDataInner responseResourceListDataInner1 = ResponseResourceListDataInner.builder()
                    .resourceId(reg.getResourceId())
                    .status(ResponseResourceListDataInner.StatusEnum.fromValue(reg.getStatus().getValue()))
                    .type(ResponseResourceListDataInner.TypeEnum.fromValue(reg.getType().getValue()))
                    .build();
            data.add(responseResourceListDataInner1);
        }

        Links links = Links.builder()
                .first(responseResources.getLinks().getFirst())
                .last(responseResources.getLinks().getLast())
                .next(responseResources.getLinks().getNext())
                .prev(responseResources.getLinks().getPrev())
                .self(responseResources.getLinks().getSelf())
                .build();

        MetaResponse meta = MetaResponse.builder()
                .requestDateTime(responseResources.getMeta().getRequestDateTime())
                .totalPages(responseResources.getMeta().getTotalPages())
                .totalRecords(responseResources.getMeta().getTotalRecords())
                .build();

        response.setData(data);
        response.setLinks(links);
        response.setMeta(meta);

        exchange.getMessage().setBody(response);
    }
}
