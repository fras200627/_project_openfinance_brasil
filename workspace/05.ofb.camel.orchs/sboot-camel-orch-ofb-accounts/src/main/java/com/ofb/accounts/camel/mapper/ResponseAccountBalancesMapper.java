package com.ofb.accounts.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseAccountBalancesMapper {

    ResponseAccountBalancesMapper INSTANCE = Mappers.getMapper(ResponseAccountBalancesMapper.class);

    @Mapping(target = "data",  source = "source.data")
    @Mapping(target = "meta",  source = "source.meta")
    @Mapping(target = "links", source = "source.links")
    com.ofb.accounts.server.api.model.ResponseAccountBalances responseAccountBalances(
    com.ofb.accounts.client.accounts.model.ResponseAccountBalances source);

}
