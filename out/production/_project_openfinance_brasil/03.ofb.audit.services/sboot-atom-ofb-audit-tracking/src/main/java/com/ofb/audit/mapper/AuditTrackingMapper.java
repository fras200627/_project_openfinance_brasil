package com.ofb.audit.mapper;

import com.ofb.audit.entity.AuditEntity;
import com.ofb.audit.server.model.AuditResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuditTrackingMapper {

    AuditTrackingMapper INSTANCE = Mappers.getMapper( AuditTrackingMapper.class );

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

    @Mapping(target = "id",                 source = "source.id")
    @Mapping(target = "createAt",           source = "source.createAt")
    @Mapping(target = "xTicketId",          source = "source.xTicketId")
    @Mapping(target = "xFapiInteractionId", source = "source.xFapiInteractionId")
    @Mapping(target = "requestTime",        source = "source.requestTime")
    @Mapping(target = "requestURI",         source = "source.requestUri")
    @Mapping(target = "requestMethod",      source = "source.requestMethod")
    @Mapping(target = "requestUserName",    source = "source.requestUserName")
    @Mapping(target = "payload",            source = "source.payload")
    AuditResponse entityToResponse(AuditEntity source);

}
