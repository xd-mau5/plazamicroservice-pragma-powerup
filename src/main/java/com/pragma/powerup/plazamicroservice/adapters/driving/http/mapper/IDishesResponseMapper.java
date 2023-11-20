package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishesResponseDto;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    DishesResponseDto toResponse(DishesResponseDto dishesResponseDto);
    List<DishesResponseDto> toResponseList(List<Dishes> dishesList);
}
