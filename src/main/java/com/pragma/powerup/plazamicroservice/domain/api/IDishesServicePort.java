package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Dishes;
import com.pragma.powerup.plazamicroservice.domain.model.UpdateDish;

import java.util.List;

public interface IDishesServicePort {
    void createDish(Dishes dish);
    void updateDish(Long id, UpdateDish updateDish);
    void updateDishStatus(Long id, boolean status);
    List<Dishes> getAllDishes(Long restaurantId, Integer page, Integer size, String category);
}
