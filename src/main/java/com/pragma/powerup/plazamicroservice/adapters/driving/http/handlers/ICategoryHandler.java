package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.CategoryRequestDto;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);
}
