package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishesResponseDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private float price;
    private Long restaurantId;
    private String imageUrl;
    private String active;
}
