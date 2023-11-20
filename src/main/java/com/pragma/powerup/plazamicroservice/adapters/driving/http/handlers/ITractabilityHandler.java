package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.TractabilityRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.TractabilityResponseDto;

import java.util.List;
import java.util.Map;

public interface ITractabilityHandler {
    void saveTractability(TractabilityRequestDto tractabilityRequestDto);
    List<TractabilityResponseDto> findAllByClientId(Long clientId);
    List<TractabilityResponseDto> getAllTractabilities(Integer page, Integer size);
    String calculateDurationPerOrder(Long orderId);
    Map<Long, String> calculateAverageDurationPerEmployee();
}
