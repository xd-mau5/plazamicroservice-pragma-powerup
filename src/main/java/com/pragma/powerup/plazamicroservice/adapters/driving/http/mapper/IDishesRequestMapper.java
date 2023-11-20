package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.UpdateDishesRequestDto;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesRequestMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "restaurantId", source = "restaurantId")
    Dishes toDishes(DishesRequestDto dishesRequestDto);
    UpdateDish updateDishes(UpdateDishesRequestDto updateDishesRequestDto);
}
