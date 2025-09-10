package com.ofb.clients.business.mapper;

import com.ofb.clients.business.entity.ClientsBusinessEntity;
import com.ofb.clients.business.server.clients.model.OAuth2ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface ClientsBusinessMapper {

    ClientsBusinessMapper INSTANCE = Mappers.getMapper( ClientsBusinessMapper.class );

    default OffsetDateTime getNewOffsetDateTime() {
        return OffsetDateTime.now();
    }

    default String getNewOffsetDateTimeFormatString() {
        return OffsetDateTime.now().toString();
    }

    default String getIsAccountExpired(ClientsBusinessEntity source) {
        return String.valueOf(source.isAccountExpired());
    }

    default String getIsAccountLocked(ClientsBusinessEntity source) {
        return String.valueOf(source.isAccountLocked());
    }

    default String getIsCredentialsExpired(ClientsBusinessEntity source) {
        return String.valueOf(source.isCredentialsExpired());
    }

    default String getIsEnabled(ClientsBusinessEntity source) {
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
    OAuth2ClientResponse entityToResponse(ClientsBusinessEntity source);

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
    List<OAuth2ClientResponse> listEntityToListResponse(List<ClientsBusinessEntity> source);

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
    ClientsBusinessEntity responseToEntity(OAuth2ClientResponse source);

}
