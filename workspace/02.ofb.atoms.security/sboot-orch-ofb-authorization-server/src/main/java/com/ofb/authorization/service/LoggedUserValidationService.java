package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.customers.handler.CustomersApi;
import com.ofb.authorization.client.customers.model.ResponsePersonalCustomerData;
import com.ofb.authorization.client.customers.model.ResponsePersonalCustomersIdentification;
import com.ofb.authorization.server.authorizations.model.Meta;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationValidate;
import com.ofb.authorization.server.authorizations.model.ValidateResult;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    public ResponseAuthorizationValidate loggedUserValidate(String accessToken) {

        listResponseErrors = new ArrayList<>();
        String customerDocument = null;
        ResponsePersonalCustomerData returnData = null;

        customersApi.getApiClient().setBasePath(PATH_CUSTOMERS_API);
        customersApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            customerDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("customer.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in customer.document (document of Customer) verify.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        try {
            returnData = customersApi.customerIdentificationSummary(accessToken.replace("Bearer ", ""), customerDocument);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred while accessing the Customers API. "  +
                            "Error message: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred while accessing the Customers API. " +
                            "Error message: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in customer.document (Cusomer not exists) verify.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (listResponseErrors.isEmpty()) {
            return ResponseAuthorizationValidate.builder()
                    .data(ValidateResult.builder()
                            .status("BusinessEntity (in Authorization Service) successfully validate.").build())
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        } else {
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

    }

}
