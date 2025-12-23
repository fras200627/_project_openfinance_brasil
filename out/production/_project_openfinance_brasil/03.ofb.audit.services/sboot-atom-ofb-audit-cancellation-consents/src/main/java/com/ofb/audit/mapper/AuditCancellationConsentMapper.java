package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditCancellationConsentEntity;
import com.ofb.audit.server.model.AuditResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuditCancellationConsentMapper {

    AuditCancellationConsentMapper INSTANCE = Mappers.getMapper( AuditCancellationConsentMapper.class );

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "consentId",          source = "source.consentId")
    @Mapping(target = "consentRequestDate", source = "source.consentRequestDate")
    @Mapping(target = "consentCancelDate",  source = "source.consentCancelDate")
    @Mapping(target = "reason",             source = "source.reason")
    @Mapping(target = "payload",            source = "source.payload")
    List<AuditResponse> listEntityToListResponse(List<AuditCancellationConsentEntity> source);

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "consentId",          source = "source.consentId")
    @Mapping(target = "consentRequestDate", source = "source.consentRequestDate")
    @Mapping(target = "consentCancelDate",  source = "source.consentCancelDate")
    @Mapping(target = "reason",             source = "source.reason")
    @Mapping(target = "payload",            source = "source.payload")
    AuditResponse entityToResponse(AuditCancellationConsentEntity source);

}
