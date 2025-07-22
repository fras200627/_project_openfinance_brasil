package com.tican.oauth2.clients.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tican.oauth2.clients.entity.OAuth2RegisteredClientsEntity;

import java.util.Date;

public record OAuth2RegisteredClientsRecord(
        String  id,
        String  clientId,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        Date    clientIdIssuedAt,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        Date    clientSecretExpiresAt,
        String  clientName,
        String  clientAuthenticationMethods,
        String  authorizationGrantTypes,
        String  redirectUris,
        String  scopes,
        String  clientSettings,
        String  tokenSettings,
        String  status,
        boolean isAccountExpired,
        boolean isAccountLocked,
        boolean isCredentialsExpired,
        boolean isEnabled
) {
    public OAuth2RegisteredClientsRecord(OAuth2RegisteredClientsEntity response) {
        this(response.getId(),
                response.getClientId(),
                response.getClientIdIssuedAt(),
                response.getClientSecretExpiresAt(),
                response.getClientName(),
                response.getClientAuthenticationMethods(),
                response.getAuthorizationGrantTypes(),
                response.getRedirectUris(),
                response.getScopes(),
                response.getClientSettings(),
                response.getTokenSettings(),
                response.getStatus(),
                response.isAccountExpired(),
                response.isAccountLocked(),
                response.isCredentialsExpired(),
                response.isEnabled()
        );
    }
}
