package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;

public interface IDishesServicePort {
    void createDish(Dishes dish);
    void updateDish(Long id, UpdateDish updateDish);
}
