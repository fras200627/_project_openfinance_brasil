package com.ofb.authentication.service;

import com.ofb.authentication.model.UserAuthenticated;
import com.ofb.authentication.server.model.TokenResponseModelTemplate;
import com.ofb.lib.amqp.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {

  @Value("${jwt.issuer}")
  private String issuerInfo;

  private final JwtEncoder              encoder;
  private final HttpServletResponse     httpServletResponse;
  private final HttpServletRequest      httpServletRequest;
  private final UserDetailsServiceImpl  userDetailsService;
  private final MessageService          messageService;
  
  public AuthenticationService(JwtEncoder encoder, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, UserDetailsServiceImpl userDetailsService, MessageService messageService) {
      this.encoder = encoder;
      this.httpServletResponse = httpServletResponse;
      this.httpServletRequest = httpServletRequest;
      this.userDetailsService = userDetailsService;
      this.messageService = messageService;
  }

  public TokenResponseModelTemplate generateClientToken() {

    UserAuthenticated userAuthenticated = userDetailsService.loadUserAuthenticated(httpServletRequest.getUserPrincipal().getName());
    OffsetDateTime expiresAt = userAuthenticated.getClientSecretExpiresAt().toLocalDateTime().atOffset(ZoneOffset.UTC);
    
    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer(issuerInfo)
            .subject(userAuthenticated.getUsername())
            .issuedAt(Instant.now())
            .expiresAt(expiresAt.toInstant())
            .claim("clientId"               , userAuthenticated.getUsername())
            .claim("clientName"             , userAuthenticated.getUsernameFull())
            .claim("clientSecretExpiratesAt", expiresAt.toString())
            .claim("authorities"            , userAuthenticated.getAuthorities().stream()
                                                    .map(GrantedAuthority::getAuthority)
                                                    .collect(Collectors.joining(",")))
            .claim("scope"                  , userAuthenticated.getScopes())
            .build();

    TokenResponseModelTemplate tokenResponseModelTemplate = TokenResponseModelTemplate.builder()
                                                            .accessToken(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                                                            .scope(userAuthenticated.getScopes())
                                                            .tokenType("Bearer Token")
                                                            .expiresIn(expiresAt.toInstant().hashCode())
                                                            .build();
    
    messageService.sendMessageAuditTemplate(httpServletRequest);
    
    return tokenResponseModelTemplate;
  }

  public TokenResponseModelTemplate generateAccessToken(String consentId) {

    var clientId         = httpServletRequest.getUserPrincipal().getName();
    OffsetDateTime expiresConsentAt = Timestamp.valueOf("2025-12-31 23:59:59").toLocalDateTime().atOffset(ZoneOffset.UTC);
    OffsetDateTime createdConsentAt = Timestamp.valueOf("2025-05-22 12:21:20").toLocalDateTime().atOffset(ZoneOffset.UTC);
    var scopes           = "RESOURCES_READ,ACCOUNTS_READ,ACCOUNTS_BALANCES_READ,ACCOUNTS_OVERDRAFT_LIMITS_READ,ACCOUNTS_TRANSACTIONS_READ";

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer(issuerInfo)
            .issuedAt(Instant.now())
            .expiresAt(expiresConsentAt.toInstant())
            .subject(clientId)
            .claim("BUSINESSENTITYIDENTIFICATION" , "Banco Santander")
            .claim("BUSINESSENTITYDOCUMENTREL"    , "00.000.000/0000-00")
            .claim("CPFNUMBER"                    , "65046301601")
            .claim("CONSENTID"                    , "urn:bancotcn:435E569CB1BC498595C4DD9B1ACB6801")
            .claim("CIVILNAME"                    , "Marina Alice Regina Peixoto")
            .claim("STATUS"                       , "AUTHORISED")
            .claim("CREATIONDATETIME"             ,	createdConsentAt.toString())
            .claim("EXPIRATIONDATETIME"           , expiresConsentAt.toString())
            .claim("SCOPE"                        , scopes )
            .build();

    TokenResponseModelTemplate tokenResponseModelTemplate =  TokenResponseModelTemplate.builder()
                                                            .accessToken(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                                                            .scope(scopes)
                                                            .tokenType("Bearer Token")
                                                            .expiresIn(expiresConsentAt.toInstant().hashCode())
                                                            .build();

    messageService.sendMessageAuditTemplate(httpServletRequest);

    return tokenResponseModelTemplate;
  }

}