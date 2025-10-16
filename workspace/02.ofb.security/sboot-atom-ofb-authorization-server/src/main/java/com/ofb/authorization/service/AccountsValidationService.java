package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.resources.handler.ResourcesApi;
import com.ofb.authorization.client.resources.model.ConsentIdentification;
import com.ofb.authorization.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.authorization.client.resources.model.ResourcesAccountPermissions;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationData;
import com.ofb.authorization.server.authorizations.model.ResultErrorsErrorsInner;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class AccountsValidationService {

    private final static String PERMISSION_REQUIRED = "ACCOUNTS_OVERDRAFT_LIMITS_READ";

    @Value("${app.paths.clients.ofb-resources-api}")
    private String OFB_PATH_RESOURCES;

    @Autowired
    private ResourcesApi resourcesApi;

    private Gson gson = new Gson();
    @Autowired private JwtDecoder jwtDecoder;
    private String consentId;
    private List<ResponseErrorsInnerTemplate> listResponseErrors;
    private ResourcesAccountPermissions responseAccountPermissions;
    private ConsentIdentification consentIdentification;
    private List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList;

    public ResponseAuthorizationData accountsValidate(String accessToken) {

        List<ResultErrorsErrorsInner> errors = new ArrayList<>();
        ResponseAuthorizationData responseAuthorizationData =  new ResponseAuthorizationData();

        resourcesApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
        resourcesApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        consentId                   = jwtDecoder.decode(accessToken.replace("Bearer ", "")).getClaim("client.document").toString();;
        listResponseErrors          = new ArrayList<>();
        responseAccountPermissions  = new ResourcesAccountPermissions();
        consentIdentification       = new ConsentIdentification();
        resourcesAuthorisedList     = new ArrayList<>();

        /// Get All Accounts Resources
        try {
            responseAccountPermissions = resourcesApi.resourcesGetAccountPermissions(consentId);
            consentIdentification   = responseAccountPermissions.getData().getConsentIdentification();
            resourcesAuthorisedList = responseAccountPermissions.getData().getResourcesAuthorised();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Account request error (in ResourcesAPI)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Account request error (in ResourcesAPI)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Checks if accounts exist for consentId and if required permission is granted
        boolean accountExists    = false;
        boolean permissionExists = false;
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            accountExists = true;
            if (reg.getPermissions().toString().contains(PERMISSION_REQUIRED)) {
                permissionExists = true;
                break;
            }
        }
        if (!accountExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The requested account is not authorized in " +
                            "the consent informed by the AccessToken.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }
        if (!permissionExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Requested account does not have the required permission.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        return responseAuthorizationData;
    }
}
