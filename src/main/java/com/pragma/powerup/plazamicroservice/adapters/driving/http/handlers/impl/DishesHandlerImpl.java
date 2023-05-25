package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.UpdateDishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishesHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishesRequestMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IDishesServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishesHandlerImpl implements IDishesHandler {
    private final IDishesServicePort dishesServicePort;
    private final IDishesRequestMapper dishesRequestMapper;

    @Override
    public void createDish(DishesRequestDto dishesRequestDto) {
        dishesServicePort.createDish(dishesRequestMapper.toDishes(dishesRequestDto));
    }
    @Override
    public void updateDish(Long id, UpdateDishesRequestDto updateDishesRequestDto) {
        dishesServicePort.updateDish(id, dishesRequestMapper.updateDishes(updateDishesRequestDto) );
    }
}
