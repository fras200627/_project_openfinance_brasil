package com.ofb.consents.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponsePostConsentExtendsMapper {

    ResponsePostConsentExtendsMapper INSTANCE = Mappers.getMapper(ResponsePostConsentExtendsMapper.class);

    @Mapping(target = "data",  source = "source.data")
    @Mapping(target = "meta",  source = "source.meta")
    @Mapping(target = "links", source = "source.links")
    com.ofb.consents.server.model.ResponseConsentExtensions responseConsentExtends(
        com.ofb.consents.client.consents.model.ResponseConsentExtensions source);
}
