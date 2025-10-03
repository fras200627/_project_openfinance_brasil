package com.ofb.authorization.service;

import com.ofb.authorization.server.authorizations.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationValidationService {

    @Autowired private AccessTokenClaimsValidationService   accessTokenClaimsValidationService;
    @Autowired private BusinessEntityValidationService      businessEntityValidationService;
    @Autowired private LoggedUserValidationService          loggedUserValidationService;
    @Autowired private ConsentValidationService             consentValidationService;

    public ResponseAuthorizationData authorizationValidate(String accessToken) {

        ResponseAuthorizationData responseAuthorizationData = new ResponseAuthorizationData();
        ResponseAuthorizationData responseExecuteValidate   = new ResponseAuthorizationData();
        ResultErrors resultErrors = new ResultErrors();
        List<ResultErrorsErrorsInner> errors = new ArrayList<>();

        responseAuthorizationData = accessTokenClaimsValidationService.accessTokenClaimsValidate(accessToken);
        if (responseAuthorizationData.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            return responseAuthorizationData;
        }

        responseExecuteValidate = businessEntityValidationService.businessEntityValidate(accessToken);
        if (responseExecuteValidate.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            responseAuthorizationData.getData().getResultStatus().setStatus(responseExecuteValidate.getData().getResultStatus().getStatus());
            responseAuthorizationData.getData().getResultValidation().setBusinessEntity(responseExecuteValidate.getData().getResultValidation().getBusinessEntity());
            errors.addAll(responseExecuteValidate.getData().getResultErrors().getErrors());
            resultErrors.setErrors(errors);
        } else {
            responseAuthorizationData.getData().getResultValidation().setBusinessEntity(responseExecuteValidate.getData().getResultValidation().getBusinessEntity());
        }

        responseExecuteValidate = loggedUserValidationService.loggedUserValidate(accessToken);
        if (responseExecuteValidate.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            responseAuthorizationData.getData().getResultStatus().setStatus(responseExecuteValidate.getData().getResultStatus().getStatus());
            responseAuthorizationData.getData().getResultValidation().setLoggedUser(responseExecuteValidate.getData().getResultValidation().getLoggedUser());
            errors.addAll(responseExecuteValidate.getData().getResultErrors().getErrors());
            resultErrors.setErrors(errors);
        } else {
            responseAuthorizationData.getData().getResultValidation().setLoggedUser(responseExecuteValidate.getData().getResultValidation().getLoggedUser());
        }

        responseExecuteValidate = consentValidationService.consentValidate(accessToken);
        if (responseExecuteValidate.getData().getResultStatus().getStatus().equals(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)) {
            responseAuthorizationData.getData().getResultStatus().setStatus(responseExecuteValidate.getData().getResultStatus().getStatus());
            responseAuthorizationData.getData().getResultValidation().setConsent(responseExecuteValidate.getData().getResultValidation().getConsent());
            errors.addAll(responseExecuteValidate.getData().getResultErrors().getErrors());
            resultErrors.setErrors(errors);
        } else {
            responseAuthorizationData.getData().getResultValidation().setConsent(responseExecuteValidate.getData().getResultValidation().getConsent());
        }

        responseAuthorizationData.getData().setResultErrors(resultErrors);
        responseAuthorizationData.setMeta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build());

        return responseAuthorizationData;
    }

}
