package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name = "dishes_ordered")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishesOrderedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "dishes")
    private OrderEntity orderEntity;
    @ManyToOne
    @JoinColumn(name = "orders")
    private DishesEntity dishesEntity;
    private int quantity;
}
