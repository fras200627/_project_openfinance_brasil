package com.tican.oauth2.clients.mapper;

import com.tican.oauth2.clients.api.server.model.OAuth2ClientResponse;
import com.tican.oauth2.clients.entity.OAuth2RegisteredClientsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface OAuth2RegisteredClientMapper {

    OAuth2RegisteredClientMapper INSTANCE = Mappers.getMapper( OAuth2RegisteredClientMapper.class );

    default OffsetDateTime getNewOffsetDateTime() {
        return OffsetDateTime.now();
    }

    default String getNewOffsetDateTimeFormatString() {
        return OffsetDateTime.now().toString();
    }

    default String getIsAccountExpired(OAuth2RegisteredClientsEntity source) {
        return String.valueOf(source.isAccountExpired());
    }

    default String getIsAccountLocked(OAuth2RegisteredClientsEntity source) {
        return String.valueOf(source.isAccountLocked());
    }

    default String getIsCredentialsExpired(OAuth2RegisteredClientsEntity source) {
        return String.valueOf(source.isCredentialsExpired());
    }

    default String getIsEnabled(OAuth2RegisteredClientsEntity source) {
        return String.valueOf(source.isEnabled());
    }

    @Mapping(target = "registeredId",               source = "source.id")
    @Mapping(target = "clientId",                   source = "source.clientId")
    @Mapping(target = "clientIdIssuedAt",           source = "source.clientIdIssuedAt")
    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAt")
    @Mapping(target = "clientName",                 source = "source.clientName")
    @Mapping(target = "clientAuthenticationMethods",source = "source.clientAuthenticationMethods")
    @Mapping(target = "authorizationGrantTypes",    source = "source.authorizationGrantTypes")
    @Mapping(target = "redirectUris",               source = "source.redirectUris")
    @Mapping(target = "securityScope",              source = "source.scopes")
    @Mapping(target = "clientSettings",             source = "source.clientSettings")
    @Mapping(target = "tokenSettings",              source = "source.tokenSettings")
    @Mapping(target = "status",                     source = "source.status")
    @Mapping(target = "isAccountExpired",           expression = "java(getIsAccountExpired(source))")
    @Mapping(target = "isAccountLocked",            expression = "java(getIsAccountLocked(source))")
    @Mapping(target = "isCredentialsExpired",       expression = "java(getIsCredentialsExpired(source))")
    @Mapping(target = "isEnabled",                  expression = "java(getIsEnabled(source))")
    OAuth2ClientResponse entityToResponse(OAuth2RegisteredClientsEntity source);

    @Mapping(target = "registeredId",               source = "source.id")
    @Mapping(target = "clientId",                   source = "source.clientId")
    @Mapping(target = "clientIdIssuedAt",           source = "source.clientIdIssuedAt")
    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAt")
    @Mapping(target = "clientName",                 source = "source.clientName")
    @Mapping(target = "clientAuthenticationMethods",source = "source.clientAuthenticationMethods")
    @Mapping(target = "authorizationGrantTypes",    source = "source.authorizationGrantTypes")
    @Mapping(target = "redirectUris",               source = "source.redirectUris")
    @Mapping(target = "securityScope",              source = "source.scopes")
    @Mapping(target = "clientSettings",             source = "source.clientSettings")
    @Mapping(target = "tokenSettings",              source = "source.tokenSettings")
    @Mapping(target = "status",                     source = "source.status")
    @Mapping(target = "isAccountExpired",           expression = "java(getIsAccountExpired(source))")
    @Mapping(target = "isAccountLocked",            expression = "java(getIsAccountLocked(source))")
    @Mapping(target = "isCredentialsExpired",       expression = "java(getIsCredentialsExpired(source))")
    @Mapping(target = "isEnabled",                  expression = "java(getIsEnabled(source))")
    List<OAuth2ClientResponse> listEntityToListResponse(List<OAuth2RegisteredClientsEntity> source);

    @Mapping(target = "id",                         source = "source.registeredId")
    @Mapping(target = "clientId",                   source = "source.clientId")
    @Mapping(target = "clientIdIssuedAt",           source = "source.clientIdIssuedAt")
    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAt")
    @Mapping(target = "clientName",                 source = "source.clientName")
    @Mapping(target = "clientAuthenticationMethods",source = "source.clientAuthenticationMethods")
    @Mapping(target = "authorizationGrantTypes",    source = "source.authorizationGrantTypes")
    @Mapping(target = "redirectUris",               source = "source.redirectUris")
    @Mapping(target = "scopes",                     source = "source.securityScope")
    @Mapping(target = "clientSettings",             source = "source.clientSettings")
    @Mapping(target = "tokenSettings",              source = "source.tokenSettings")
    @Mapping(target = "status",                     source = "source.status")
    OAuth2RegisteredClientsEntity responseToEntity(OAuth2ClientResponse source);

}
