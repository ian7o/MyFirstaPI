package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import lombok.Setter;

@Builder
@Getter
@Setter
@Entity(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
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




