package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carid;
    private String brand;
    private String model;
    @Column(unique = true, nullable = false)
    private String plate;
    private boolean activated;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}