package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesEntityMapper {

    @Mapping(target = "categoryEntity", source = "dishes.categoryId", qualifiedByName = "idToCategoryEntity")
    @Mapping(target = "restaurantEntity", source = "dishes.restaurantId", qualifiedByName = "idToRestaurantEntity")
    @Mapping(target = "name", source = "dishes.name")
    @Mapping(target = "price", source = "dishes.price")
    @Mapping(target = "description", source = "dishes.description")
    @Mapping(target = "imageUrl", source = "dishes.imageUrl")
    @Mapping(target = "active", source = "dishes.active")
    DishesEntity toEntity(Dishes dishes);
    List<Dishes> toDishesList(List<DishesEntity> dishesEntities);
    Dishes toDishes(DishesEntity dishesEntity);

    @Named("idToCategoryEntity")
    static CategoryEntity idToCategoryEntity(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        return categoryEntity;
    }

    @Named("idToRestaurantEntity")
    static RestaurantEntity idToRestaurantEntity(Long restaurantId) {
        if (restaurantId == null) {
            return null;
        }
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurantId);
        return restaurantEntity;
    }
}
