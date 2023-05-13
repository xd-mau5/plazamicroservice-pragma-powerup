package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String birthDate;
    @Column(unique = true, nullable = false)
    private String mail;
    @Column(unique = true, nullable = false, length = 13)
    private String phone;
    private String address;
    @Column(nullable = false)
    private String idDniType;
    @Column(unique = true, nullable = false, length = 20)
    private Long dniNumber;
    @Column(nullable = false)
    private String password;
    private String tokenPassword;
}
