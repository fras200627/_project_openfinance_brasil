package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.server.authorizations.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
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

    private String consentId;
    private String clientDocument;
    private String customerDocument;
    private String scope;
    private String expiration;

    public ResponseAuthorizationData accessTokenClaimsValidate(String accessToken) {

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
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is invalid or cannot be executed")
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
                            .consentId(consentId)
                            .loggedUserDocument(customerDocument)
                            .loggedUserDocumentRel("CPF")
                            .businessEntityDocument(clientDocument)
                            .businessEntityDocumentRel("CNPJ")
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is valid")
                            .consent("consentId is informed")
                            .loggedUser("LoggedUser is informed")
                            .businessEntity("BusinessEntity is informed")
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
        this.accessTokenConsentIdValidate(accessToken);
        this.accessTokenClientDocumentValidate(accessToken);
        this.accessTokenCustomerDocumentValidate(accessToken);
        this.accessTokenPermissionsValidate(accessToken);
        this.accessTokenExpirationDateTimeValidate(accessToken);

        return listResponseErrors;
    }

    public void accessTokenConsentIdValidate(String accessToken) {
        consentId = "";
        try {
            consentId = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("ConsentId is invalid or not informed.")
                    .build());
        }
    }

    public void accessTokenClientDocumentValidate(String accessToken) {
        clientDocument = "";
        try {
            clientDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("client.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("Document of Participant is invalid or not informed.")
                    .build());
        }
    }

    public void accessTokenCustomerDocumentValidate(String accessToken) {
        customerDocument = "";
        try {
            customerDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("customer.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("Document of LoggedUser is invalid or not informed.")
                    .build());
        }
    }

    public void accessTokenPermissionsValidate(String accessToken) {
        scope = "";
        try {
            scope = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("permissions").toString();
            if (!scope.contains("RESOURCES_READ") || !scope.contains("CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ")) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Authorization was denied")
                        .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                        .detail("Authorization not exists a consent permissions RESOURCES_READ and CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ " +
                                "of LoggedUser verify.")
                        .build());
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("Scope consent permissions is invalid or not informed.")
                    .build());
        }
    }

    public void accessTokenExpirationDateTimeValidate(String accessToken) {
        expiration = "";
        try {
            expiration = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("exp").toString();
            if (!OffsetDateTime.parse(expiration).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Authorization was denied)")
                        .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                        .detail("Consent ExpirationDateTime is expired.")
                        .build());
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization was denied")
                    .code(ResponseOFBCodesEnum.CodeEnum.AUTHORIZATION_DENIED.getValue())
                    .detail("Consent ExpirationDateTime is invalid or not informed.")
                    .build());
        }
    }

}
