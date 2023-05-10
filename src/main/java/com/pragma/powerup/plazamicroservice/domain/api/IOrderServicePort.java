package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Orders;

public interface IOrderServicePort {
    void createOrder(Orders orders);
}
