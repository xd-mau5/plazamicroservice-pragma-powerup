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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
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
    @Override
    public Map<Long, String> calculateAverageDurationPerEmployee() {
        Map<Long,Duration> durationPerEmployee = new HashMap<>();
        Map<Long,Integer> countPerEmployee = new HashMap<>();
        Map<Long,String> averageDurationPerEmployee = new HashMap<>();

        List<TractabilityEntity> tractabilityEntitiesList = tractabilityRepository.findAll();
        LocalDateTime startTime = null;
        LocalDateTime endTime;
        for (TractabilityEntity entity : tractabilityEntitiesList) {
            if (entity.getPreviousState().equals("Pendiente")) {
                startTime = entity.getDate();
                Long employeeId = entity.getEmployeeId();

                durationPerEmployee.put(employeeId, Duration.ZERO);
                countPerEmployee.put(employeeId, 0);
            }
            if (entity.getNewState().equals("Entregado")) {
                Long employeeId = entity.getEmployeeId();
                endTime = entity.getDate();
                assert startTime != null;
                durationPerEmployee.put(employeeId, durationPerEmployee.get(employeeId).plus(Duration.between(startTime, endTime)));
                countPerEmployee.put(employeeId, countPerEmployee.get(employeeId) + 1);
            }
        }

        for (Map.Entry<Long, Duration> entry : durationPerEmployee.entrySet()) {
            Long employeeId = entry.getKey();
            Duration totalDuration = entry.getValue();
            int count = countPerEmployee.get(employeeId);
            Duration averageDuration;
            if (count == 0) {
                averageDuration = Duration.ZERO;
            } else {
                averageDuration = totalDuration.dividedBy(count);
            }

            String averageDurationString = averageDuration.toMinutes() + " minutos y "
                    + averageDuration.toSecondsPart() + " segundos en entregar un pedido.";

            averageDurationPerEmployee.put(employeeId, averageDurationString);
        }

        return averageDurationPerEmployee;
    }
}
