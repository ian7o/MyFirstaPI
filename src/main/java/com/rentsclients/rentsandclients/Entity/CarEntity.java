package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Car")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carID;
    private String brand;
    private String model;
    @Column(unique = true, nullable = false)
    private String plate;
    private boolean activated;
}