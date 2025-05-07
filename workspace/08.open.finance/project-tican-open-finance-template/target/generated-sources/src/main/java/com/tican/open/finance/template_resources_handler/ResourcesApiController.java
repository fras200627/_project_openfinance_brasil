package com.tican.open.finance.template_resources_handler;

import com.tican.open.finance.template_resources_model.ResponseErrorWithAbleAdditionalProperties;
import com.tican.open.finance.template_resources_model.ResponseResourceList;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:28:16.171410100-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
@Controller
@RequestMapping("${openapi.aPIResourcesOpenFinanceBrasil.base-path:/open-banking/resources/v3}")
public class ResourcesApiController implements ResourcesApi {

    private final ResourcesApiDelegate delegate;

    public ResourcesApiController(@Autowired(required = false) ResourcesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ResourcesApiDelegate() {});
    }

    @Override
    public ResourcesApiDelegate getDelegate() {
        return delegate;
    }

}
