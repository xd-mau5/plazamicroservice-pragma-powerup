package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;

public interface IDishesPersistencePort {
    void createDish(Dishes dishes);
    void updateDish(Long id, UpdateDish updateDish);
    void updateDishStatus(Long id, boolean status);
}
