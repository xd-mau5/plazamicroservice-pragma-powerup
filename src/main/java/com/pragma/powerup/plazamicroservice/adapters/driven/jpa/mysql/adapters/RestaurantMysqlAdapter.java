package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.InvalidRestaurantNameException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantAlreadyExistException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.UserIsNotAOwnerException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazamicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    @Override
    public void createRestaurant(Restaurant restaurant) {
        if (restaurant.getName().isEmpty()) {
            throw new InvalidRestaurantNameException();
        }
        if (restaurantRepository.findByName(restaurant.getName()).isPresent()) {
            throw new RestaurantAlreadyExistException();
        }
        if (restaurantRepository.findByNit(Long.getLong(restaurant.getNit())).isPresent()) {
            throw new RestaurantAlreadyExistException();
        }
        if (restaurant.getOwnerId() == null) {
            throw new UserIsNotAOwnerException();
        }
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntitiesList = restaurantRepository.findAll();
        if (restaurantEntitiesList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantList(restaurantEntitiesList);
    }
}

