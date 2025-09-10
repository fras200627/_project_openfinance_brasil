package com.ofb.authorization.service;

import com.ofb.authorization.client.consents.handler.ConsentsApi;
import com.ofb.authorization.client.consents.model.ResponseConsentRead;
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
 * Passo 05: Verificação do Consentimento (ofb.consent.id)
 *     Consentimento existe na base de consentimentos?
 *     Consentimento está com status AUTHORISED?
 *     Consentimento expirou ?
 */
@Service @Slf4j
public class ConsentValidationService {

    @Autowired
    private HttpServletRequest request;

    @Value("${app.paths.clients.consents-api}")
    private String PATH_CONSENTS_API;

    @Autowired
    private ConsentsApi consentsApi;

    private List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

    public void consentValidate(Object objectData, Object referenceId) {

        String consentId = "";
        ResponseConsentRead returnData = null;
        consentsApi.getApiClient().setBasePath(PATH_CONSENTS_API);
        consentsApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));

        try {
            returnData = consentsApi.consentsGetConsentsConsentId(
                                    consentId, request.getHeader("Authorization"),
                                    UUID.randomUUID(),
                                    null, null, null);
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

        returnData.getData().getStatus();
        returnData.getData().getExpirationDateTime();
    }

}
