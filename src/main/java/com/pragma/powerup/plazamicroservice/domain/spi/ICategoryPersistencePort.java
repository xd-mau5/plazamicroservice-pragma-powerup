package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Category;

public interface ICategoryPersistencePort {
    void createCategory(Category category);
}
