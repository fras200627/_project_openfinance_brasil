package com.ofb.consents.camel.processor;

import com.google.gson.Gson;
import com.ofb.consents.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.consents.client.consents.handler.ConsentsApi;
import com.ofb.consents.client.consents.model.ResponseConsent;
import com.ofb.consents.client.consents.model.ResponseConsentRead;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ConsentsDeleteConsentIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Gson gson = new Gson();
        List<ResultErrorsErrorsInner> listResponseErrors = new ArrayList<>();

        ///
        ConsentsApi consentsApi = new ConsentsApi();
        consentsApi.getApiClient().setBasePath(exchange.getProperty("OFB_CONSENTS_API_URL").toString());
        consentsApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        consentsApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());

        ///
        try {
            consentsApi.consentsDeleteConsentsConsentId(exchange.getProperty("consentId").toString(),
                    UUID.fromString(exchange.getProperty("x-fapi-interaction-id").toString()));

            exchange.getMessage().setBody("");
        } catch (Exception e) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Delete Consent request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
    }

}
