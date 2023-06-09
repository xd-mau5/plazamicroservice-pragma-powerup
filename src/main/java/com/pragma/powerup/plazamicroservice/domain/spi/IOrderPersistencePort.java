package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Orders orders);
    void updateOrder(Long idOrder, String status);
    void setOrderToEmployee(Long idOrder, Long idEmployee);
    List<Orders> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size);
}
