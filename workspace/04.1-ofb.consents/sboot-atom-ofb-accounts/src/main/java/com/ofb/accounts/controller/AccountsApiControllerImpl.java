package com.ofb.accounts.controller;

import com.nimbusds.jose.shaded.gson.Gson;

import com.ofb.accounts.client.resources.model.ConsentIdentification;
import com.ofb.accounts.client.resources.model.ResourcesAccountAuthorisedInner;
import com.ofb.accounts.client.resources.model.ResourcesAccountPermissions;
import com.ofb.accounts.server.accounts.handler.AccountsApiDelegate;
import com.ofb.accounts.client.resources.handler.ResourcesCorporateApi;
import com.ofb.accounts.server.accounts.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import com.ofb.lib.security.profiles.CanClientOFBRead;
import com.ofb.lib.security.profiles.CanSystemOFBAdmin;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class AccountsApiControllerImpl implements AccountsApiDelegate {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ResourcesCorporateApi resourcesCorporateApi;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountList> accountsGetAccounts(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, EnumAccountType accountType, String paginationKey) {

        Gson gson = new Gson();
        String      consentId;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        ResourcesAccountPermissions responseAccountPermissions = null;
        ConsentIdentification consentIdentification = null;
        List<ResourcesAccountAuthorisedInner> resourcesAuthorisedList = new ArrayList<>();

        /// Extract AccessToken claims values
        try {
            consentId = jwtDecoder.decode(authorization.replace("Bearer ", "")).getClaim("ofb.consent.id").toString();
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Accounts request error (in getClaim AccessToken")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        ///  Consents Accounts API parameters
        try {
            resourcesCorporateApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
            resourcesCorporateApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));
            responseAccountPermissions = resourcesCorporateApi.resourcesGetAccountPermissions(authorization, consentId);

            consentIdentification = responseAccountPermissions.getData().getConsentIdentification();
            resourcesAuthorisedList = responseAccountPermissions.getData().getResourcesAuthorised();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 400) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Accounts request error (in ConsentsAPI method consentsGetConsentsConsentId)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("AccessToken: " + ex.getMessage().substring(ex.getMessage().indexOf("detail") + 9, ex.getMessage().indexOf("meta") - 5))
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            } else {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Accounts request error (in ConsentsAPI method consentsGetConsentsConsentId)")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail(ex.getMessage())
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Accounts request error (in ConsentsAPI method consentsGetConsentsConsentId)")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail(e.getMessage())
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        /// Validations
        if (!consentIdentification.getConsentStatus().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Accounts request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request Accounts information. The consentId (" + consentId + ") provided in the " +
                            "AccessToken has a current status of [" + consentIdentification.getConsentStatus() + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(consentIdentification.getConsentExpiration()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Accounts request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request Accounts information. The consentId (" + consentId + ") provided in " +
                            "the AccessToken has an ExpirationDateTime " +
                            "(" +
                            consentIdentification.getConsentExpiration()
                            + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        List<AccountData> accountDataList = new ArrayList<>();
        for (ResourcesAccountAuthorisedInner reg : resourcesAuthorisedList) {
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

        Links links = null;
        if (accountDataList.size() != 0) {
            links = Links.builder()
                    .self(URI.create("https://api.banco.com.br/open-banking/api/v1/resource").toString())
                    .first(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=1&page-size=25").toString())
                    .last(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=1&page-size=25").toString())
                    .next(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=1&page-size=25").toString())
                    .prev(URI.create("https://api.banco.com.br/open-banking/api/consents/" + consentId + "/extensions?page=1&page-size=25").toString())
                    .build();
        }

        Meta meta = Meta.builder()
                .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                .totalPages(accountDataList.size() == 0 ? 0 : 1)
                .totalRecords(accountDataList.size())
                .build();

        ResponseAccountList responseAccountList = ResponseAccountList.builder()
                .data(accountDataList)
                .links(links)
                .meta(meta)
                .build();

        return new ResponseEntity<>(responseAccountList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountId(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override
    public ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdBalances(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override
    public ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdOverdraftLimits(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, LocalDate fromBookingDate, LocalDate toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactions(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }

    @Override
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, LocalDate fromBookingDate, LocalDate toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactionsCurrent(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }

}
