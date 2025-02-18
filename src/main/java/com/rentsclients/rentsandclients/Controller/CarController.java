package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createACar(@RequestBody CarDTO carDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(carService.createACar(carDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(carService.getAllCars());
        } catch (Exception e) {
            System.out.println("Error in ResponseEntity: getAllCars: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(carService.getASpecifiqueCarID(id));
        } catch (Exception e) {
            System.out.println("Error in getCarById: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarByID(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.updateCarByID(id, carDTO));
        } catch (RuntimeException e) {
            System.out.println("Error in updateCarByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/activeOrDeactivateCar/{id}")
    public ResponseEntity<?> activeOrDesativeCarrByID(@PathVariable("id") long id, @RequestBody CarDTO carDTO) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.activateOrDeactivateCarByID(id, carDTO));
        } catch (Exception e) {
            System.out.println("Error in activeOrDesativeCarrByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteASpecifiqueCarID(@PathVariable("id") Long id) {
        try {
            carService.deleteCarByID(id);
            return ResponseEntity.ok("Car successful deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}