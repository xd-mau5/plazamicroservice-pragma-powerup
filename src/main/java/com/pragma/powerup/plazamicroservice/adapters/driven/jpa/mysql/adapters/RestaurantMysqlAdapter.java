package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.InvalidRestaurantNameException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantAlreadyExistException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.UserIsNotAOwnerException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import com.pragma.powerup.plazamicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import javax.management.modelmbean.InvalidTargetObjectTypeException;

@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public void createRestaurant(Restaurant restaurant, User owner) {
        if (owner.getRole().getId().equals(1L)) {
            throw new UserIsNotAOwnerException();
        }
        if (restaurantRepository.findByRestaurant(restaurant.getName()).isEmpty()) {
            throw new InvalidRestaurantNameException();
        }
        if (restaurantRepository.findByRestaurant(restaurant.getName()).isPresent()) {
            throw new RestaurantAlreadyExistException();
        }
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
        //TODO: Probar si esto funciona
    }

    }

