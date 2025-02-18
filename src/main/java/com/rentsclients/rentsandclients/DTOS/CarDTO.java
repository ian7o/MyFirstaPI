package com.rentsclients.rentsandclients.DTOS;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CarDTO {
    private Long carID;
    private String brand;
    private String model;
    private String plate;
    private boolean activated;
    private CarDTO clientid;
    private List<ClientDTO> clients;

    public CarDTO() {
    }

    public CarDTO(Long carID, String brand, String model, String plate, boolean activated, CarDTO clientid, List<ClientDTO> clients) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.activated = activated;
        this.clientid = clientid;
        this.clients = clients;
    }
}
