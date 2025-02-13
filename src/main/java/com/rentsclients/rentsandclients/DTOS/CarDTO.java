package com.rentsclients.rentsandclients.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {
    private Long carID;
    private String brand;
    private String model;
    private String plate;
    private boolean activated;

    public CarDTO() {
    }

    public CarDTO(Long carID, String brand, String model, String plate, boolean activated) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.activated = activated;
    }

    public CarDTO(Long carID, String brand, String model, String plate) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
    }
}
