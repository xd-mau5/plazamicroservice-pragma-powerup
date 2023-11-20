package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.TractabilityRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.TractabilityResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.ITractabilityHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.ITractabilityRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.ITractabilityResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.ITractabilityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TractabilityHandlerImpl implements ITractabilityHandler {
    private final ITractabilityServicePort tractabilityServicePort;
    private final ITractabilityRequestMapper tractabilityRequestMapper;
    private final ITractabilityResponseMapper tractabilityResponseMapper;

    @Override
    public void saveTractability(TractabilityRequestDto tractabilityRequestDto) {
        tractabilityServicePort.createTractability(tractabilityRequestMapper.toTractability(tractabilityRequestDto));
    }
    @Override
    public List<TractabilityResponseDto> findAllByClientId(Long clientId) {
        return tractabilityResponseMapper.toResponseList(tractabilityServicePort.findAllByClientId(clientId));
    }
    @Override
    public List<TractabilityResponseDto> getAllTractabilities(Integer page, Integer size) {
        return tractabilityResponseMapper.toResponseList(tractabilityServicePort.getAllTractabilities(page, size));
    }
    @Override
    public String calculateDurationPerOrder(Long orderId) {
        return tractabilityServicePort.calculateDurationPerOrder(orderId);
    }
    @Override
    public Map<Long, String> calculateAverageDurationPerEmployee(){
        return tractabilityServicePort.calculateAverageDurationPerEmployee();
    }
}
