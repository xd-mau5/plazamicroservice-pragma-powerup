package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "dishes_ordered")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishesOrderedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dishes")
    private DishesEntity dishesEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders")
    private OrderEntity orderEntity;
    private int quantity;
}
