package com.pragma.powerup.plazamicroservice.adapters.driven.mongo.repository;

import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.entity.TractabilityEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITractabilityRepository extends MongoRepository<TractabilityEntity, Long> {
    @NotNull List<TractabilityEntity> findAll();
    @NotNull List<TractabilityEntity> findAllByClientId (Long clientId);
    @NotNull List<TractabilityEntity> findAllByOrderId(Long orderId);
}
