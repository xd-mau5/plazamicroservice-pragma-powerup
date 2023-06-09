package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapters;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IOrdersEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IOrdersRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Orders;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrdersRepository ordersRepository;
    private final IOrdersEntityMapper ordersEntityMapper;
    private final IUserRepository userRepository;
    private final IRestaurantRepository restaurantRepository;
    @Override
    public void saveOrder(Orders orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Orders can't be null");
        }
        if (orders.getId() != null) {
            throw new IllegalArgumentException("Orders id can't be null");
        }
        if (ordersRepository.findByUserEntityIdAndStatus(orders.getUserId(), "Pendiente").isPresent()) {
            throw new IllegalArgumentException("User already has an order pending");
        }
        if (orders.getRestaurantId() == null) {
            throw new IllegalArgumentException("Restaurant id can't be null");
        }
        RestaurantEntity restaurantEntity = restaurantRepository.findById(orders.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("Restaurant doesn't exist"));
        ordersEntityMapper.toEntity(orders).setRestaurantEntity(restaurantEntity);
        orders.setRestaurantId(restaurantEntity.getId());
        orders.setStatus("Pendiente");
        orders.setChefId(1L);
        ordersRepository.save(ordersEntityMapper.toEntity(orders));
    }
    @Override
    public void updateOrder(Long idOrder, String status){
        if (idOrder == null) {
            throw new IllegalArgumentException("IdOrder can't be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        if (ordersRepository.findById(idOrder).isEmpty()) {
            throw new IllegalArgumentException("Orders doesn't exist");
        }
        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order doesn't exist"));

        orderEntity.setStatus(status);
        ordersRepository.save(orderEntity);
    }
    @Override
    public void setOrderToEmployee(Long idOrder, Long idEmployee) {
        if (idOrder == null) {
            throw new IllegalArgumentException("IdOrder can't be null");
        }
        if (idEmployee == null) {
            throw new IllegalArgumentException("IdEmployee can't be null");
        }

        OrderEntity orderEntity = ordersRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order doesn't exist"));

        UserEntity chefEntity = userRepository.findById(idEmployee)
                .orElseThrow(() -> new IllegalArgumentException("Employee doesn't exist"));

        orderEntity.setChefEntity(chefEntity);
        ordersRepository.save(orderEntity);
    }

}
