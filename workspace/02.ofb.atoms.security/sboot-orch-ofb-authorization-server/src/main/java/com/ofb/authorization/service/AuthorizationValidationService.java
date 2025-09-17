package com.ofb.authorization.service;

import com.ofb.authorization.server.authorizations.model.Meta;
import com.ofb.authorization.server.authorizations.model.ResponseAuthorizationData;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<ResponseErrorsInnerTemplate> resultValidation = new ArrayList<>();

        List<ResponseErrorsInnerTemplate> responseClaimsValidation          =  accessTokenClaimsValidationService.executeValidate(accessToken);
        if (!responseClaimsValidation.isEmpty()) {
            resultValidation.addAll(responseClaimsValidation);
            return new ResponseAuthorizationData().toBuilder()
                    .data(null)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        }

        resultValidation.addAll(businessEntityValidationService.executeValidate(accessToken));
        resultValidation.addAll(loggedUserValidationService.executeValidate(accessToken));
        resultValidation.addAll(consentValidationService.executeValidate(accessToken));
        if (!resultValidation.isEmpty()) {
            return new ResponseAuthorizationData().toBuilder()
                    .data(null)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        } else {
            return new ResponseAuthorizationData().toBuilder()
                    .data(null)
                    .meta(Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build())
                    .build();
        }
    }

}
