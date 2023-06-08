package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishesEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishesRepository extends JpaRepository<DishesEntity, Long> {
    Optional<DishesEntity> findByName(String name);
    @NotNull Optional<DishesEntity> findById(@NotNull Long id);
    Optional<DishesEntity> findByIdAndRestaurantEntity (Long id, Long idRestaurant);
    Page<DishesEntity> findAll(Specification<DishesEntity> combinedSpecification, Pageable pagination);
}
