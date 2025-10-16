package com.ofb.accounts.camel.processor;

import com.ofb.accounts.camel.model.RequestOrchParams;
import com.ofb.accounts.camel.model.RequestParam;
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
            if (param.getParamName().equals("accountId")) {
                exchange.setProperty("accountId", param.getParamValue().toString());
            }
            if (param.getParamName().equals("accountType")) {
                exchange.setProperty("accountType", param.getParamValue().toString());
            }
            if (param.getParamName().equals("page")) {
                exchange.setProperty("page", param.getParamValue().toString());
            }
            if (param.getParamName().equals("pageSize")) {
                exchange.setProperty("pageSize", param.getParamValue().toString());
            }
            if (param.getParamName().equals("fromBookingDate")) {
                exchange.setProperty("fromBookingDate", param.getParamValue() == null ? "" : param.getParamValue().toString());
            }
            if (param.getParamName().equals("toBookingDate")) {
                exchange.setProperty("toBookingDate", param.getParamValue() == null ? "" : param.getParamValue().toString());
            }
            if (param.getParamName().equals("creditDebitIndicator")) {
                exchange.setProperty("creditDebitIndicator", param.getParamValue() == null ? "" : param.getParamValue().toString());
            }
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("GET_ACCOUNTS_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "ACCOUNTS_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("GET_ACCOUNT_ID_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "ACCOUNTS_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("GET_ACCOUNT_ID_BALANCES_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "ACCOUNTS_BALANCES_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("GET_ACCOUNT_ID_OVERDRAFT_LIMITS_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "ACCOUNTS_OVERDRAFT_LIMITS_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("GET_ACCOUNT_ID_TRANSACTIONS_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "ACCOUNTS_TRANSACTIONS_READ");
            } else
            if (param.getParamName().equals("requestType") && param.getParamValue().equals("GET_ACCOUNT_ID_TRANSACTIONS_CURRENT_READ")) {
                exchange.setProperty("requestType", param.getParamValue().toString());
                exchange.setProperty("PERMISSION_REQUIRED", "ACCOUNTS_TRANSACTIONS_READ");
            }
        }
    }
}
