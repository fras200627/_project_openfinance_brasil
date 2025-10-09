package com.ofb.customers.camel.processor;

import com.google.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.customers.client.authorization.handler.AuthorizationValidateApi;
import com.ofb.customers.client.authorization.model.ResponseAuthorizationData;
import com.ofb.customers.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.customers.client.authorization.model.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @Slf4j
public class AuthorizationProcessor implements Processor {

    private Gson gson = new Gson();
    private String consentId;
    private List<ResultErrorsErrorsInner> listResponseErrors ;
    private ResponseAuthorizationData responseAuthorizationData;

    @Override
    public void process(Exchange exchange) throws Exception {
        AuthorizationValidateApi authorizationValidateApi = new AuthorizationValidateApi();

        authorizationValidateApi.getApiClient().setBasePath(exchange.getProperty("AUTHORIZATION_API_URL").toString());
        authorizationValidateApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        authorizationValidateApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());

        listResponseErrors    = new ArrayList<>();
        responseAuthorizationData = new ResponseAuthorizationData();

        try {
            responseAuthorizationData = authorizationValidateApi.authorizationValidate();
            consentId = responseAuthorizationData.getData().getResultStatus().getConsentId();
        } catch (Exception e) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Authorization request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            for (ResultErrorsErrorsInner reg : responseAuthorizationData.getData().getResultErrors().getErrors()) {
                listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                        .title(reg.getTitle())
                        .code(reg.getCode())
                        .detail(reg.getDetail())
                        .build());
            }
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Set a consentId property in exchange properties
        exchange.setProperty("consentId", responseAuthorizationData.getData().getResultValidation().getConsent());
        exchange.setProperty("customerDocument", responseAuthorizationData.getData().getResultValidation().getLoggedUser());
        ///

        exchange.getMessage().setBody(responseAuthorizationData);
    }

}
