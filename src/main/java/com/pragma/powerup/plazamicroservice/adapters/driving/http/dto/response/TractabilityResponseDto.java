package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TractabilityResponseDto {
    private ObjectId id;
    private Long orderId;
    private Long clientId;
    private String clientEmail;
    private LocalDateTime date;
    private String previousState;
    private String newState;
    private Long employeeId;
    private String employeeEmail;
}
