package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(Orders orders);
    void updateOrder(Long idOrder, String status);
    List<Orders> setOrderToEmployee(Long idOrder, Long idEmployee, String status, Integer page, Integer size);
    List<Orders> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size);
}
