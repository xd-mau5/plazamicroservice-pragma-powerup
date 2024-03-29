package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrdersRepository extends JpaRepository<OrderEntity, Long> {
    @Override
    @NotNull
    Optional<OrderEntity> findById(@NotNull Long id);
    Optional<OrderEntity> findByUserEntityIdAndStatus(Long idUser, String status);
    Page<OrderEntity> findAll(Specification<OrderEntity> combinedSpecification, Pageable pagination);
}
