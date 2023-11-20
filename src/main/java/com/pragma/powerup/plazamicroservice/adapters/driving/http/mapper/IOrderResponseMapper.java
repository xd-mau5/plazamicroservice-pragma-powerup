package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "userId", source = "userId")
    OrderResponseDto toResponse(OrderResponseDto orderResponseDto);
    List<OrderResponseDto> toResponseList(List<Orders> ordersList);
}
