package com.ofb.customers.camel.processor;

import com.google.gson.Gson;
import com.ofb.customers.client.resources.model.ConsentCompleteIdentification;
import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.client.resources.model.ResourcesCustomerPermissions;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.customers.client.authorization.model.ResultErrorsErrorsInner;
import com.ofb.customers.client.resources.handler.ResourcesApi;
import com.ofb.customers.client.resources.model.ResponseResourceList;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Component @Slf4j
public class CustomerResourcesProcessor implements Processor {

    private Gson gson = new Gson();
    private ResourcesCustomerPermissions resourcesCustomerPermissions;
    private List<ResultErrorsErrorsInner> listResponseErrors ;
    private String permissionRequired;
    private String consentId;

    @Override
    public void process(Exchange exchange) throws Exception {

        ResourcesApi resourcesApi = new ResourcesApi();
        listResponseErrors        = new ArrayList<>();
        resourcesCustomerPermissions      = new ResourcesCustomerPermissions();
        ResourcesCustomerPermissions responseCustomerPermissions = new ResourcesCustomerPermissions();
        ConsentCompleteIdentification consentIdentification      = new ConsentCompleteIdentification();
        List<ResourcesCustomerAuthorisedInner> resourcesAuthorisedList = new ArrayList<>();

        resourcesApi.getApiClient().setBasePath(exchange.getProperty("OFB_RESOURCES_API_URL").toString());
        resourcesApi.getApiClient().setBearerToken(exchange.getProperty("Authorization").toString().replace("Bearer ", ""));
        resourcesApi.getApiClient().addDefaultHeader("x-fapi-interaction-id",exchange.getProperty("x-fapi-interaction-id").toString());
        permissionRequired = exchange.getProperty("PERMISSION_REQUIRED").toString();
        consentId = exchange.getProperty("consentId").toString();

        try {
            resourcesCustomerPermissions = resourcesApi.resourcesGetCustomerPermissions(consentId);
            consentIdentification = resourcesCustomerPermissions.getData().getConsentCompleteIdentification();
            resourcesAuthorisedList = resourcesCustomerPermissions.getData().getResourcesAuthorised();
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

        boolean customerExists = false;
        boolean permissionExists = false;
        for (ResourcesCustomerAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(consentIdentification.getPersonalId())) {
                customerExists = true;
                if (reg.getPermissions().toString().contains(permissionRequired)) {
                    permissionExists = true;
                    break;
                }
            }
        }

        if (!customerExists) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Customer Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The request information(s) for " +
                            "the consentId [" + consentId + "] reported in the AccessToken " +
                            "is not authorized in that consent.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!permissionExists) {
            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
                    .title("Get Customer Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The Customer Resources Permissions information(s) request for the consentId (" + consentId + ") " +
                            "reported in the AccessToken has [" +  resourcesAuthorisedList.size() + "] " +
                            "Authorised ResourceAccount, but none have the permission = [" + permissionRequired + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        exchange.getMessage().setBody(resourcesCustomerPermissions);
    }
}
