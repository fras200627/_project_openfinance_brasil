package com.ofb.accounts.controller;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.accounts.client.consents.handler.ConsentsApi;
import com.ofb.accounts.client.consents.model.ResponseConsentRead;
import com.ofb.accounts.server.accounts.handler.AccountsApiDelegate;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
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

    @Value("${app.paths.clients.ofb-consents}")
    private String OFB_PATH_CONSENTS;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ConsentsApi consentsApi;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountList> accountsGetAccounts(String authorization, UUID xFapiInteractionId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, EnumAccountType accountType, String paginationKey) {

        Gson gson = new Gson();
        String      consentId;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        ResponseConsentRead responseConsentRead = null;

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
            consentsApi.getApiClient().setBasePath(OFB_PATH_CONSENTS);
            consentsApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));
            responseConsentRead = consentsApi.consentsGetConsentsConsentId(consentId,
                    authorization,
                    xFapiInteractionId,
                    xFapiAuthDate,
                    xFapiCustomerIpAddress,
                    xCustomerUserAgent);
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
        try {
            if (!responseConsentRead.getData().getStatus().getValue().equals("AUTHORISED")) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Accounts request error")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Unable to request Accounts information. The consentId (" + consentId + ") provided in the " +
                                "AccessToken has a current status of [" + responseConsentRead.getData().getStatus().getValue() + "].")
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }

            if (!OffsetDateTime.parse(responseConsentRead.getData().getExpirationDateTime()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Get Accounts request error")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("Unable to request Accounts information. The consentId (" + consentId + ") provided in " +
                                "the AccessToken has an ExpirationDateTime " +
                                "(" +
                                responseConsentRead.getData().getExpirationDateTime()
                                + ") of 'expired'. ")
                        .build());
                throw new BadRequestException(gson.toJson(listResponseErrors));
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Accounts request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }




        return AccountsApiDelegate.super.accountsGetAccounts(authorization, xFapiInteractionId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, accountType, paginationKey);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountId(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdBalances(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdOverdraftLimits(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, LocalDate fromBookingDate, LocalDate toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactions(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }

    @Override @CanSystemOFBAdmin @CanClientOFBRead
    public ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String authorization, UUID xFapiInteractionId, String accountId, String xFapiAuthDate, String xFapiCustomerIpAddress, String xCustomerUserAgent, Integer page, Integer pageSize, LocalDate fromBookingDate, LocalDate toBookingDate, EnumCreditDebitIndicator creditDebitIndicator, String paginationKey) {
        return AccountsApiDelegate.super.accountsGetAccountsAccountIdTransactionsCurrent(authorization, xFapiInteractionId, accountId, xFapiAuthDate, xFapiCustomerIpAddress, xCustomerUserAgent, page, pageSize, fromBookingDate, toBookingDate, creditDebitIndicator, paginationKey);
    }
}
