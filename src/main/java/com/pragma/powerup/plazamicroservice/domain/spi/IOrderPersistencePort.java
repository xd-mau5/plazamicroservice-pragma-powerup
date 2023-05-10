package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

public interface IOrderPersistencePort {
    void createOrder(Orders orders);
}
