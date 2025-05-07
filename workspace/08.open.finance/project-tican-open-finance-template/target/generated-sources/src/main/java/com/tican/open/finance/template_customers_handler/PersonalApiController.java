package com.tican.open.finance.template_customers_handler;

import com.tican.open.finance.template_customers_model.ResponseError;
import com.tican.open.finance.template_customers_model.ResponsePersonalCustomersFinancialRelation;
import com.tican.open.finance.template_customers_model.ResponsePersonalCustomersIdentification;
import com.tican.open.finance.template_customers_model.ResponsePersonalCustomersQualification;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:28:17.923396-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
@Controller
@RequestMapping("${openapi.aPICustomersOpenFinanceBrasil.base-path:/open-banking/customers/v2}")
public class PersonalApiController implements PersonalApi {

    private final PersonalApiDelegate delegate;

    public PersonalApiController(@Autowired(required = false) PersonalApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new PersonalApiDelegate() {});
    }

    @Override
    public PersonalApiDelegate getDelegate() {
        return delegate;
    }

}
