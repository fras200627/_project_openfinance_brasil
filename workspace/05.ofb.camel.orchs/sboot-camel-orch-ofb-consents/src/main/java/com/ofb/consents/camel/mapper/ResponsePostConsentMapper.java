package com.ofb.consents.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponsePostConsentMapper {

    ResponsePostConsentMapper INSTANCE = Mappers.getMapper(ResponsePostConsentMapper.class);

    @Mapping(target = "data",  source = "source.data")
    @Mapping(target = "meta",  source = "source.meta")
    @Mapping(target = "links", source = "source.links")
    com.ofb.consents.server.model.ResponseConsent responseConsentExtends(
        com.ofb.consents.client.consents.model.ResponseConsent source);
}
