package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishesOrderedResponseDto {
    private Long id;
    private Long idOrder;
    private Long idDish;
    private int quantity;
}
