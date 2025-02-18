package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Car")
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

    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id")
    private List<ClientEntity> clients;

    public CarEntity() {
    }

    public CarEntity(Long carID, String brand, String model, String plate, boolean activated, List<ClientEntity> clientids) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.activated = activated;
        this.clients = clientids;
    }
}