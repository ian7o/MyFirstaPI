package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;


@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String brand;
    private String model;
    @Column(unique = true)
    private String plate;

    public CarEntity(){

    }
    public CarEntity(Long ID, String brand, String model, String plate) {
        this.ID = ID;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
    }

    public long getID() {
        return ID;
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

    public void setID(long ID) {
        this.ID = ID;
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