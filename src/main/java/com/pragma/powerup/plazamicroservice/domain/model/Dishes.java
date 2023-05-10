package com.pragma.powerup.plazamicroservice.domain.model;

import java.math.BigInteger;

public class Dishes {

    private BigInteger id;
    private String name;
    private BigInteger categoryId;
    private String description;
    private float price;
    private BigInteger restaurantId;
    private String imageUrl;
    private String active;

    public Dishes(BigInteger id, String name, BigInteger categoryId, String description, float price, BigInteger restaurantId, String imageUrl, String active) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BigInteger getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(BigInteger restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
