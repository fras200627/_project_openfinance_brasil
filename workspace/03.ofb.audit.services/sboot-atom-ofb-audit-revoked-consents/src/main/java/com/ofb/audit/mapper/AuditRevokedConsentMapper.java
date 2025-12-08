package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditRevokedConsentEntity;
import com.ofb.audit.server.model.AuditResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuditRevokedConsentMapper {

    AuditRevokedConsentMapper INSTANCE = Mappers.getMapper( AuditRevokedConsentMapper.class );

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "consentId",          source = "source.consentId")
    @Mapping(target = "consentRequestDate", source = "source.consentRequestDate")
    @Mapping(target = "consentRevokedDate", source = "source.consentRevokedDate")
    @Mapping(target = "reason",             source = "source.reason")
    @Mapping(target = "payload",            source = "source.payload")
    List<AuditResponse> listEntityToListResponse(List<AuditRevokedConsentEntity> source);

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "consentId",          source = "source.consentId")
    @Mapping(target = "consentRequestDate", source = "source.consentRequestDate")
    @Mapping(target = "consentRevokedDate", source = "source.consentRevokedDate")
    @Mapping(target = "reason",             source = "source.reason")
    @Mapping(target = "payload",            source = "source.payload")
    AuditResponse entityToResponse(AuditRevokedConsentEntity source);

}
