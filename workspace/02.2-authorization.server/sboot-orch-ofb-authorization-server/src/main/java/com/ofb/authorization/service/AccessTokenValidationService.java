package com.ofb.authorization.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Process 02: Validação do AccessToken (extract claims)
 *     AcessToken Expirou ? *
 *     Informa o consentId ?
 *     Informa o client.document ?
 *     Informa o customer.document ?
 *     Possui as roles minimas:
 *         resurces_read
 *         customers_read
 *
 *     * O access token gerado tem validade baseada no pedido do consentimento AUTHORISED:
 *         - Validade Trimestral (data hoje + 03 meses e horário 23:59:59)
 *         - Validade Semestral (data hoje + 06 meses e horário 23:59:59)
 *         - Validade Anual (data hoje + 12 meses e horário 23:59:59)
 *         - Validade Indefinida (fica definido para expiração em: 2099-21-31T23:59:59Z)
 */
@Service
public class AccessTokenValidationService {

    @Autowired
    private JwtDecoder jwtDecoder;

    public void accessTokenValidate(String authorization) {

        Gson gson = new Gson();
        String consentId;
        String clientDocument;
        String customerDocument;
        String scope;
        String expiration;

        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        /// Extract claim ofb.consent.id value
        try {
            consentId = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
        }

        /// Extract claim client.document value
        try {
            clientDocument = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("client.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
        }

        /// Extract claim customer.document value
        try {
            customerDocument = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("customer.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
        }

        /// Extract claim scope values
        try {
            scope = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("scope").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
        }

        /// Extract claim expiration value
        try {
            expiration = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("expiration").toString();
//            if (!OffsetDateTime.parse(responseConsentRead.getData().getExpirationDateTime()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
//                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
//                        .title("Get Resources request error")
//                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
//                        .detail("Unable to request RESOURCES information. The consentId (" + consentId + ") provided in " +
//                                "the AccessToken has an ExpirationDateTime " +
//                                "(" +
//                                responseConsentRead.getData().getExpirationDateTime()
//                                + ") of 'expired'. ")
//                        .build());
//            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
        }

        if (!listResponseErrors.isEmpty()) {
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
    }

}
