package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishesOrderedRequestDto {
    @NotBlank
    @NotNull
    private Long idOrder;
    @NotBlank
    @NotNull
    private Long idDish;
    @NotBlank
    private int quantity;
}
