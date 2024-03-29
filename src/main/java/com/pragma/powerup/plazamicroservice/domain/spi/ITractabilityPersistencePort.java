package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Tractability;

import java.util.List;
import java.util.Map;

public interface ITractabilityPersistencePort {
    void createTractability(Tractability tractability);
    List<Tractability> findAllByClientId(Long clientId);
    List<Tractability> getAllTractabilities(Integer page, Integer size);
    String calculateDurationPerOrder(Long orderId);
    Map<Long, String> calculateAverageDurationPerEmployee();
}
