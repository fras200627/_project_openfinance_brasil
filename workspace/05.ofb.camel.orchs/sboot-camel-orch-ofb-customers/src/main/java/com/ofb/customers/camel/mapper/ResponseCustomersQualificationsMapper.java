package com.ofb.customers.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseCustomersQualificationsMapper {

    ResponseCustomersQualificationsMapper INSTANCE = Mappers.getMapper(ResponseCustomersQualificationsMapper.class);

    @Mapping(target = "data",  source = "source.data")
    @Mapping(target = "meta",  source = "source.meta")
    @Mapping(target = "links", source = "source.links")
    com.ofb.customers.server.api.model.ResponsePersonalCustomersQualification responseCustommerQualifications(
    com.ofb.customers.client.customers.model.ResponsePersonalCustomersQualification source);

}
