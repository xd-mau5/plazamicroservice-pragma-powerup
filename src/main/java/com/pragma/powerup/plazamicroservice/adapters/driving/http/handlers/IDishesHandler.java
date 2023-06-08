package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.UpdateDishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishesResponseDto;

import java.util.List;

public interface IDishesHandler {
    void createDish(DishesRequestDto dishesRequestDto);
    void updateDish(Long id, UpdateDishesRequestDto updateDishesRequestDto);
    void updateDishStatus(Long id, boolean status);
    List<DishesResponseDto> getAllDishes(Long restaurantId, Integer page, Integer size, String category);
}
