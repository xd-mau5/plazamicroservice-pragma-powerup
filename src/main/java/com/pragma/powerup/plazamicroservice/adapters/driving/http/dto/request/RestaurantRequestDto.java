package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {
    private int id;
    private String name;
    private String address;
    private Long ownerId;
    private Long phoneNumber;
    private String urlLogo;
    private String nit;
}
