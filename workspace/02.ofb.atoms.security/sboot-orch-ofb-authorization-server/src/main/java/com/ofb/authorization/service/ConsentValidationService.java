package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.consents.handler.ConsentsApi;
import com.ofb.authorization.client.consents.model.ResponseConsentRead;
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
import java.util.*;

/**
 * Passo 05: Verificação do Consentimento (ofb.consent.id)
 *     Consentimento existe na base de consentimentos?
 *     Consentimento está com status AUTHORISED?
 *     Consentimento expirou ?
 */
@Service @Slf4j
public class ConsentValidationService {

    @Value("${app.paths.clients.consents-api}")
    private String PATH_CONSENTS_API;

    @Autowired private JwtDecoder jwtDecoder;
    @Autowired private ConsentsApi consentsApi;

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors;
    private ValidateErrorResponse validateErrorResponse = new ValidateErrorResponse();

    public ResponseAuthorizationData consentValidate(String accessToken) {
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
        String consentId = "";
        ResponseConsentRead returnData = null;

        consentsApi.getApiClient().setBasePath(PATH_CONSENTS_API);
        consentsApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            consentId = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
            returnData = consentsApi.consentsGetConsentsConsentId(consentId,
                        UUID.randomUUID(),
                        null, null, null);
        } catch (Exception e) {
            listResponseErrors.addAll(validateErrorResponse.buildErrorResponse(e, true));
            return listResponseErrors;
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization validate error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred while checking the Authorization validate: [Consent not exists].")
                    .build());
            return listResponseErrors;
        }
        if (!returnData.getData().getStatus().getValue().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization validate error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred while checking the Authorization validate: [Consent must by AUTHORISED].")
                    .build());
        }
        if (OffsetDateTime.parse(returnData.getData().getExpirationDateTime()).isBefore(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization validate error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred while checking the Authorization validate: [Consent must bu Expired].")
                    .build());
        }

        return listResponseErrors;
    }

}
