package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@Getter
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Date orderDate;
    private String status;
    private BigInteger chefId;
    private BigInteger restaurantId;
}
