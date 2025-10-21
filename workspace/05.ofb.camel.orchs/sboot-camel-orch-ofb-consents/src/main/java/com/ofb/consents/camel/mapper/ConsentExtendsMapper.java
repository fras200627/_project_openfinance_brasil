package com.ofb.consents.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsentExtendsMapper {

    ConsentExtendsMapper INSTANCE = Mappers.getMapper(ConsentExtendsMapper.class);

    com.ofb.consents.client.consents.model.CreateConsentExtensions consentExtendsServerToConsentExtendsClient(
        com.ofb.consents.server.model.CreateConsentExtensions source);

}
