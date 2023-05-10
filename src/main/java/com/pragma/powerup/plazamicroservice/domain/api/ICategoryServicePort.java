package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Category;

public interface ICategoryServicePort {
    void createCategory(Category category);
}
