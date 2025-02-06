package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public void createACar(@RequestBody CarEntity carEntity){
        repository.save(carEntity);
    }

    @GetMapping
    public List<CarEntity> getAll(){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public CarEntity updateCarByID(@PathVariable("id") Long id, @RequestBody CarEntity carEntity){
        Optional<CarEntity> findCar = repository.findById(id);
        if (findCar.isPresent()){
            CarEntity carToUpdate = findCar.get();
            carToUpdate.setBrand(carEntity.getBrand());
            carToUpdate.setModel(carEntity.getModel());
            carToUpdate.setPlate(carEntity.getPlate());

            return repository.save(findCar.get());
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteASpecifiqueCarID(@PathVariable("id") Long id){
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public CarEntity getASpecifiqueID(@PathVariable("id") Long id){
      return  repository.findById(id).orElse(null);
    }
}