package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.service.CarService;
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
    public CarEntity createACar(@RequestBody CarEntity carEntity) {
       return carService.createACar(carEntity);
    }

    @GetMapping
    public List<CarEntity> getAll() {
       return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarEntity getCarById(@PathVariable("id") Long id) {
        return carService.getASpecifiqueCarID(id);
    }

    @PutMapping("/{id}")
    public CarEntity updateCarByID(@PathVariable("id") Long id, @RequestBody CarEntity carEntity) {
       return carService.updateCarByID(id, carEntity);
    }

    @PutMapping("/activeOrDeactivateCar/{id}")
    public CarEntity activeOrDesativeCarrByID(@PathVariable("id") long id, @RequestBody CarEntity carEntity) {
      return carService.activateOrDeactivateCarByID(id, carEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteASpecifiqueCarID(@PathVariable("id") Long id) {
        carService.deleteCarByID(id);
    }
}