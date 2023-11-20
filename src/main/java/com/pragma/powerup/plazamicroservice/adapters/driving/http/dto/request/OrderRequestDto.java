package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class OrderRequestDto {
    @NotBlank
    @NotNull
    private Long userId;
    private Date orderDate;
    @NotBlank
    @NotNull
    private Long restaurantId;
}
