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
    @Size(min = 3, max = 50, message = "The client first name do not approved")
    @NotBlank(message = "The client firstName is empty")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "The client lastName is empty")
    @Size(min = 3, max = 50, message = "The client last name do not approved")
    private String lastName;
    @Column(unique = true, nullable = false)
    @Min(value = 100000000, message = "The clientNif do not approved")
    @Max(999999999)
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




