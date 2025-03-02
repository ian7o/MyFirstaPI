package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.CarPlateActivatedDto;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping
    public ResponseEntity<?> createACar(@RequestBody CarDTO carDTO) {
        try {
            carService.createACar(carDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Car created");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarByID(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) {
        try {
            carService.updateCarByID(id, carDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Car updated");
        } catch (RuntimeException e) {
            System.out.println("Error in updateCarByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/activeOrDeactivateCar")
    public ResponseEntity<?> activeOrDesativeCarrByID(@PathVariable("id") long id, @RequestBody CarDTO carDTO) {
        try {
            carService.activateOrDeactivateCarByID(id, carDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Car updated");
        } catch (Exception e) {
            System.out.println("Error in activeOrDesativeCarrByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("/associate/{carId}/client/{clientId}")
    public ResponseEntity<?> associateCarWithClient(@PathVariable("carId") Long carID, @PathVariable("clientId") Long clientId) {
        try {
            carService.associateCarWithClient(carID, clientId);
            return ResponseEntity.ok("Car associate");
        } catch (Exception e) {
            System.out.println("Error in associate Car With Client: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/vehiclesActivateForDeactivateUsers")
    public ResponseEntity<?> getVehiclesActivateForDeactivatedClients() {
        try {
            List<CarDTO> result = carService.getDeactivatedAccountsWithActiveVehicles();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("Error in getVehiclesActivateForDeactivatedClients " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }

    }

    @GetMapping("/activePlateForDeactivateUsers")
    public ResponseEntity<?> getActivePlateForDeactivatedUsers() {
        try {
            List<CarPlateActivatedDto> result = carService.getActiveVehicleLicensePlatesForDeactivatedUsers();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println("Error in getActiveVehicleLicensePlatesForDeactivatedUsers " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CarEntity> result = carService.getAllCars();
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
        } catch (Exception e) {
            System.out.println("Error in ResponseEntity: getAllCars: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
        try {
            CarDTO result = carService.getCarByID(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
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