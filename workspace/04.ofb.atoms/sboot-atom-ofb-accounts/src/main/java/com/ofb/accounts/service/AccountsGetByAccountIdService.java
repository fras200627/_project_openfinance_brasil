package com.ofb.accounts.service;

import com.google.gson.Gson;
import com.ofb.accounts.client.authorization.handler.AuthorizationValidateApi;
import com.ofb.accounts.client.authorization.model.ResponseAuthorizationData;
import com.ofb.accounts.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.accounts.client.authorization.model.ResultStatus;
import com.ofb.accounts.client.resources.handler.ResourcesCorporateApi;
import com.ofb.accounts.client.resources.model.ConsentIdentification;
import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.client.resources.model.ResourcesAccountPermissions;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class AccountsGetByAccountIdService {

    private final static String PERMISSION_REQUIRED = "ACCOUNTS_READ";

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Value("${app.paths.clients.ofb-authorization}")
    private String OFB_PATH_AUTHORIZATION;

    @Autowired private ResourcesCorporateApi            resourcesCorporateApi;
    @Autowired private AuthorizationValidateApi         authorizationValidateApi;

    private Gson gson = new Gson();
    private String consentId;
    private List<ResponseErrorsInnerTemplate>   listResponseErrors;
    private ResourcesAccountPermissions         responseAccountPermissions;
    private ConsentIdentification               consentIdentification;
    private List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList;
    private ResponseAuthorizationData             responseAuthorizationData;

    public ResponseAccountIdentification accountsGetByAccountId(String accessToken, String accountId) {

        authorizationValidateApi.getApiClient().setBasePath(OFB_PATH_AUTHORIZATION);
        authorizationValidateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        resourcesCorporateApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
        resourcesCorporateApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        consentId                   = "";
        listResponseErrors          = new ArrayList<>();
        responseAccountPermissions  = new ResourcesAccountPermissions();
        consentIdentification       = new ConsentIdentification();
        resourcesAuthorisedList     = new ArrayList<>();
        responseAuthorizationData   = new ResponseAuthorizationData();

        /// Authorize AccessToken
        try {
            responseAuthorizationData = authorizationValidateApi.authorizationValidate();
            consentId = responseAuthorizationData.getData().getResultStatus().getConsentId();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Account request error (in ResourcesAPI)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            for (ResultErrorsErrorsInner reg : responseAuthorizationData.getData().getResultErrors().getErrors()) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title(reg.getTitle())
                        .code(reg.getCode())
                        .detail(reg.getDetail().replaceAll("\\\\", ""))
                        .build());
            }
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Get All Accounts Resources
        try {
            responseAccountPermissions = resourcesCorporateApi.resourcesGetAccountPermissions(accessToken, consentId);
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
            if (reg.getResourceId().equals(accountId)) {
                accountExists = true;
                if (reg.getPermissions().toString().contains(PERMISSION_REQUIRED)) {
                    permissionExists = true;
                    break;
                }
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

        /// Build Response
        AccountIdentificationData accountIdentificationData = new AccountIdentificationData();
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(accountId)) {
                if (reg.getPermissions().toString().contains("ACCOUNTS_READ")) {
                    accountIdentificationData.branchCode(reg.getAccountBranchCode());
                    accountIdentificationData.checkDigit(reg.getAccountCheckDigit());
                    accountIdentificationData.compeCode(reg.getAccountCompeCode());
                    accountIdentificationData.currency(reg.getAccountCurrency());
                    accountIdentificationData.number(reg.getAccountNumber());
                    accountIdentificationData.subtype(EnumAccountSubType.fromValue(reg.getAccountSubType()));
                    accountIdentificationData.type(EnumAccountType.fromValue(reg.getAccountType()));
                    break;
                }
            }
        }

        LinksAccountId linksAccountId = LinksAccountId.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .last(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .build();

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .totalPages(1)
                .totalRecords(1)
                .build();

        ResponseAccountIdentification responseAccountIdentification = ResponseAccountIdentification.builder()
                .data(accountIdentificationData)
                .links(linksAccountId)
                .meta(meta)
                .build();

        return responseAccountIdentification;
    }

}
