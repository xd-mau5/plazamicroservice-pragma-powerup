package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
}
