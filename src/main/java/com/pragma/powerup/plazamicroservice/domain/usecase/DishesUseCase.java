package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IDishesServicePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.model.Dishes;

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
    public void updateDish(Long id, float price, String description) {
        dishesPersistencePort.updateDish(id, price, description);
    }
}
