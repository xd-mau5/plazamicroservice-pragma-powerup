package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IDishesOrderedServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesOrderedPersistencePort;

public class DishesOrderedUseCase implements IDishesOrderedServicePort {
    private final IDishesOrderedPersistencePort dishesOrderedPersistencePort;

    public DishesOrderedUseCase(IDishesOrderedPersistencePort dishesOrderedPersistencePort) {
        this.dishesOrderedPersistencePort = dishesOrderedPersistencePort;
    }
    @Override
    public void saveDishesOrdered(DishesOrdered dishesOrdered) {
        dishesOrderedPersistencePort.saveDishesOrdered(dishesOrdered);
    }
}
