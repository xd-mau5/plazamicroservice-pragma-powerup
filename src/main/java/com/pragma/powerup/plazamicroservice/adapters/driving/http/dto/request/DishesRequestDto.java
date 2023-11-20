package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishesRequestDto {
    private String name;
    @NotBlank
    @NotNull
    private Long categoryId;
    private String description;
    private float price;
    @NotBlank
    @NotNull
    private Long restaurantId;
    private String imageUrl;
    private boolean isActive;
}
