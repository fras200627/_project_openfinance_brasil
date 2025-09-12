package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.participants.handler.ClientsBusinessResourcesApi;
import com.ofb.authorization.client.participants.model.OAuth2ClientResponse;
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

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

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

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors;

    public ResponseAuthorizationValidate businessEntityValidate(String accessToken) {

        OAuth2ClientResponse returnData = new OAuth2ClientResponse();
        String clientDocument = null;
        listResponseErrors = new ArrayList<>();

        registeredClientsResourcesApi.getApiClient().setBasePath(PATH_PARTICIPANTS_API);
        registeredClientsResourcesApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            clientDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("client.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in client.document (document of Participant) verify.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        try {
            returnData = registeredClientsResourcesApi.getFindByClientDocument(clientDocument);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred while accessing the Participants API. "  +
                            "Error message: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred while accessing the Participants API. " +
                            "Error message: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant verification: Participant not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
        if (!returnData.getStatus().equals("ACTIVE")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant verification: Participant must be ACTIVE.")
                    .build());
        }
        if (returnData.getSecurityScope() != null && !returnData.getSecurityScope().contains("client.ofb.read")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in Participant varification: Participant must be role client.ofb.read.")
                    .build());
        }
        if (returnData.getSecurityScope() != null && !returnData.getSecurityScope().contains("client.ofb.write")) {
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

        if (listResponseErrors.isEmpty()) {
            return ResponseAuthorizationValidate.builder()
                    .data(ValidateResult.builder()
                            .status("BusinessEntity/Partipant (in Authorization Service) successfully validate.").build())
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        } else {
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
    }

}
