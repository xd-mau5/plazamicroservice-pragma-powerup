package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrdersEntityMapper {
    @Mapping(target = "id", source = "orders.id")
    @Mapping(target = "userEntity.id", source = "orders.userId", qualifiedByName = "idToUserEntity")
    @Mapping(target = "restaurantEntity.id", source = "orders.restaurantId", qualifiedByName = "idToRestaurantEntity")
    @Mapping(target = "status", source = "orders.status")
    @Mapping(target = "chefEntity.id", source = "orders.chefId", qualifiedByName = "idToChefEntity")
    @Mapping(target = "orderDate", source = "orders.orderDate")
    OrderEntity toEntity(Orders orders);
    @Named("idToUserEntity")
    default Long idToUserEntity(Long idUser) {
        return idUser;
    }
    @Named("idToRestaurantEntity")
    default Long idToRestaurantEntity(Long idRestaurant) {
        return idRestaurant;
    }
    @Named("idToChefEntity")
    default Long idToChefEntity(Long idChef) {
        return idChef;
    }
}
