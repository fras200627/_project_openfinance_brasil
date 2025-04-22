package com.tican.oauth2.users.mapper;

import com.tican.oauth2.users.api.server.model.UserResponse;
import com.tican.oauth2.users.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface UserBusinessMapper {

    UserBusinessMapper INSTANCE = Mappers.getMapper( UserBusinessMapper.class);

    @Mapping(target = "id"        , source = "source.id")
    @Mapping(target = "name"      , source = "source.name")
    @Mapping(target = "eMail"     , source = "source.email")
    @Mapping(target = "type"      , source = "source.type")
    @Mapping(target = "createdAt" , source = "source.createdAt")
    @Mapping(target = "status"    , source = "source.status")
    UserResponse entityToUserResponse(UserEntity source);

    @Mapping(target = "id"        , source = "source.id")
    @Mapping(target = "name"      , source = "source.name")
    @Mapping(target = "eMail"     , source = "source.email")
    @Mapping(target = "type"      , source = "source.type")
    @Mapping(target = "createdAt" , source = "source.createdAt")
    @Mapping(target = "status"    , source = "source.status")
    List<UserResponse> listEntityToListUserResponse(List<UserEntity> source);

}
