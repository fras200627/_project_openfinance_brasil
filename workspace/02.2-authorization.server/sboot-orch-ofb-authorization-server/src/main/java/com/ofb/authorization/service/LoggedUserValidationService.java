package com.ofb.authorization.service;

import com.ofb.authorization.client.customers.handler.CustomersApi;
import com.ofb.authorization.client.customers.model.ResponsePersonalCustomersIdentification;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CustomersApi customersApi;

    private List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

    public void loggedUserValidate(Object objectData, Object referenceId) {

        ResponsePersonalCustomersIdentification returnData = null;
        customersApi.getApiClient().setBasePath(PATH_CUSTOMERS_API);
        customersApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));

        try {
            returnData = customersApi.customersGetPersonalIdentifications(
                                                      request.getHeader("Authorization"),
                                                      UUID.randomUUID(),
                                                      null, null, null,
                                                      1, 10,
                                                      null);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Registered Client does not exist in the OFB registered client database")
                    .build());
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error (API not active) occurred while checking the requested Registered Client")
                    .build());
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Business Entity")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Registered Client  does not exist in the OFB registered client database")
                    .build());
            return;
        }

        if (returnData.getData().get(0).getPersonalStatus().equals("ATIVO")) {

        } else {

        }

    }

}
