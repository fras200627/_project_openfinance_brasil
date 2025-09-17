package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.customers.handler.CustomersApi;
import com.ofb.authorization.client.customers.model.ResponsePersonalCustomerData;
import com.ofb.authorization.server.authorizations.model.Meta;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationData;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.ValidateErrorResponse;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Passo 04: Verificação do LoggedUser (customer.document)
 *     Customer existe na base de customers ?
 *     Customer está ativo e desbloqueado?
 */
@Service @Slf4j
public class LoggedUserValidationService {

    @Value("${app.paths.clients.customers-api}")
    private String PATH_CUSTOMERS_API;

    @Autowired private JwtDecoder jwtDecoder;
    @Autowired private CustomersApi customersApi;

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors;
    private ValidateErrorResponse validateErrorResponse = new ValidateErrorResponse();

    public ResponseAuthorizationData loggedUserValidate(String accessToken) {
        listResponseErrors = this.executeValidate(accessToken);
        if (listResponseErrors.isEmpty()) {
            return ResponseAuthorizationData.builder()
                    .data(null)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        } else {
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
    }

    public List<ResponseErrorsInnerTemplate> executeValidate(String accessToken) {
        listResponseErrors = new ArrayList<>();
        String customerDocument = null;
        ResponsePersonalCustomerData returnData = null;

        customersApi.getApiClient().setBasePath(PATH_CUSTOMERS_API);
        customersApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            customerDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("customer.document").toString();
            returnData = customersApi.customerIdentificationSummary(customerDocument);
        } catch (Exception e) {
            listResponseErrors.addAll(validateErrorResponse.buildErrorResponse(e, true));
            return listResponseErrors;
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Customer validation: Customer not exists.")
                    .build());
            return listResponseErrors;
        }

        if (!returnData.getData().get(0).getPersonalStatus().equals("ATIVO")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Customer validation: Customer status is [" +
                            returnData.getData().get(0).getPersonalStatus() + "].")
                    .build());
        }

        return listResponseErrors;
    }

}
