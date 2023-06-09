package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrdersRepository extends JpaRepository<OrderEntity, Long> {
    @Override
    @NotNull
    Optional<OrderEntity> findById(@NotNull Long id);
    Optional<OrderEntity> findByUserEntityIdAndStatus(Long idUser, String status);
    Optional<OrderEntity> findByRestaurantEntity (Long idRestaurant);
}
