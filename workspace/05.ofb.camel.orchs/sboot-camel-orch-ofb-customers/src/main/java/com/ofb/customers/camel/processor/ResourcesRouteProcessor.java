package com.ofb.customers.camel.processor;

import com.google.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.customers.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.customers.client.resources.handler.ResourcesApi;
import com.ofb.customers.client.resources.model.ResponseResourceList;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @Slf4j
public class ResourcesRouteProcessor implements Processor {

    private Gson gson = new Gson();
    private ResponseResourceList responseResourceList;
    private List<ResultErrorsErrorsInner> listResponseErrors ;

    @Override
    public void process(Exchange exchange) throws Exception {
        ResourcesApi resourcesApi = new ResourcesApi();
        listResponseErrors        = new ArrayList<>();
        responseResourceList      = new ResponseResourceList();

        resourcesApi.getApiClient().setBasePath(exchange.getProperty("OFB_RESOURCES_API_URL").toString());
        resourcesApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        resourcesApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());

        try {
            responseResourceList = resourcesApi.resourcesGetResources(1, 100);
        } catch (Exception e) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Authorization request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        exchange.getMessage().setBody(responseResourceList);
    }
}
