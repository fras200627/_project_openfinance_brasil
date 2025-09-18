package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.consents.handler.ConsentsApi;
import com.ofb.authorization.client.consents.model.ResponseConsentRead;
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

    private String consentId = null;
    private ResponseConsentRead returnData;

    public ResponseAuthorizationData consentValidate(String accessToken) {
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
                            .status(ResultStatus.StatusEnum.ACCESS_TOKEN_UNAUTHORIZED)
                            .consentId(consentId == null ? "not verified" : consentId)
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is invalid")
                            .consent("An error occurred while the Consent validation")
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
                            .status(ResultStatus.StatusEnum.ACCESS_TOKEN_AUTHORIZED)
                            .consentId(consentId)
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is valid")
                            .consent("consentId informed is valid and Authorized")
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
        consentId = "";
        returnData = new ResponseConsentRead();

        consentsApi.getApiClient().setBasePath(PATH_CONSENTS_API);
        consentsApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            consentId = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
            returnData = consentsApi.consentsGetConsentsConsentId(consentId,
                        UUID.randomUUID(),
                        null, null, null);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An Internal error occurred in Consent validation: [" + e.getMessage() + "].")
                    .build());
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
