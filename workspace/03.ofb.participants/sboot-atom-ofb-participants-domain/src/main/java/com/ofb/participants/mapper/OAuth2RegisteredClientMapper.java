package com.ofb.participants.mapper;

import com.ofb.participants.entity.OAuth2RegisteredClientsEntity;
import com.ofb.participants.server.model.OAuth2ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.UUID;

@Mapper
public interface OAuth2RegisteredClientMapper {

    OAuth2RegisteredClientMapper INSTANCE = Mappers.getMapper( OAuth2RegisteredClientMapper.class );

    default String getUUID() {
        return UUID.randomUUID().toString();
    }

    default String getStatus() {
        return "DISABLED";
    }

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

    /*
    private String id;
	private String clientId;
	private Instant clientIdIssuedAt;
	private String clientSecret;
	private Instant clientSecretExpiresAt;
	private String clientName;
	private Set<ClientAuthenticationMethod> clientAuthenticationMethods; string:
	private Set<AuthorizationGrantType> authorizationGrantTypes; string:
	private Set<String> redirectUris; string
	private Set<String> scopes; string
	private ClientSettings clientSettings;
	private TokenSettings tokenSettings;
     */

//    @Mapping(target = "id",                         expression = "java(getUUID())")
//    @Mapping(target = "clientId",                   source = "source.clientId")
//    @Mapping(target = "clientName",                 source = "source.clientName")
//    @Mapping(target = "clientIdIssuedAt",           expression = "java(getNewOffsetDateTime())")
//    @Mapping(target = "clientSecret",               source = "source.clientSecret")
//    @Mapping(target = "tokenDurationOfMinutes",     source = "source.tokenDurationOfMinutes")
//    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAtOfDays")
//    @Mapping(target = "requireAuthorizationConsent",source = "source.requireAuthorizationConsent")
//    @Mapping(target = "status",                     expression = "java(getStatus())")
//    @Mapping(target = "clientAuthenticationMethods",source = "source.listOfClientAuthenticationMethods")
//    @Mapping(target = "authorizationGrantTypes",    source = "source.listOfAuthorizationGrantTypes")
//    @Mapping(target = "redirectUris",               source = "source.listOfRedirectUris")
//    @Mapping(target = "scopes",                     source = "source.listOfScopes")
//    OAuth2RegisteredClientsEntity createRecordToEntity(ClientCreateRecord source);

//    @Mapping(target = "id",                         source = "source.registeredId")
//    @Mapping(target = "clientId",                   source = "source.clientId")
//    @Mapping(target = "clientName",                 source = "source.clientName")
//    @Mapping(target = "tokenDurationOfMinutes",     source = "source.tokenDurationOfMinutes")
//    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAtOfDays")
//    @Mapping(target = "requireAuthorizationConsent",source = "source.requireAuthorizationConsent")
//    @Mapping(target = "status",                     source = "source.status")
//    @Mapping(target = "clientAuthenticationMethods",source = "source.listOfClientAuthenticationMethods")
//    @Mapping(target = "authorizationGrantTypes",    source = "source.listOfAuthorizationGrantTypes")
//    @Mapping(target = "redirectUris",               source = "source.listOfRedirectUris")
//    @Mapping(target = "scopes",                     source = "source.listOfScopes")
//    OAuth2RegisteredClientsEntity updateRecordToEntity(ClientUpdateRecord source);

//    @Mapping(target = "registeredId",               source = "source.id")
//    @Mapping(target = "clientId",                   source = "source.clientId")
//    @Mapping(target = "clientIdIssuedAt",           source = "source.clientIdIssuedAt")
//    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAt")
//    @Mapping(target = "clientName",                 source = "source.clientName")
//    @Mapping(target = "clientAuthenticationMethods",source = "source.clientAuthenticationMethods")
//    @Mapping(target = "authorizationGrantTypes",    source = "source.authorizationGrantTypes")
//    @Mapping(target = "redirectUris",               source = "source.redirectUris")
//    @Mapping(target = "securityScope",              source = "source.scopes")
//    @Mapping(target = "clientSettings",             source = "source.clientSettings")
//    @Mapping(target = "tokenSettings",              source = "source.tokenSettings")
//    @Mapping(target = "status",                     source = "source.status")
//    @Mapping(target = "isAccountExpired",           expression = "java(getIsAccountExpired(source))")
//    @Mapping(target = "isAccountLocked",            expression = "java(getIsAccountLocked(source))")
//    @Mapping(target = "isCredentialsExpired",       expression = "java(getIsCredentialsExpired(source))")
//    @Mapping(target = "isEnabled",                  expression = "java(getIsEnabled(source))")
//    List<OAuth2ClientResponse> listEntityToListResponse(List<OAuth2RegisteredClientsEntity> source);

//    @Mapping(target = "id",                         source = "source.registeredId")
//    @Mapping(target = "clientId",                   source = "source.clientId")
//    @Mapping(target = "clientIdIssuedAt",           source = "source.clientIdIssuedAt")
//    @Mapping(target = "clientSecretExpiresAt",      source = "source.clientSecretExpiresAt")
//    @Mapping(target = "clientName",                 source = "source.clientName")
//    @Mapping(target = "clientAuthenticationMethods",source = "source.clientAuthenticationMethods")
//    @Mapping(target = "authorizationGrantTypes",    source = "source.authorizationGrantTypes")
//    @Mapping(target = "redirectUris",               source = "source.redirectUris")
//    @Mapping(target = "scopes",                     source = "source.securityScope")
//    @Mapping(target = "clientSettings",             source = "source.clientSettings")
//    @Mapping(target = "tokenSettings",              source = "source.tokenSettings")
//    @Mapping(target = "status",                     source = "source.status")
//    OAuth2RegisteredClientsEntity responseToEntity(OAuth2ClientResponse source);

}
