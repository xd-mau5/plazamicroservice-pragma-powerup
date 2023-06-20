package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Tractability;

import java.util.List;

public interface ITractabilityPersistencePort {
    void createTractability(Tractability tractability);
    List<Tractability> findAllByClientId(Long clientId);
    List<Tractability> getAllTractabilities(Integer page, Integer size);
}
