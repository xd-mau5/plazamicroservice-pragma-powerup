package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesOrderedRequestDto;
import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishesOrderedRequestMapper {
    @Mapping(target = "idOrder", source = "idOrder")
    @Mapping(target = "idDish", source = "idDish")
    @Mapping(target = "quantity", source = "quantity")
    DishesOrdered toDishesOrdered(DishesOrderedRequestDto dishesOrderedRequestDto);

}
