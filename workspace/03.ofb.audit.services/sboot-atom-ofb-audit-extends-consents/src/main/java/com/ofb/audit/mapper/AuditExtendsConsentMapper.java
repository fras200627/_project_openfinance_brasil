package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditExtendsConsentEntity;
import com.ofb.audit.server.model.AuditResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuditExtendsConsentMapper {

    AuditExtendsConsentMapper INSTANCE = Mappers.getMapper( AuditExtendsConsentMapper.class );

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "consentId",          source = "source.consentId")
    @Mapping(target = "consentRequestDate", source = "source.consentRequestDate")
    @Mapping(target = "consentExtendsDate", source = "source.consentExtendsDate")
    @Mapping(target = "payload",            source = "source.payload")
    List<AuditResponse> listEntityToListResponse(List<AuditExtendsConsentEntity> source);

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "consentId",          source = "source.consentId")
    @Mapping(target = "consentRequestDate", source = "source.consentRequestDate")
    @Mapping(target = "consentExtendsDate", source = "source.consentExtendsDate")
    @Mapping(target = "payload",            source = "source.payload")
    AuditResponse entityToResponse(AuditExtendsConsentEntity source);

}
