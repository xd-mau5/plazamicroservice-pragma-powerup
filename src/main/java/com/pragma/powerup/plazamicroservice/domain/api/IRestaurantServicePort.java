package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
}
