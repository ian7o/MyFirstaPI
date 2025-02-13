package com.rentsclients.rentsandclients.DTOS;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RentalDTO {
    private Long rentalID;

    private CarEntity car;

    private ClientEntity client;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public RentalDTO() {
    }

    public RentalDTO(Long rentalID, CarEntity car, ClientEntity client, LocalDateTime startTime, LocalDateTime endTime) {
        this.rentalID = rentalID;
        this.car = car;
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}