//package com.rentsclients.rentsandclients.Entity;
//
//import jakarta.persistence.*;
//
//@Entity
//public class RentalsEntity {
//    @Id
//    private Long rentalID;
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "clientID")
//    private Long associatedClientID;
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "carID")
//    private Long associateCarID;
//
//    /*
//    aqui vem a data do aluguel
//    dia e hora
//     */
//
//    public RentalsEntity(Long rentalID, Long associatedClientID, Long associateCarID) {
//        this.rentalID = rentalID;
//        this.associatedClientID = associatedClientID;
//        this.associateCarID = associateCarID;
//    }
//
//
//}
