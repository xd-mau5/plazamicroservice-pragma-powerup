package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;

public interface IDishesOrderedPersistencePort {
    void saveDishesOrdered(DishesOrdered dishesOrdered);
}
