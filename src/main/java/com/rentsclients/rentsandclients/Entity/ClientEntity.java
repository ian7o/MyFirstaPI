package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientid;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private int nif;
    private boolean activated;

    public ClientEntity() {
    }

    public ClientEntity(Long clientid, String firstName, String lastName, int nif, boolean activated) {
        this.clientid = clientid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.activated = activated;
    }
}




