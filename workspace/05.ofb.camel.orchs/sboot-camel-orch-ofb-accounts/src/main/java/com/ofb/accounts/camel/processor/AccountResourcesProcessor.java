package com.ofb.accounts.camel.processor;

import com.google.gson.Gson;
import com.ofb.accounts.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.accounts.client.resources.handler.ResourcesApi;
import com.ofb.accounts.client.resources.model.ConsentIdentification;
import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.client.resources.model.ResourcesAccountPermissions;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Component @Slf4j
public class AccountResourcesProcessor implements Processor {

    private Gson gson = new Gson();
    private ResourcesAccountPermissions resourcesAccountPermissions;
    private List<ResultErrorsErrorsInner> listResponseErrors ;
    private String permissionRequired;
    private String consentId;
    private String accountId;

    @Override
    public void process(Exchange exchange) throws Exception {

        ResourcesApi resourcesApi = new ResourcesApi();
        listResponseErrors        = new ArrayList<>();
        resourcesAccountPermissions      = new ResourcesAccountPermissions();
        ConsentIdentification consentIdentification      = new ConsentIdentification();
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = new ArrayList<>();

        resourcesApi.getApiClient().setBasePath(exchange.getProperty("OFB_RESOURCES_API_URL").toString());
        resourcesApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        resourcesApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());
        permissionRequired = exchange.getProperty("PERMISSION_REQUIRED").toString();
        consentId = exchange.getProperty("consentId").toString();
        accountId = exchange.getProperty("accountId").toString();

        try {
            resourcesAccountPermissions = resourcesApi.resourcesGetAccountPermissions(consentId);
            consentIdentification = resourcesAccountPermissions.getData().getConsentIdentification();
            resourcesAuthorisedList = resourcesAccountPermissions.getData().getResourcesAuthorised();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                        .title("Get Customer Resources Permissions request error")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                        .title("Get Customer Resources Permissions request error")
                        .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new InternalErrorException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Customer Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        boolean accountExists = false;
        boolean permissionExists = false;
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(accountId) || accountId.isEmpty()) {
                accountExists = true;
                if (reg.getPermissions().toString().contains(permissionRequired)) {
                    permissionExists = true;
                    break;
                }
            }
        }

        if (!accountExists) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Account request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The request information(s) for " +
                            "the consentId [" + consentId + "] reported in the AccessToken " +
                            "is not authorized in that consent.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!permissionExists) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Account request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The account information(s) request for the consentId (" + consentId + ") " +
                            "reported in the AccessToken has [" +  resourcesAuthorisedList.size() + "] " +
                            "Authorised ResourceAccount, but none have the permission = [" + permissionRequired + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        exchange.getMessage().setBody(resourcesAuthorisedList);
    }
}
