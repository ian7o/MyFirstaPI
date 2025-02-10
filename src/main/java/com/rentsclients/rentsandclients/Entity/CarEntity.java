package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "CarTest")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carID;
    private String brand;
    private String model;
    @Column(unique = true, nullable = false)
    private String plate;
    private String carIsActivated;

    public CarEntity() {
    }

    public CarEntity(Long carID, String brand, String model, String plate, String carIsActivated) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.carIsActivated = carIsActivated;
    }

    public Long getCarID() {
        return carID;
    }

    public void setCarID(Long carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getCarIsActivated() {
        return carIsActivated;
    }

    public void setCarIsActivated(String carIsActivated) {
        this.carIsActivated = carIsActivated;
    }
}