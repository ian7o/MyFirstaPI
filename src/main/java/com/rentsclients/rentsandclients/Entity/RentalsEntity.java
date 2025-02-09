package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;

@Entity
public class RentalsEntity {
    @Id
    private Long rentalID;

    @ManyToOne
    @JoinColumn(name = "carID")
    private CarEntity associateCarID;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private ClientEntity associateClientID;

}
