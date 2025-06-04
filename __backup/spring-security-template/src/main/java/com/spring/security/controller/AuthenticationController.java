package com.spring.security.controller;

import  com.spring.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping(value = "/authenticate")
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }

  @GetMapping(value = "/accessToken")
  public String accessToken(Authentication authentication, 
                            @RequestHeader String consentId, 
                            @RequestHeader String  customerId, 
                            @RequestHeader String  customerCpf) {
    
    return authenticationService.generateAccessToken(authentication,
                                                     consentId,
                                                     customerId,
                                                     customerCpf);
  }

}
