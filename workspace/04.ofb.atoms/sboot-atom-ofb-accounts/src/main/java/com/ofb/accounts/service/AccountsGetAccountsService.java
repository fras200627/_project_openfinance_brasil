package com.ofb.accounts.service;

import com.google.gson.Gson;
import com.ofb.accounts.client.resources.handler.ResourcesApi;
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

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class AccountsGetAccountsService {

    @Autowired
    private HttpServletRequest request;

    private final static String PERMISSION_REQUIRED = "ACCOUNTS_READ";

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Autowired private ResourcesApi resourcesApi;

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate>   listResponseErrors;
    private ResourcesAccountPermissions         responseAccountPermissions;
    private ConsentIdentification               consentIdentification;
    private List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList;

    public ResponseAccountList accountsGetAccounts(String consentId, EnumAccountType accountType) {

        String token = request.getHeader("Authorization");

        resourcesApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
        resourcesApi.getApiClient().setBearerToken(token.replace("Bearer ", ""));

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

        /// Build Response
        List<AccountData> accountDataList = new ArrayList<>();
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getPermissions().toString().contains(PERMISSION_REQUIRED)) {
                if (reg.getAccountType().equals(accountType.getValue())) {
                    accountDataList.add(AccountData.builder()
                            .accountId(reg.getResourceId())
                            .type(EnumAccountType.fromValue(reg.getAccountType()))
                            .branchCode(reg.getAccountBranchCode())
                            .checkDigit(reg.getAccountCheckDigit())
                            .brandName(reg.getAccountBrandName())
                            .companyCnpj(reg.getAccountCompanyCNPJ())
                            .compeCode(reg.getAccountCompeCode())
                            .number(reg.getAccountNumber())
                            .build());
                }
            }
        }

        Links links = null;
        if (accountDataList.size() != 0) {
            links = Links.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .last(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/api/v2/resource").toString())
                    .build();
        }

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                .totalPages(accountDataList.size() == 0 ? 0 : 1)
                .totalRecords(accountDataList.size())
                .build();

        ResponseAccountList responseAccountList = ResponseAccountList.builder()
                .data(accountDataList)
                .links(links)
                .meta(meta)
                .build();

        return responseAccountList;
    }

}
