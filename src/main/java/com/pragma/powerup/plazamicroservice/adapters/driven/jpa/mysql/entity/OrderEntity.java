package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private UserEntity userEntity;
    private Date orderDate;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_chef")
    private UserEntity chefEntity;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurantEntity;
}
