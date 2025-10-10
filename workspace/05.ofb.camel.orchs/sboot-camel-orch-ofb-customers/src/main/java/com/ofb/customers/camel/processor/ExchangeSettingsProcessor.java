package com.ofb.customers.camel.processor;

import com.ofb.customers.camel.model.RequestOrchParams;
import com.ofb.customers.camel.model.RequestParam;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExchangeSettingsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        RequestOrchParams requestOrchParams = (RequestOrchParams) exchange.getMessage().getBody();
        for (RequestParam param : requestOrchParams.getRequestOrchParams()) {
            if (param.getParamName().equals("Authorization")) {
                exchange.setProperty("Authorization", (String) param.getParamValue());
            }
            if (param.getParamName().equals("x-fapi-interaction-id")) {
                exchange.setProperty("x-fapi-interaction-id", ((UUID) param.getParamValue()).toString());
            }
            if (param.getParamName().equals("consentId")) {
                exchange.setProperty("consentId", param.getParamValue().toString());
            }
            if (param.getParamName().equals("page")) {
                exchange.setProperty("page", param.getParamValue().toString());
            }
            if (param.getParamName().equals("pageSize")) {
                exchange.setProperty("pageSize", param.getParamValue().toString());
            }
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("IDENTIFICATION_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("QUALIFICATION_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "CUSTOMERS_PERSONAL_ADITTIONALINFO_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("FINANCIAL_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "CUSTOMERS_PERSONAL_ADITTIONALINFO_READ");
            }
        }
    }
}
