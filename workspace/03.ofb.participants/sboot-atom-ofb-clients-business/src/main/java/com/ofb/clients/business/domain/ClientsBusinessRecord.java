package com.ofb.clients.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ofb.clients.business.entity.ClientsBusinessEntity;

import java.util.Date;

public record ClientsBusinessRecord(
        String  id,
        String  clientId,
        String document,
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
    public ClientsBusinessRecord(ClientsBusinessEntity response) {
        this(response.getId(),
                response.getClientId(),
                response.getDocument(),
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
