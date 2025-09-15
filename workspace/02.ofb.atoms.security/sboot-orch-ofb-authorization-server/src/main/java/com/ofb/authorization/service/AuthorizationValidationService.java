package com.ofb.authorization.service;

import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationValidationService {

    @Autowired private AccessTokenClaimsValidationService accessTokenClaimsValidationService;
    @Autowired private BusinessEntityValidationService businessEntityValidationService;
    @Autowired private LoggedUserValidationService loggedUserValidationService;
    @Autowired private ConsentValidationService consentValidationService;

    public ResponseAuthorizationValidate authorizationValidate(String accessToken) {

        accessTokenClaimsValidationService.executeValidate(accessToken);
        businessEntityValidationService.executeValidate(accessToken);
        loggedUserValidationService.executeValidate(accessToken);
        consentValidationService.executeValidate((accessToken));

        return new ResponseAuthorizationValidate();
    }

}
