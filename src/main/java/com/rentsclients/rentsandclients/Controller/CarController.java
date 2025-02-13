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
        CarDTO carEntity = carService.createACar(carDTO);
        if (carEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(carEntity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cannot create");
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CarEntity> carDTO = carService.getAllCars();
        if (carDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not Found results");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(carDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) throws Exception {
        CarDTO carDTO = carService.getASpecifiqueCarID(id);
        if (carDTO == null) {
          return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not Found");
        }
       return ResponseEntity.status(HttpStatus.FOUND).body(carDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarByID(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) throws Exception {
        CarDTO carDTO1 = carService.updateCarByID(id, carDTO);
        if (carDTO1==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carDTO1);
    }

    @PutMapping("/activeOrDeactivateCar/{id}")
    public ResponseEntity<?> activeOrDesativeCarrByID(@PathVariable("id") long id, @RequestBody CarDTO carDTO) throws Exception {
        CarDTO carDTO1 = carService.activateOrDeactivateCarByID(id, carDTO);
        if (carDTO1==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carDTO1);
    }

    @DeleteMapping("/{id}")
    public void deleteASpecifiqueCarID(@PathVariable("id") Long id) {
        carService.deleteCarByID(id);
    }
}