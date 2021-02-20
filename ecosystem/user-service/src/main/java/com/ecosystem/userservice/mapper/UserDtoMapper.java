package com.ecosystem.userservice.mapper;

import com.ecosystem.userservice.domain.UserDto;
import com.ecosystem.userservice.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(source = "userEntity.id", target = "userDto.id")
    @Mapping(source = "userEntity.firstname", target = "userDto.firstname")
    @Mapping(source = "userEntity.lastname", target = "userDto.lastname")
    @Mapping(source = "userEntity.score", target = "userDto.score")
    @Mapping(source = "userEntity.rank", target = "userDto.rank")
    List<UserDto> from(List<UserEntity> userEntity);

}



