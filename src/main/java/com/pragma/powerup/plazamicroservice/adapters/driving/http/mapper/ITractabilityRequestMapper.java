package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.TractabilityRequestDto;
import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITractabilityRequestMapper {
    Tractability toTractability(TractabilityRequestDto tractabilityRequestDto);
}
