package com.ofb.authentication.service;

import com.ofb.authentication.model.UserAuthenticated;
import com.ofb.authentication.server.model.AccessTokenRequest;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {

  @Value("${jwt.issuer}")
  private String issuerInfo;

  private final JwtEncoder encoder;
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

  public TokenResponseModelTemplate generateAccessToken(AccessTokenRequest accessTokenRequest) {
    var clientId = httpServletRequest.getUserPrincipal().getName();
    OffsetDateTime expiresConsentAt = null;
    if (accessTokenRequest.getExpirationDateTime() != null && !accessTokenRequest.getExpirationDateTime().isEmpty()) {
      expiresConsentAt = Timestamp.valueOf(accessTokenRequest.getExpirationDateTime().replace("T", " ").replace("Z", "")).toLocalDateTime().atOffset(ZoneOffset.UTC);
    } else {
      expiresConsentAt = OffsetDateTime.of(2099, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
    }
    String consentPermissions = Arrays.toString(accessTokenRequest.getScopes().toArray()).replace("[", "").replace("]", "");

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer(issuerInfo)
            .issuedAt(Instant.now())
            .expiresAt(expiresConsentAt.toInstant())
            .subject(clientId)
            .claim("ofb.consent.id",                                     accessTokenRequest.getConsentId())
//            .claim("ofb.consent.consentStatus",                          "AUTHORISED")
//            .claim("ofb.consent.creation.datetime",	                     accessTokenRequest.getCreationDateTime())
//            .claim("ofb.consent.expiration.datetime",                    accessTokenRequest.getExpirationDateTime())
//            .claim("ofb.consent.businessEntity.name",                    accessTokenRequest.getBusinessEntity().getDocument().getEntityBusinessName())
//            .claim("ofb.consent.businessEntity.document.identification", accessTokenRequest.getBusinessEntity().getDocument().getIdentification())
//            .claim("ofb.consent.businessEntity.document.rel",            accessTokenRequest.getBusinessEntity().getDocument().getRel())
//            .claim("ofb.consent.logged.user.name",                       accessTokenRequest.getLoggedUser().getDocument().getLoggedUserName())
//            .claim("ofb.consent.logged.user.document.identification",    accessTokenRequest.getLoggedUser().getDocument().getIdentification())
//            .claim("ofb.consent.logged.user.document.rel",               accessTokenRequest.getLoggedUser().getDocument().getRel())
            .claim("ofb.consent.permissions",                            consentPermissions)
            .build();

    TokenResponseModelTemplate tokenResponseModelTemplate =  TokenResponseModelTemplate.builder()
                                                            .accessToken(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                                                            .scope("client.ofb.read")
                                                            .tokenType("Bearer Token")
                                                            .expiresIn(expiresConsentAt.toInstant().hashCode())
                                                            .build();

    messageService.sendMessageAuditTemplate(httpServletRequest);

    return tokenResponseModelTemplate;
  }

}