package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(Orders orders);
    void updateOrder(Long idOrder, String status);
    void setOrderToEmployee(Long idOrder, Long idEmployee);
    List<Orders> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size);
}
