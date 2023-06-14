package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Orders orders);
    void updateOrder(Long idOrder, String status);
    List<Orders> setOrderToEmployee(Long idOrder, Long idEmployee, String status, Integer page, Integer size);
    List<Orders> getAllOrdersByStatus(Long restaurantId, String status, Integer page, Integer size);
    void sendMessageToUser(Long idOrder);
    String generateSecurityCode(Long idOrder, String status);
    boolean checkSecurityCode(Long idOrder, String securityCode);
}
