package com.pragma.powerup.plazamicroservice.adapters.driven.mongo.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driven.mongo.entity.TractabilityEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITractabilityEntityMapper {
    TractabilityEntity toEntity(Tractability tractability);
    List<Tractability> toTractabilityList(List<TractabilityEntity> tractabilityEntities);
}
