package com.rentsclients.rentsandclients.DTOS;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDtoOnlyForFirstAndLastNames {
    private String firstName;
    private String lastName;

    public ClientDtoOnlyForFirstAndLastNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
