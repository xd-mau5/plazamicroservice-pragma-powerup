package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.DishesAlreadyExistException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.InvalidDishesNameException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishesEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
    public void updateDish(Long id, UpdateDish updateDish) {
        if (id == null) {
            throw new NullPointerException();
        }
        if (updateDish.getDescription().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (updateDish.getPrice() < 0) {
            throw new IllegalArgumentException();
        }
        if (dishesRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }
        Optional<DishesEntity> dishesOptional = dishesRepository.findById(id);
        if (dishesOptional.isPresent()) {
            DishesEntity dishes = dishesOptional.get();
            dishes.setDescription(updateDish.getDescription());
            dishes.setPrice(updateDish.getPrice());
            dishesRepository.save(dishes);
        }
    }

}
