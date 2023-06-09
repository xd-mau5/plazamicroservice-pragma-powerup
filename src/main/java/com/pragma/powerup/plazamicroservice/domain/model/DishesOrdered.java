package com.pragma.powerup.plazamicroservice.domain.model;

public class DishesOrdered {
    private Long id;
    private Long idOrder;
    private Long idDish;
    private int quantity;

    public DishesOrdered(Long id,Long idOrder, Long idDish, int quantity) {
        this.id = id;
        this.idOrder = idOrder;
        this.idDish = idDish;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
