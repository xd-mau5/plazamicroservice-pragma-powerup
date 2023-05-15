package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantResponseDto {
    private int id;
    private String name;
    private String address;
    private Long ownerId;
    private Long phoneNumber;
    private String urlLogo;
    private String nit;
}
