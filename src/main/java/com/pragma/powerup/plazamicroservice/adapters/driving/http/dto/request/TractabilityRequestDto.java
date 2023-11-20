package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TractabilityRequestDto {
    private Long orderId;
    private Long clientId;
    private String clientEmail;
    private LocalDateTime date;
    private String previousState;
    private String newState;
    private Long employeeId;
    private String employeeEmail;
}
