package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.participants.handler.ClientsBusinessResourcesApi;
import com.ofb.authorization.client.participants.model.OAuth2ClientResponse;
import com.ofb.authorization.server.authorizations.model.Meta;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationValidate;
import com.ofb.authorization.server.authorizations.model.ValidateResult;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
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

    @Value("${app.parameters.consents.validate.check-if-consent-already-exists}")
    private boolean EXECUTE_CHECK_IF_CONSENTS_ALREADY_EXISTS;

    @Value("${app.parameters.execute-throw-immediately}")
    private boolean EXECUTE_THROW_IMMEDIATELY;

    @Value("${app.parameters.consents.organization}")
    private String CONSENTS_ORGANIZATION;

    @Value("${app.paths.clients.consents-api}")
    private String PATH_CONSENTS_API;

    @Value("${app.paths.clients.participants-api}")
    private String PATH_PARTICIPANTS_API;

    @Value("${app.paths.clients.customers-api}")
    private String PATH_CUSTOMERS_API;

    @Value("${app.paths.clients.resources-api}")
    private String PATH_RESOURCES_API;

    @Autowired private JwtDecoder jwtDecoder;
    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

    @Autowired private ClientsBusinessResourcesApi registeredClientsResourcesApi = null;

    public ResponseAuthorizationValidate businessEntityValidate(String accessToken) {

        OAuth2ClientResponse returnData = new OAuth2ClientResponse();
        String clientDocument = null;

        registeredClientsResourcesApi.getApiClient().setBasePath(PATH_PARTICIPANTS_API);
        registeredClientsResourcesApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        try {
            clientDocument = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("client.document").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (document of Participant) verify.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        try {
            returnData = registeredClientsResourcesApi.getFindByClientDocument(clientDocument);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (document of Participant) verify.")
                    .build());
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (document of Participant) verify.")
                    .build());
        }

//        returnData.getIsAccountExpired();
//        returnData.getIsAccountLocked();
//        returnData.getIsCredentialsExpired();
//        returnData.getIsEnabled();

        if (returnData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (Participant not exists) verify.")
                    .build());
        }
        if (returnData.getStatus() != null && !returnData.getStatus().equals("ACTIVE")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (Participant must be ACTIVE) verify.")
                    .build());
        }
        if (returnData.getSecurityScope() != null && !returnData.getSecurityScope().contains("client.ofb.read")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (Participant must be role client.ofb.read) verify.")
                    .build());
        }
        if (returnData.getSecurityScope() != null && !returnData.getSecurityScope().contains("client.ofb.write")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (Participant must be role client.ofb.write) verify.")
                    .build());;
        }
        if (returnData.getSecurityScope() != null && !returnData.getIsCredentialsExpired().equals("true")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization invalid (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INVALID_AUTHORIZATIONS.getValue())
                    .detail("An error occurred in clientDocument (Participant must be secret password not expired) verify.")
                    .build());
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
