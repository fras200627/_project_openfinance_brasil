package com.ofb.resources.camel.processors;

import com.google.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.resources.client.authorization.handler.AuthorizationValidateApi;
import com.ofb.resources.client.authorization.model.ResponseAuthorizationData;
import com.ofb.resources.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.resources.client.authorization.model.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @Slf4j
public class AuthorizationRouteProcessor implements Processor {

    private Gson gson = new Gson();
    private String consentId;
    private List<ResultErrorsErrorsInner> listResponseErrors ;
    private ResponseAuthorizationData     responseAuthorizationData;

    @Override
    public void process(Exchange exchange) throws Exception {

        AuthorizationValidateApi authorizationValidateApi = new AuthorizationValidateApi();

        authorizationValidateApi.getApiClient().setBasePath(exchange.getIn().getHeader("OFB_PATH_AUTHORIZATION").toString());
        authorizationValidateApi.getApiClient().setBearerToken(exchange.getIn().getBody(String.class).replace("Bearer ", ""));

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
            throw new BadRequestException(gson.toJson(listResponseErrors));
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
        exchange.getMessage().setBody(responseAuthorizationData);
    }

}
