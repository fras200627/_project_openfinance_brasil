package com.ofb.consents.controller;

import com.ofb.consents.camel.model.RequestOrchParams;
import com.ofb.consents.camel.model.RequestParam;
import com.ofb.consents.client.consents.handler.ConsentsApi;
import com.ofb.consents.client.consents.model.ResponseAccessTokenRead;
import com.ofb.consents.client.consents.model.ResponseAccessTokenReadData;
import com.ofb.consents.server.handler.ConsentsApiDelegate;
import com.ofb.consents.server.model.*;
import com.ofb.consents.service.ConsentGetAccessTokenService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController @Slf4j
@SecurityScheme(type = SecuritySchemeType.HTTP,
                name = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class ConsentsApiControllerImpl implements ConsentsApiDelegate {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private ConsentGetAccessTokenService consentGetAccessTokenService;

    @Override
    public ResponseEntity<ResponseConsentRead> consentsGetConsentsConsentId(String consentId,
                                                                            String authorization,
                                                                            UUID xFapiInteractionId,
                                                                            String xFapiAuthDate,
                                                                            String xFapiCustomerIpAddress,
                                                                            String xCustomerUserAgent) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_CONSENT").build());
        requestParams.add(RequestParam.builder().paramName("consentId")
                .paramValue(consentId).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        ///
        ResponseConsentRead responseConsentRead = producerTemplate.requestBody("direct:consentRequestOrchestrationFlow",
                requestOrchParams, ResponseConsentRead.class);

        ///
        consentGetAccessTokenService.consentsGetAccessTokenConsentId(responseConsentRead);

        return new ResponseEntity<>(responseConsentRead, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseConsentReadExtensions> consentsGetConsentsConsentIdExtensions(String consentId,
                                                                                                String authorization,
                                                                                                UUID xFapiInteractionId,
                                                                                                String xFapiAuthDate,
                                                                                                String xFapiCustomerIpAddress,
                                                                                                String xCustomerUserAgent,
                                                                                                Integer page,
                                                                                                Integer pageSize) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("GET_CONSENT_EXTENSIONS").build());
        requestParams.add(RequestParam.builder().paramName("consentId")
                .paramValue(consentId).build());
        requestParams.add(RequestParam.builder().paramName("page")
                .paramValue(page).build());
        requestParams.add(RequestParam.builder().paramName("pageSize")
                .paramValue(pageSize).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:consentRequestOrchestrationFlow",
                requestOrchParams, ResponseConsentReadExtensions.class),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseConsent> consentsPostConsents(String authorization,
                                                                UUID xFapiInteractionId,
                                                                CreateConsent createConsent,
                                                                String xFapiAuthDate,
                                                                String xFapiCustomerIpAddress,
                                                                String xCustomerUserAgent) {


        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("POST_CONSENTS").build());
        requestParams.add(RequestParam.builder().paramName("createConsent")
                .paramValue(createConsent).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:consentRequestOrchestrationFlow",
                requestOrchParams, ResponseConsent.class),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseConsentExtensions> consentsPostConsentsConsentIdExtends(String consentId,
                                                                                          String authorization,
                                                                                          String xFapiCustomerIpAddress,
                                                                                          UUID xFapiInteractionId,
                                                                                          String xCustomerUserAgent,
                                                                                          CreateConsentExtensions createConsentExtensions,
                                                                                          String xFapiAuthDate) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("POST_CONSENTS_EXTENDS").build());
        requestParams.add(RequestParam.builder().paramName("consentId")
                .paramValue(consentId).build());
        requestParams.add(RequestParam.builder().paramName("createConsentExtensions")
                .paramValue(createConsentExtensions).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:consentRequestOrchestrationFlow",
                requestOrchParams, ResponseConsentExtensions.class),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> consentsDeleteConsentsConsentId(String consentId,
                                                                String authorization,
                                                                UUID xFapiInteractionId,
                                                                String xFapiAuthDate,
                                                                String xFapiCustomerIpAddress,
                                                                String xCustomerUserAgent) {

        List<RequestParam> requestParams = new ArrayList<>();
        requestParams.add(RequestParam.builder().paramName("Authorization")
                .paramValue(authorization).build());
        requestParams.add(RequestParam.builder().paramName("x-fapi-interaction-id")
                .paramValue(xFapiInteractionId).build());
        requestParams.add(RequestParam.builder().paramName("requestType")
                .paramValue("DELETE_CONSENTS").build());
        requestParams.add(RequestParam.builder().paramName("consentId")
                .paramValue(consentId).build());
        RequestOrchParams requestOrchParams = RequestOrchParams.builder()
                .requestOrchParams(requestParams).build();

        return new ResponseEntity<>(producerTemplate.requestBody("direct:consentRequestOrchestrationFlow",
                requestOrchParams, void.class),
                HttpStatus.OK);

    }

}
