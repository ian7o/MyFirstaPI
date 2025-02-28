package com.rentsclients.rentsandclients.DTOS;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CarPlateActivatedDto {
    private String plate;
    private boolean activated;
    private ClientDTO client;

    public CarPlateActivatedDto(String plate, boolean activated, ClientDTO client) {
        this.plate = plate;
        this.activated = activated;
        this.client = client;
    }
}
