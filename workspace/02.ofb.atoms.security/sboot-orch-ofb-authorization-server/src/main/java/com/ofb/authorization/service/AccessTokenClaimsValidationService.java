package com.ofb.authorization.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.authorization.server.authorizations.model.Meta;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationData;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
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
public class AccessTokenClaimsValidationService {

    @Autowired private BusinessEntityValidationService businessEntityValidationService;
    @Autowired private LoggedUserValidationService loggedUserValidationService;
    @Autowired private ConsentValidationService consentValidationService;
    @Autowired private JwtDecoder jwtDecoder;

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors;

    public ResponseAuthorizationData accessTokenClaimsValidate(String accessToken) {

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
        this.accessTokenConsentIdValidate(accessToken);
        this.accessTokenClientDocumentValidate(accessToken);
        this.accessTokenCustomerDocumentValidate(accessToken);
        this.accessTokenPermissionsValidate(accessToken);
        this.accessTokenExpirationDateTimeValidate(accessToken);

        return listResponseErrors;
    }

    public void accessTokenConsentIdValidate(String accessToken) {
        String consentId;
        try {
            consentId = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in consentId verify.")
                    .build());
        }
    }

    public void accessTokenClientDocumentValidate(String accessToken) {
        String clientDocument;
        try {
            clientDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("client.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (document of Participant) verify.")
                    .build());
        }
    }

    public void accessTokenCustomerDocumentValidate(String accessToken) {
        String customerDocument;
        try {
            customerDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("customer.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in customerDocument (document of LoggedUser) verify.")
                    .build());
        }
    }

    public void accessTokenPermissionsValidate(String accessToken) {
        String scope;
        try {
            scope = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("permissions").toString();
            if (!scope.contains("RESOURCES_READ") || !scope.contains("CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ")) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Authorization invalid (in getClaim AccessToken")
                        .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                        .detail("Authorization not exists a consent permissions RESOURCES_READ and CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ " +
                                "of LoggedUser verify.")
                        .build());
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in scope (consent permissions) of LoggedUser verify.")
                    .build());
        }
    }

    public void accessTokenExpirationDateTimeValidate(String accessToken) {
        String expiration;
        try {
            expiration = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("exp").toString();
            if (!OffsetDateTime.parse(expiration).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Authorization invalid (in getClaim AccessToken)")
                        .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                        .detail("The consent ExpirationDateTime is invalid.")
                        .build());
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in consent Expiration DateTime verify.")
                    .build());
        }
    }

}
