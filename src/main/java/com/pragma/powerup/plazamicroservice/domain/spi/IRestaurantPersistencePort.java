package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazamicroservice.domain.model.User;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant, User user);
}
