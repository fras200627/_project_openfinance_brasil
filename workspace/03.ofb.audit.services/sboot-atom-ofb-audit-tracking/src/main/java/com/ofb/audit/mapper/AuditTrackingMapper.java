package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditEntity;
import com.ofb.audit.server.model.AuditResponse;
import com.ofb.audit.server.model.AuditResponsePageable;
import com.ofb.audit.server.model.PageableDetailsTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface AuditTrackingMapper {

    AuditTrackingMapper INSTANCE = Mappers.getMapper( AuditTrackingMapper.class );

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
//    @Mapping(target = "xTicketId",          source = "source.xTicketId")
//    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "requestTime",        source = "source.requestTime")
    @Mapping(target = "requestURI",         source = "source.requestUri")
    @Mapping(target = "requestMethod",      source = "source.requestMethod")
    @Mapping(target = "requestUserName",    source = "source.requestUserName")
    @Mapping(target = "payload",            source = "source.payload")
    AuditResponse entityToResponse(AuditEntity source);

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "requestTime",        source = "source.requestTime")
    @Mapping(target = "requestURI",         source = "source.requestUri")
    @Mapping(target = "requestMethod",      source = "source.requestMethod")
    @Mapping(target = "requestUserName",    source = "source.requestUserName")
    @Mapping(target = "payload",            source = "source.payload")
    List<AuditResponse> listEntityToListResponse(List<AuditEntity> source);

//    default OffsetDateTime getNewOffsetDateTime() {
//        return OffsetDateTime.now();
//    }
//    default String getNewOffsetDateTimeFormatString() {
//        return OffsetDateTime.now().toString();
//    }
//
//    default String getIsAccountExpired(ClientsBusinessEntity source) {
//        return String.valueOf(source.isAccountExpired());
//    }
//
//    default String getIsAccountLocked(ClientsBusinessEntity source) {
//        return String.valueOf(source.isAccountLocked());
//    }
//
//    default String getIsCredentialsExpired(ClientsBusinessEntity source) {
//        return String.valueOf(source.isCredentialsExpired());
//    }
//
//    default String getIsEnabled(ClientsBusinessEntity source) {
//        return String.valueOf(source.isEnabled());
//    }



//    @Mapping(target = "registeredId",               source = "source.id")
//    @Mapping(target = "clientId",                   source = "source.clientId")
//    @Mapping(target = "document",                   source = "source.document")
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



//    @Mapping(target = "registeredId",               source = "source.id")
//    @Mapping(target = "clientId",                   source = "source.clientId")
//    @Mapping(target = "document",                   source = "source.document")
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




}
