package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishesOrderedEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IOrdersEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesOrderedRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IOrdersRepository;
import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesOrderedPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DishesOrderedMysqlAdapter implements IDishesOrderedPersistencePort {
    private final IDishesOrderedRepository dishesOrderedRepository;
    private final IDishesOrderedEntityMapper dishesOrderedEntityMapper;
    private final IDishesRepository dishesRepository;
    private final IOrdersRepository ordersRepository;
    private final IOrdersEntityMapper ordersEntityMapper;

    @Override
    public void saveDishesOrdered(DishesOrdered dishesOrdered) {
        if (dishesOrdered == null) {
            throw new IllegalArgumentException("DishesOrdered can't be null");
        }
        if (!dishesRepository.findByIdAndRestaurantEntityId(
                dishesOrdered.getIdDish(),
                ordersRepository.findById(dishesOrdered.getIdOrder()).get()
                        .getRestaurantEntity().getId()).isPresent()
        ) {
            throw new IllegalArgumentException("Dish doesn't exist in the restaurant");
        }
        dishesOrderedRepository.save(dishesOrderedEntityMapper.toEntity(dishesOrdered));
    }
}