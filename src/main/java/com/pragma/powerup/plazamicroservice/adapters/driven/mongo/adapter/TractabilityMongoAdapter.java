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
}