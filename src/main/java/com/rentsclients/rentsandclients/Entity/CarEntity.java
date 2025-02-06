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

    public CarEntity(){

    }
    public CarEntity(Long carID, String brand, String model, String plate) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
    }

    public long getID() {
        return carID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}