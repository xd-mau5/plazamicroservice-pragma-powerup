package com.pragma.powerup.plazamicroservice.domain.model;

public class Dishes {

    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private float price;
    private Long restaurantId;
    private String imageUrl;
    private String active;

    public Dishes(Long id, String name, Long categoryId, String description, float price, Long restaurantId, String imageUrl, String active) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
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
