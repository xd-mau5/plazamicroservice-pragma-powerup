package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesOrderedRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishesOrderedHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishesOrderedRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishesOrderedResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IDishesOrderedServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishesOrdersHandlerImpl implements IDishesOrderedHandler {
    private final IDishesOrderedServicePort dishesOrderedServicePort;
    private final IDishesOrderedRequestMapper dishesOrderedRequestMapper;
    private final IDishesOrderedResponseMapper dishesOrderedResponseMapper;

    @Override
    public void saveDishesOrdered(DishesOrderedRequestDto dishesOrderedRequestDto) {
        dishesOrderedServicePort.saveDishesOrdered(dishesOrderedRequestMapper.toDishesOrdered(dishesOrderedRequestDto));
    }
}
