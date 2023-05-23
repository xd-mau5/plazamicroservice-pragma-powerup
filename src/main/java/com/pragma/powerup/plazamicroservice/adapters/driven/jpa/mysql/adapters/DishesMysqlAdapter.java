package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.DishesAlreadyExistException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.InvalidDishesNameException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishesEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishesMysqlAdapter implements IDishesPersistencePort {
    private final IDishesRepository dishesRepository;
    private final IDishesEntityMapper dishesEntityMapper;
    @Override
    public void createDish(Dishes dishes) {
        if (dishes.getName().isEmpty()) {
            throw new InvalidDishesNameException();
        }
        if (dishesRepository.findByName(dishes.getName()).isPresent()) {
            throw new DishesAlreadyExistException();
        }
        dishesRepository.save(dishesEntityMapper.toEntity(dishes));
    }
    @Override
    public void updateDish(Long id, float price, String description) {
        if (id == null) {
            throw new NullPointerException();
        }
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (dishesRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        DishesEntity dishes = dishesRepository.findById(id).get();
        dishes.setDescription(dishes.getDescription());
        dishes.setPrice(dishes.getPrice());
    }

}
