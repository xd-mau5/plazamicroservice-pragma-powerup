package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Dishes;

public interface IDishesPersistencePort {
    void createDish(Dishes dishes);
}
