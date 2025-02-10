package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentrest")
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