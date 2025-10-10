package com.ofb.customers.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseCustomersFinancialsMapper {

    ResponseCustomersFinancialsMapper INSTANCE = Mappers.getMapper(ResponseCustomersFinancialsMapper.class);

    @Mapping(target = "data",  source = "source.data")
    @Mapping(target = "meta",  source = "source.meta")
    @Mapping(target = "links", source = "source.links")
    com.ofb.customers.server.api.model.ResponsePersonalCustomersFinancialRelation responseCustommerFinancials(
    com.ofb.customers.client.customers.model.ResponsePersonalCustomersFinancialRelation source);

}
