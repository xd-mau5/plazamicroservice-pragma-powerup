package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesOrderedEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishesOrderedRepository extends JpaRepository<DishesOrderedEntity, Long> {
    @Override
    @NotNull
    Optional<DishesOrderedEntity> findById(@NotNull Long id);
}
