package com.spring.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private JwtService jwtService;

  public AuthenticationService(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  public String authenticate(Authentication authentication) {
    return jwtService.generateToken(authentication);
  }

  public String generateAccessToken(Authentication authentication, String consentId, String customerId, String customerCpf) {
    return jwtService.generateAccessToken(authentication, consentId, customerId, customerCpf);
  }
  
}
