package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    @Mapping(target = "userEntity", source = "restaurant.ownerId", qualifiedByName = "idToUserEntity")
    @Mapping(target = "address", source = "restaurant.address")
    @Mapping(target = "name", source = "restaurant.name")
    @Mapping(target = "nit", source = "restaurant.nit")
    RestaurantEntity toEntity(Restaurant restaurant);
    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantEntities);
    @Named("idToUserEntity")
    static UserEntity idToUserEntity(Long ownerId) {
        if (ownerId == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(ownerId);
        return userEntity;
    }
}
