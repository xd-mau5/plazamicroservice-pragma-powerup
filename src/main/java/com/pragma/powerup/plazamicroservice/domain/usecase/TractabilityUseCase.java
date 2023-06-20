package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.ITractabilityServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import com.pragma.powerup.plazamicroservice.domain.spi.ITractabilityPersistencePort;

import java.util.List;

public class TractabilityUseCase implements ITractabilityServicePort {
    private final ITractabilityPersistencePort tractabilityPersistencePort;

    public TractabilityUseCase(ITractabilityPersistencePort tractabilityPersistencePort) {
        this.tractabilityPersistencePort = tractabilityPersistencePort;
    }
    @Override
    public void createTractability(Tractability tractability) {
        tractabilityPersistencePort.createTractability(tractability);
    }
    @Override
    public List<Tractability> findAllByClientId(Long clientId){
        return tractabilityPersistencePort.findAllByClientId(clientId);
    }

    @Override
    public List<Tractability> getAllTractabilities(Integer page, Integer size) {
        return tractabilityPersistencePort.getAllTractabilities(page, size);
    }
    @Override
    public String calculateDurationPerOrder(Long orderId) {
        return tractabilityPersistencePort.calculateDurationPerOrder(orderId);
    }
}
