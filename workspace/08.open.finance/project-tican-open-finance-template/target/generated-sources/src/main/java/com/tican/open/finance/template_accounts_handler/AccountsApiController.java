package com.tican.open.finance.template_accounts_handler;

import org.springframework.format.annotation.DateTimeFormat;
import com.tican.open.finance.template_accounts_model.EnumAccountType;
import com.tican.open.finance.template_accounts_model.EnumCreditDebitIndicator;
import java.time.LocalDate;
import com.tican.open.finance.template_accounts_model.ResponseAccountBalances;
import com.tican.open.finance.template_accounts_model.ResponseAccountIdentification;
import com.tican.open.finance.template_accounts_model.ResponseAccountList;
import com.tican.open.finance.template_accounts_model.ResponseAccountOverdraftLimits;
import com.tican.open.finance.template_accounts_model.ResponseAccountTransactions;
import com.tican.open.finance.template_accounts_model.ResponseError;
import com.tican.open.finance.template_accounts_model.ResponseErrorMetaSingle;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:28:21.090691100-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
@Controller
@RequestMapping("${openapi.aPIAccountsOpenFinanceBrasil.base-path:/open-banking/accounts/v2}")
public class AccountsApiController implements AccountsApi {

    private final AccountsApiDelegate delegate;

    public AccountsApiController(@Autowired(required = false) AccountsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new AccountsApiDelegate() {});
    }

    @Override
    public AccountsApiDelegate getDelegate() {
        return delegate;
    }

}
