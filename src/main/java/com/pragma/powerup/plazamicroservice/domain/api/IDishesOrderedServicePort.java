package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;

public interface IDishesOrderedServicePort {
    void saveDishesOrdered(DishesOrdered dishesOrdered);
}
