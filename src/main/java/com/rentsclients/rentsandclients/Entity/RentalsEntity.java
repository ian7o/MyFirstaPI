package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "rent")
public class RentalsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalID;

    @ManyToOne
    @JoinColumn(name = "carID", nullable = false)
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "clientID", nullable = false)
    private ClientEntity client;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;
}