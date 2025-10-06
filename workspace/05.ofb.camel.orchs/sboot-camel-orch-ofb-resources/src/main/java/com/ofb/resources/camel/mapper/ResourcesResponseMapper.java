package com.ofb.resources.camel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourcesResponseMapper {

    ResourcesResponseMapper INSTANCE = Mappers.getMapper(ResourcesResponseMapper.class);

//
//    @Mapping(target = "data",  expression = "java(responseListToResponseResourceListDataInner(source.data))")
//    @Mapping(target = "meta",  expression = "java(responseMetaToResponseMetaResource(source.meta))")
//    @Mapping(target = "links",  expression = "java(responseLinksToResponseResourceLinks(source.links))")
//    com.ofb.resources.server.api.model.ResponseResourceList responseListToResponseResource(
//            com.ofb.resources.client.resources.model.ResponseResourceList source);

    @Mapping(target = "resourceId",   source = "source.resourceId")
    @Mapping(target = "status",       source = "source.status")
    @Mapping(target = "type",         source = "source.type")
    List<com.ofb.resources.server.api.model.ResponseResourceListDataInner> responseListToResponseResourceListDataInner(
            List<com.ofb.resources.client.resources.model.ResponseResourceListDataInner> source);

    @Mapping(target = "self",   source = "source.self")
    @Mapping(target = "first",  source = "source.first")
    @Mapping(target = "prev",   source = "source.prev")
    @Mapping(target = "next",   source = "source.next")
    @Mapping(target = "last",   source = "source.last")
    com.ofb.resources.server.api.model.Links responseLinksToResponseResourceLinks(
            com.ofb.resources.client.resources.model.Links source);

    @Mapping(target = "requestDateTime", source = "source.requestDateTime")
    @Mapping(target = "totalRecords",    source = "source.totalRecords")
    @Mapping(target = "totalPages",      source = "source.totalPages")
    com.ofb.resources.server.api.model.MetaResponse responseMetaToResponseMetaResource(
            com.ofb.resources.client.resources.model.MetaResponse source);
}
