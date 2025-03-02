package com.rentsclients.rentsandclients.DTOS;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CarDTO {
//    @Size(min = 3, max = 50)
    private String brand;
//    @Size(min = 3, max = 50)
    private String model;
//    @Size(min = 3, max = 50)
    private String plate;
    private boolean activated;
    private ClientDTO client;

    public CarDTO() {
    }

    public CarDTO(String brand, String model, String plate, boolean activated, ClientDTO client) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.activated = activated;
        this.client = client;
    }
}