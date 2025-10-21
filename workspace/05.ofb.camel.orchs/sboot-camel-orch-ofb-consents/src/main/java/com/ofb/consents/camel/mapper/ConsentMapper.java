package com.ofb.consents.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsentMapper {

    ConsentMapper INSTANCE = Mappers.getMapper(ConsentMapper.class);

    com.ofb.consents.client.consents.model.CreateConsent consentServerToConsentClient(
        com.ofb.consents.server.model.CreateConsent source);

}
