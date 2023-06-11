package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrdersHandler {
    void saveOrder(OrderRequestDto orderRequestDto);
    void updateOrder(Long idOrder, String status);
    List<OrderResponseDto> setOrderToEmployee(Long idOrder, Long idEmployee, String status, Integer page, Integer size);
    List<OrderResponseDto> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size);
}
