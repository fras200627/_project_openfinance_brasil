package com.ofb.customers.service.validation;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.customers.client.resources.handler.ResourcesCorporateApi;
import com.ofb.customers.client.resources.model.ConsentCompleteIdentification;
import com.ofb.customers.client.resources.model.ResourcesCustomerAuthorisedInner;
import com.ofb.customers.client.resources.model.ResourcesCustomerPermissions;
import com.ofb.customers.service.CustomerGetPersonalIdentificationsService;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class CustomerRequestValidation {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ResourcesCorporateApi resourcesCorporateApi;

    @Autowired
    private JwtDecoder jwtDecoder;

    public List<ResourcesCustomerAuthorisedInner> validateRequest(String authorization, String personalId, String permission) {

        Gson gson = new Gson();
        String      consentId;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();

        ResourcesCustomerPermissions responseCustomerPermissions = null;
        ConsentCompleteIdentification consentIdentification = null;
        List<ResourcesCustomerAuthorisedInner> resourcesAuthorisedList = new ArrayList<>();

        /// Extract AccessToken claims values
        try {
            consentId = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        ///  Consents Accounts API parameters
        try {
            resourcesCorporateApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
            resourcesCorporateApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));
            responseCustomerPermissions = resourcesCorporateApi.resourcesGetCustomerPermissions(authorization, consentId);

            consentIdentification = responseCustomerPermissions.getData().getConsentCompleteIdentification();
            resourcesAuthorisedList = responseCustomerPermissions.getData().getResourcesAuthorised();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Customer request error (in ResourcesAPI")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Customer request error (in ResourcesAPI")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error (in ResourcesAPI")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Validations
        if (!consentIdentification.getConsentStatus().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request Customer information. The consentId (" + consentId + ") provided in the " +
                            "AccessToken has a current status of [" + consentIdentification.getConsentStatus() + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(consentIdentification.getConsentExpiration()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request information(s). The consentId (" + consentId + ") provided in " +
                            "the AccessToken has an ExpirationDateTime " +
                            "(" +
                            consentIdentification.getConsentExpiration()
                            + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        boolean customerExists = false;
        boolean permissionExists = false;
        for (ResourcesCustomerAuthorisedInner reg : resourcesAuthorisedList) {
            if (reg.getResourceId().equals(personalId) || personalId.isEmpty()) {
                customerExists = true;
                if (reg.getPermissions().toString().contains(permission)) {
                    permissionExists = true;
                    break;
                }
            }
        }

        if (!customerExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The request information(s) for " +
                            "the consentId [" + consentId + "] reported in the AccessToken " +
                            "is not authorized in that consent.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!permissionExists) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The customer information(s) request for the consentId (" + consentId + ") " +
                            "reported in the AccessToken has [" +  resourcesAuthorisedList.size() + "] " +
                            "Authorised ResourceAccount, but none have the permission = [" + permission + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        return resourcesAuthorisedList;
    }

}
