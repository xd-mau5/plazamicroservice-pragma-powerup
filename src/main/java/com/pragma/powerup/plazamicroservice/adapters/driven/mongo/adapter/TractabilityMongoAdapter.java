package com.pragma.powerup.plazamicroservice.adapters.driven.mongo.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.entity.TractabilityEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.mapper.ITractabilityEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.repository.ITractabilityRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import com.pragma.powerup.plazamicroservice.domain.spi.ITractabilityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TractabilityMongoAdapter implements ITractabilityPersistencePort {
    private final ITractabilityRepository tractabilityRepository;
    private final ITractabilityEntityMapper tractabilityEntityMapper;

    @Override
    public void createTractability(Tractability tractability) {
        tractabilityRepository.save(tractabilityEntityMapper.toEntity(tractability));
    }
    @Override
    public List<Tractability> findAllByClientId(Long userId){
        List<TractabilityEntity> tractabilityEntitiesList = tractabilityRepository.findAllByClientId(userId);
        return tractabilityEntityMapper.toTractabilityList(tractabilityEntitiesList);
    }

    @Override
    public List<Tractability> getAllTractabilities(Integer page, Integer size) {
        Pageable pagination = PageRequest.of(page, size);
        List<TractabilityEntity> tractabilityEntitiesList = tractabilityRepository.findAll(pagination).getContent();
        return tractabilityEntityMapper.toTractabilityList(tractabilityEntitiesList);
    }
    public String calculateDurationPerOrder(Long orderId) {
        List<TractabilityEntity> tractabilityEntitiesList = tractabilityRepository.findAllByOrderId(orderId);

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        for (TractabilityEntity entity : tractabilityEntitiesList) {
            if (entity.getPreviousState().equals("Pendiente")) {
                startTime = entity.getDate();
            }

            if (entity.getNewState().equals("Entregado")) {
                endTime = entity.getDate();
                break;
            }
        }

        if (startTime != null && endTime != null) {
            return "El pedido duró " + Duration.between(startTime, endTime).toMinutes() + " minutos y "
                    + Duration.between(startTime, endTime).toSecondsPart() + " segundos.";
        } else {
            throw new IllegalStateException(
                    "No se encontró el pedido o no hay suficientes registros de trazabilidad para calcular la duración."
            );
        }
    }

}