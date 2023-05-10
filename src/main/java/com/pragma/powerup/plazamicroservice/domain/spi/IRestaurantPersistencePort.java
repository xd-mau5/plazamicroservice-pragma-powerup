package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);
}
