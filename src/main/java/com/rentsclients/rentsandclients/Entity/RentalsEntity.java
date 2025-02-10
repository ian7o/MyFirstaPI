package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "rentrest")
public class RentalsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalID;

    @ManyToOne
    @JoinColumn(name = "carID")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private ClientEntity client;

}