package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesOrderedRequestDto;

public interface IDishesOrderedHandler {
    void saveDishesOrdered(DishesOrderedRequestDto dishesOrderedRequestDto);
}
