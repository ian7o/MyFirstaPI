package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car")
@Setter
@Getter
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
    @JoinColumn( name = "client_id")
    private ClientEntity client;

    public CarEntity() {
    }

    public CarEntity(Long carid, String brand, String model, String plate, boolean activated, ClientEntity client) {
        this.carid = carid;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.activated = activated;
        this.client = client;
    }
}