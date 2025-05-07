package com.tican.open.finance.template_consents_handler;

import com.tican.open.finance.template_consents_model.ConsentsPostConsents529Response;
import com.tican.open.finance.template_consents_model.CreateConsent;
import com.tican.open.finance.template_consents_model.CreateConsentExtensions;
import com.tican.open.finance.template_consents_model.Model422ResponseErrorCreateConsent;
import com.tican.open.finance.template_consents_model.ResponseConsent;
import com.tican.open.finance.template_consents_model.ResponseConsentExtensions;
import com.tican.open.finance.template_consents_model.ResponseConsentRead;
import com.tican.open.finance.template_consents_model.ResponseConsentReadExtensions;
import com.tican.open.finance.template_consents_model.ResponseError;
import com.tican.open.finance.template_consents_model.ResponseErrorUnprocessableEntity;
import com.tican.open.finance.template_consents_model.ResponseErrorUnprocessableEntityDelete;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:28:22.865256600-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
@Controller
@RequestMapping("${openapi.aPIConsentsOpenFinanceBrasil.base-path:/open-banking/consents/v3}")
public class ConsentsApiController implements ConsentsApi {

    private final ConsentsApiDelegate delegate;

    public ConsentsApiController(@Autowired(required = false) ConsentsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ConsentsApiDelegate() {});
    }

    @Override
    public ConsentsApiDelegate getDelegate() {
        return delegate;
    }

}
