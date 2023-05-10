package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Category;
import com.pragma.powerup.plazamicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.api.ICategoryServicePort;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createCategory(Category category) {
        categoryPersistencePort.createCategory(category);

    }
}