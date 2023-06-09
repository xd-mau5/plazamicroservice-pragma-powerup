package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

public interface IOrderPersistencePort {
    void saveOrder(Orders orders);
    void updateOrder(Long idOrder, String status);
    void setOrderToEmployee(Long idOrder, Long idEmployee);
}
