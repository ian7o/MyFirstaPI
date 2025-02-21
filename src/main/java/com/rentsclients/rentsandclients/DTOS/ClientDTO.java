package com.rentsclients.rentsandclients.DTOS;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Long clientID;
    private String firstName;
    private String lastName;
    private Integer nif;
    private Boolean activated;

    public ClientDTO(Long clientID, String firstName, String lastName, Integer nif, Boolean activated) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.activated = activated;
    }
}
