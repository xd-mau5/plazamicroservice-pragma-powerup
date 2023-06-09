package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;

public interface IOrdersHandler {
    void saveOrder(OrderRequestDto orderRequestDto);
    void updateOrder(Long idOrder, String status);
    void setOrderToEmployee(Long idOrder, Long idEmployee);
}
