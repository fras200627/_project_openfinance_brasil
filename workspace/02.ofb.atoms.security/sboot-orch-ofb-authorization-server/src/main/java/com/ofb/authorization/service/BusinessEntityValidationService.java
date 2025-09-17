package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.participants.handler.ClientsBusinessResourcesApi;
import com.ofb.authorization.client.participants.model.OAuth2ClientResponse;
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
 * Passo 03: Verificação ds BusinessEntity (Participante indicado no client.document):
 *     O Participante existe na base de participantes ?
 *     O Participante deve estar ATIVO e DESBLOQUEADO (status: ACTIVE, LOCKED, INACTIVE)
 *     O Participante deve ter data de expiração válida. (client_secret_expires_at)
 *     O Participante possui as roles necessarias: client.ofb.read e client.ofb.write?
 */
@Service @Slf4j
public class BusinessEntityValidationService {

    @Value("${app.paths.clients.participants-api}")
    private String PATH_PARTICIPANTS_API;

    @Autowired private JwtDecoder jwtDecoder;
    @Autowired private ClientsBusinessResourcesApi registeredClientsResourcesApi;

    //private Gson gson = new Gson();
    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors;
    private ValidateErrorResponse             validateErrorResponse;
    private OAuth2ClientResponse              returnData;
    private String                            documentNumber;

    public ResponseAuthorizationData businessEntityValidate(String accessToken) {

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
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is invalid")
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
//                            .consentId(consentId)
//                            .loggedUserDocument(customerDocument)
//                            .loggedUserDocumentRel("CPF")
                            .businessEntityDocument(documentNumber)
                            .businessEntityDocumentRel("CNPJ")
                            .build())
                    .resultValidation(ResultValidation.builder()
                            .accessToken("AccessToken is valid")
//                            .consent("consentId is informed")
//                            .loggedUser("LoggedUser is informed ")
                            .businessEntity("BusinessEntity informed is valid and Authorized")
                            .build())
//                    .resultErrors(null)
                    .build();

            responseAuthorizationData = ResponseAuthorizationData.builder()
                    .data(data)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        }

        return responseAuthorizationData;
    }

    public List<ResponseErrorsInnerTemplate> executeValidate(String accessToken) {

        returnData          = new OAuth2ClientResponse();
        documentNumber      = null;
        listResponseErrors  = new ArrayList<>();
        validateErrorResponse = new ValidateErrorResponse();

        registeredClientsResourcesApi.getApiClient().setBasePath(PATH_PARTICIPANTS_API);
        registeredClientsResourcesApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            documentNumber = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("client.document").toString();
            returnData = registeredClientsResourcesApi.getFindByClientDocument(documentNumber);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An Internal error occurred in BusinessEntity validation: [" + e.getMessage() + "].")
                    .build());
            return listResponseErrors;
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant verification: Participant not exists.")
                    .build());
            return listResponseErrors;
        }
        if (!returnData.getStatus().equals("ACTIVE")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant verification: Participant must be ACTIVE.")
                    .build());
        }
        if (!returnData.getSecurityScope().contains("client.ofb.read")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant varification: Participant must be role client.ofb.read.")
                    .build());
        }
        if (!returnData.getSecurityScope().contains("client.ofb.write")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Partipant verification: Participant must be role client.ofb.write.")
                    .build());;
        }
        if (returnData.getIsCredentialsExpired().equals("true")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant verification: Participant must be secret password not expired.")
                    .build());
        }

        return listResponseErrors;
    }

}
