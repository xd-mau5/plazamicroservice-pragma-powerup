package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishesOrderedResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesOrderedResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "idOrder", source = "idOrder")
    @Mapping(target = "idDish", source = "idDish")
    @Mapping(target = "quantity", source = "quantity")
    DishesOrderedResponseDto toResponse(DishesOrderedResponseDto dishesOrderedResponseDto);
}
