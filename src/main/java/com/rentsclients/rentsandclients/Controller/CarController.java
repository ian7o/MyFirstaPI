package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Void> createACar(@RequestBody CarDTO carDTO) {
        try {
            carService.createACar(carDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarByID(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.updateCarByID(id, carDTO));
        } catch (RuntimeException e) {
            System.out.println("Error in updateCarByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("/activeOrDeactivateCar/{id}")
    public ResponseEntity<?> activeOrDesativeCarrByID(@PathVariable("id") long id, @RequestBody CarDTO carDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.activateOrDeactivateCarByID(id, carDTO));
        } catch (Exception e) {
            System.out.println("Error in activeOrDesativeCarrByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("/associate/{carId}/client/{clientId}")
    public ResponseEntity<?> associateCarWithClient(@PathVariable("carId") Long carID, @PathVariable("clientId") Long clientId) {
        try {
            return ResponseEntity.ok(carService.associateCarWithClient(carID, clientId));
        } catch (Exception e) {
            System.out.println("error in asociateCarWithClient: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
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