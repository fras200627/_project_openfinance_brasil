package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.customers.handler.CustomersApi;
import com.ofb.authorization.client.customers.model.ResponsePersonalCustomerData;
import com.ofb.authorization.server.authorizations.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
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

    private String customerDocument = null;
    private ResponsePersonalCustomerData returnData;

    public ResponseAuthorizationData loggedUserValidate(String accessToken) {
        List<ResultErrorsErrorsInner> errors = new ArrayList<>();
        ResponseAuthorizationData responseAuthorizationData =  new ResponseAuthorizationData();

        listResponseErrors = this.executeValidate(accessToken);


        if (!listResponseErrors.isEmpty()) {
            for (ResponseErrorsInnerTemplate reg : listResponseErrors) {
                errors.add(ResultErrorsErrorsInner.builder()
                        .title(reg.getTitle())
                        .code(reg.getCode())
                        .detail(reg.getDetail())
                        .build());
            }
            ResponseResultData data = ResponseResultData.builder()
                    .resultStatus(ResultStatus.builder()
                            .status(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)
                            .loggedUserDocument(customerDocument == null ? "not verified" : customerDocument)
                            .loggedUserDocumentRel(customerDocument == null ? null : "CPF")
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("Authorization was denied")
                            .loggedUser("LoggedUser is invalid or cannot be executed")
                            .build())
                    .resultErrors(ResultErrors.builder()
                            .errors(errors)
                            .build())
                    .build();

            responseAuthorizationData = ResponseAuthorizationData.builder()
                    .data(data)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        } else {
            ResponseResultData data = ResponseResultData.builder()
                    .resultStatus(ResultStatus.builder()
                            .status(ResultStatus.StatusEnum.AUTHORIZATION_GRANTED)
                            .loggedUserDocument(customerDocument)
                            .loggedUserDocumentRel("CPF")
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is valid")
                            .loggedUser("The LoggedUser informed is valid and is Authorized")
                            .build())
                    .build();

            responseAuthorizationData = ResponseAuthorizationData.builder()
                    .data(data)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        }

        return responseAuthorizationData;
    }

    public List<ResponseErrorsInnerTemplate> executeValidate(String accessToken) {
        listResponseErrors = new ArrayList<>();
        customerDocument = null;
        returnData = new ResponsePersonalCustomerData();

        customersApi.getApiClient().setBasePath(PATH_CUSTOMERS_API);
        customersApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            customerDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("customer.document").toString();
            returnData = customersApi.customerIdentificationSummary(customerDocument);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Error executing Authorization")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            return listResponseErrors;
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("Customer (LoggedUser) not exists.")
                    .build());
            return listResponseErrors;
        }

        if (!returnData.getData().get(0).getPersonalStatus().equals("ATIVO")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("Customer (LoggedUser) status is [" +
                            returnData.getData().get(0).getPersonalStatus() + "].")
                    .build());
        }

        return listResponseErrors;
    }

}
