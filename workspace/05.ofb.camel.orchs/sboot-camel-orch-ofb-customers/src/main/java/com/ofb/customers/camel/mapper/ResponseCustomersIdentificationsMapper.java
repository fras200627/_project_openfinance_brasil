package com.ofb.customers.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseCustomersIdentificationsMapper {

    ResponseCustomersIdentificationsMapper INSTANCE = Mappers.getMapper(ResponseCustomersIdentificationsMapper.class);

    @Mapping(target = "data",  source = "source.data")
    @Mapping(target = "meta",  source = "source.meta")
    @Mapping(target = "links", source = "source.links")
    com.ofb.customers.server.api.model.ResponsePersonalCustomersIdentification responseCustommerIdentifications(
    com.ofb.customers.client.customers.model.ResponsePersonalCustomersIdentification source);

}
