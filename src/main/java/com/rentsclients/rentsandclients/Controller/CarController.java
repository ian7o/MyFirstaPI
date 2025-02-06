package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Repository.RentsAndClientsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private final RentsAndClientsRepository repository;

    public CarController(RentsAndClientsRepository repository) {
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

}
