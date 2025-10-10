package com.ofb.customers.camel.processor;

import com.google.gson.Gson;
import com.ofb.customers.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.customers.client.customers.handler.CustomersApi;
import com.ofb.customers.client.customers.model.ResponsePersonalCustomersIdentification;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component @Slf4j
public class CustomersPersonalIdentificationsProcessor implements Processor {

    private Gson gson = new Gson();
    private ResponsePersonalCustomersIdentification responseResourceList;
    private List<ResultErrorsErrorsInner> listResponseErrors ;

    @Override
    public void process(Exchange exchange) throws Exception {
        CustomersApi customersApi = new CustomersApi();
        listResponseErrors        = new ArrayList<>();
        responseResourceList      = new ResponsePersonalCustomersIdentification();

        customersApi.getApiClient().setBasePath(exchange.getProperty("OFB_CUSTOMERS_API_URL").toString());
        customersApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        customersApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());

        try {
            responseResourceList = customersApi.customersGetPersonalIdentifications(
                    exchange.getProperty("customerDocument").toString(),
                    null,
                    UUID.fromString(exchange.getProperty("x-fapi-interaction-id").toString()),
                    Integer.valueOf(exchange.getProperty("page").toString()),
                    Integer.valueOf(exchange.getProperty("pageSize").toString()));
        } catch (Exception e) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Customer Identifications Data request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        exchange.getMessage().setBody(responseResourceList);
    }
}
