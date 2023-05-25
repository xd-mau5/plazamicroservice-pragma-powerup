package com.pragma.powerup.plazamicroservice.domain.model;

public class UpdateDish {
    private String description;
    private float price;

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

    public UpdateDish(String description, float price) {
        this.description = description;
        this.price = price;
    }
}
