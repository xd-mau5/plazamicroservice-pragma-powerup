package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.UpdateDishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishesResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishesHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishesRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishesResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IDishesServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishesHandlerImpl implements IDishesHandler {
    private final IDishesServicePort dishesServicePort;
    private final IDishesRequestMapper dishesRequestMapper;
    private final IDishesResponseMapper dishesResponseMapper;

    @Override
    public void createDish(DishesRequestDto dishesRequestDto) {
        dishesServicePort.createDish(dishesRequestMapper.toDishes(dishesRequestDto));
    }
    @Override
    public void updateDish(Long id, UpdateDishesRequestDto updateDishesRequestDto) {
        dishesServicePort.updateDish(id, dishesRequestMapper.updateDishes(updateDishesRequestDto) );
    }
    @Override
    public void updateDishStatus(Long id, boolean status){
        dishesServicePort.updateDishStatus(id, status);
    }
    @Override
    public List<DishesResponseDto> getAllDishes(Long restaurantId, Integer page, Integer size, String category) {
        return dishesResponseMapper.toResponseList(dishesServicePort.getAllDishes(restaurantId, page, size, category));
    }
}
