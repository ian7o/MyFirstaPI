package com.rentsclients.rentsandclients.DTOS;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientDTO {
    private Long clientID;
    private String firstName;
    private String lastName;
    private Integer nif;
    private Boolean activated;

    public ClientDTO() {
    }

    public ClientDTO(Long clientID, String firstName, String lastName, Integer nif, Boolean activated) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.activated = activated;
    }

    public ClientDTO(Long clientID, String firstName, String lastName) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}