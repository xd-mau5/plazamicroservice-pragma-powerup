package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(target = "personEntity.id", source = "person.id")
    @Mapping(target = "roleEntity.id", source = "role.id")
    UserEntity toEntity(User user);

    @Mapping(target = "person.id", source = "personEntity.id")
    @Mapping(target = "role.id", source = "roleEntity.id")
    User toUser(UserEntity userEntity);
}