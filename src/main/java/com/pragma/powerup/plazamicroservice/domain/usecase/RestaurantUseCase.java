package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazamicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }
    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.createRestaurant(restaurant);
    }
    @Override
    public List<Restaurant> getAllRestaurants(Integer page, Integer size) {
        return restaurantPersistencePort.getAllRestaurants(page, size);
    }
}
