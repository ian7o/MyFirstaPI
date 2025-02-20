package com.rentsclients.rentsandclients.DTOS;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CarDTO {
    private Long carid;
    private String brand;
    private String model;
    private String plate;
    private boolean activated;
    private ClientDTO client;

    public CarDTO() {
    }

    public CarDTO(Long carid, String brand, String model, String plate, boolean activated, ClientDTO client) {
        this.carid = carid;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.activated = activated;
        this.client = client;
    }
}