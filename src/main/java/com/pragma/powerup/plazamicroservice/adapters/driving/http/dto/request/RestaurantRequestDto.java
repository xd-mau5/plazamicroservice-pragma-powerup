package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {
    private String name;
    private String address;
    @NotBlank
    @NotNull
    private Long ownerId;
    private Long phoneNumber;
    private String urlLogo;
    private String nit;
}