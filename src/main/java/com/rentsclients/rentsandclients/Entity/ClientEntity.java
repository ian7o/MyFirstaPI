package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientID;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private int nif;
    private boolean activated;

    @ManyToMany
    private List<CarEntity> cars;

    public ClientEntity() {
    }

    public ClientEntity(Long clientID, String firstName, String lastName, int nif, boolean activated, List<CarEntity> carids) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.activated = activated;
        this.cars = carids;
    }
}