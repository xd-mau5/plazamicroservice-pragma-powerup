package com.pragma.powerup.plazamicroservice.domain.model;

import java.math.BigInteger;
import java.util.Date;

public class Orders {
    private BigInteger id;
    private Long userId;
    private Date date;
    private String status;
    private BigInteger chefId;
    private BigInteger restaurantId;

    public Orders(BigInteger id, Long userId, Date date, String status, BigInteger chefId, BigInteger restaurantId) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.chefId = chefId;
        this.restaurantId = restaurantId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getChefId() {
        return chefId;
    }

    public void setChefId(BigInteger chefId) {
        this.chefId = chefId;
    }

    public BigInteger getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(BigInteger restaurantId) {
        this.restaurantId = restaurantId;
    }
}
