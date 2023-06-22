package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishesOrderedEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesOrderedRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishesRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IOrdersRepository;
import com.pragma.powerup.plazamicroservice.domain.model.DishesOrdered;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishesOrderedPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class DishesOrderedMysqlAdapter implements IDishesOrderedPersistencePort {
    private final IDishesOrderedRepository dishesOrderedRepository;
    private final IDishesOrderedEntityMapper dishesOrderedEntityMapper;
    private final IDishesRepository dishesRepository;
    private final IOrdersRepository ordersRepository;

    @Override
    public void saveDishesOrdered(DishesOrdered dishesOrdered) {
        if (dishesOrdered == null) {
            throw new IllegalArgumentException("DishesOrdered can't be null");
        }

        Optional<OrderEntity> orderOptional = ordersRepository.findById(dishesOrdered.getIdOrder());
        if (orderOptional.isEmpty()) {
            throw new IllegalArgumentException("Order doesn't exist");
        }

        OrderEntity orderEntity = orderOptional.get();
        Long restaurantId = orderEntity.getRestaurantEntity().getId();

        Optional<DishesEntity> dishesOptional = dishesRepository.findByIdAndRestaurantEntityId(
                dishesOrdered.getIdDish(), restaurantId);
        if (dishesOptional.isEmpty()) {
            throw new IllegalArgumentException("Dish doesn't exist in the restaurant");
        }

        dishesOrderedRepository.save(dishesOrderedEntityMapper.toEntity(dishesOrdered));
    }

}