package com.ofb.resources.controller;

import com.ofb.resources.client.authorization.model.ResponseAuthorizationData;
import com.ofb.resources.server.api.handler.ResourcesApiDelegate;
import com.ofb.resources.server.api.model.Links;
import com.ofb.resources.server.api.model.MetaResponse;
import com.ofb.resources.server.api.model.ResponseResourceList;
import com.ofb.resources.server.api.model.ResponseResourceListDataInner;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityScheme(type    = SecuritySchemeType.HTTP,
                name    = "bearerAuth",
                scheme  = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class ResourcesAPIControllerImpl implements ResourcesApiDelegate {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public ResponseEntity<ResponseResourceList> resourcesGetResources(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize) {
        ResponseAuthorizationData responseAuthorization = producerTemplate.requestBody("direct:authorizationRoute", authorization, ResponseAuthorizationData.class);
        com.ofb.resources.client.resources.model.ResponseResourceList responseResources = producerTemplate.requestBody("direct:resourcesRoute", authorization,
                com.ofb.resources.client.resources.model.ResponseResourceList.class);

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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
