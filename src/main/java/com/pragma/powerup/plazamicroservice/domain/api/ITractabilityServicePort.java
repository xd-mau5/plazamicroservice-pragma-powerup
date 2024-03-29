package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Tractability;

import java.util.List;
import java.util.Map;

public interface ITractabilityServicePort {
    void createTractability(Tractability tractability);
    List<Tractability> findAllByClientId(Long clientId);
    List<Tractability> getAllTractabilities(Integer page, Integer size);
    String calculateDurationPerOrder(Long orderId);
    Map<Long, String> calculateAverageDurationPerEmployee();
}
