package com.pragma.powerup.plazamicroservice.domain.model;

import java.math.BigInteger;

public class DishesOrdered {
    private BigInteger idOrder;
    private BigInteger idDish;
    private int quantity;

    public DishesOrdered(BigInteger idOrder, BigInteger idDish, int quantity) {
        this.idOrder = idOrder;
        this.idDish = idDish;
        this.quantity = quantity;
    }

    public BigInteger getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(BigInteger idOrder) {
        this.idOrder = idOrder;
    }

    public BigInteger getIdDish() {
        return idDish;
    }

    public void setIdDish(BigInteger idDish) {
        this.idDish = idDish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
