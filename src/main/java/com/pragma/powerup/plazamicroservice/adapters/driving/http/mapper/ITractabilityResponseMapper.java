package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.TractabilityResponseDto;
import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITractabilityResponseMapper {
    TractabilityResponseDto toResponse(String tractability);
    List<TractabilityResponseDto> toResponseList(List<Tractability> tractability);
}
