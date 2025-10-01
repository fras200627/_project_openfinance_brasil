package com.ofb.resources.service;

import com.google.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.resources.client.authorization.model.*;
import com.ofb.resources.client.authorization.handler.AuthorizationValidateApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorizationService {

    private final static String PERMISSION_REQUIRED = "RESOURCES_READ";

    @Value("${app.paths.clients.ofb-authorization}")
    private String OFB_PATH_AUTHORIZATION;

    private Gson gson = new Gson();
    private String consentId;
    private List<ResultErrorsErrorsInner>   listResponseErrors;
    private ResponseAuthorizationData responseAuthorizationData;

    @Autowired
    private AuthorizationValidateApi authorizationValidateApi;

    public ResponseAuthorizationData authorizationValidate(String accessToken) {

        authorizationValidateApi.getApiClient().setBasePath(OFB_PATH_AUTHORIZATION);
        authorizationValidateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        consentId                   = "";
        listResponseErrors          = new ArrayList<>();
        responseAuthorizationData   = new ResponseAuthorizationData();

        /// Authorize AccessToken
        try {
            responseAuthorizationData = authorizationValidateApi.authorizationValidate();
            consentId = responseAuthorizationData.getData().getResultStatus().getConsentId();
        } catch (Exception e) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Authorization request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            return ResponseAuthorizationData.builder()
                    .data(ResponseResultData.builder()
                            .resultStatus(ResultStatus.builder()
                                    .status(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)
                                    .build())
                            .resultErrors(ResultErrors.builder()
                                    .errors(listResponseErrors)
                                    .build())
                            .build()
                    )
                    .meta(Meta.builder()
                            .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z")
                            .build())
                    .build();
        }

        return responseAuthorizationData;
    }

}
