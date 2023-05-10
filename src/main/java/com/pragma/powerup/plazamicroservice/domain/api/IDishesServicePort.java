package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Dishes;

public interface IDishesServicePort {
    void createDish(Dishes dish);
}
