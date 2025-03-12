package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.CarDtoOnlyForActivated;
import com.rentsclients.rentsandclients.DTOS.CarPlateActivatedDto;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> createACar(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            carService.createACar(carDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Car created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCarByID(@PathVariable("id") Long id, @Valid @RequestBody CarDTO carDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            carService.updateCarByID(id, carDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Car updated");
    }

    @PatchMapping("/{id}/activeOrDeactivateCar")
    public ResponseEntity<?> activeOrDeactivateCarByID(@PathVariable("id") long id, @RequestBody CarDtoOnlyForActivated carDtoOnlyForActivated, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            carService.activateOrDeactivateCarByID(id, carDtoOnlyForActivated);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Car updated");
    }

    @PatchMapping("/associate/{carId}/client/{clientId}")
    public ResponseEntity<?> associateCarWithClient(@PathVariable("carId") Long carID, @PathVariable("clientId") Long clientId) {
            carService.associateCarWithClient(carID, clientId);
            return ResponseEntity.ok("Car associate");
    }

    @GetMapping("/vehiclesActivateForDeactivateUsers")
    public ResponseEntity<?> getVehiclesActivateForDeactivatedClients() {
            List<CarDTO> result = carService.getDeactivatedAccountsWithActiveVehicles();
            return ResponseEntity.ok(result);
    }

    @GetMapping("/activePlateForDeactivateUsers")
    public ResponseEntity<?> getActivePlateForDeactivatedUsers() {
            List<CarPlateActivatedDto> result = carService.getActiveVehicleLicensePlatesForDeactivatedUsers();
            return ResponseEntity.ok().body(result);
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
            List<CarEntity> result = carService.getAllCars();
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
            CarDTO result = carService.getCarByID(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteASpecifiqueCarID(@PathVariable("id") Long id) {
            carService.deleteCarByID(id);
            return ResponseEntity.ok("Car successful deleted");
    }
}