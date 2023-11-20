package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IDishesServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;

import java.util.List;

public class DishesUseCase implements IDishesServicePort {
    private final IDishesPersistencePort dishesPersistencePort;
    public DishesUseCase(IDishesPersistencePort dishesPersistencePort) {
        this.dishesPersistencePort = dishesPersistencePort;
    }
    @Override
    public void createDish(Dishes dishes) {
        dishesPersistencePort.createDish(dishes);
    }
    @Override
    public void updateDish(Long id, UpdateDish updateDish) {
        dishesPersistencePort.updateDish(id, updateDish);
    }
    @Override
    public void updateDishStatus(Long id, boolean status) {
        dishesPersistencePort.updateDishStatus(id, status);
    }
    @Override
    public List<Dishes> getAllDishes(Long restaurantId, Integer page, Integer size, String category) {
        return dishesPersistencePort.getAllDishes(restaurantId, page, size, category);
    }
}
