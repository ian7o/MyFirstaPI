package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarEntity createACar(CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public CarEntity getASpecifiqueCarID(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public CarEntity updateCarByID(Long id, CarEntity carEntity) {
        Optional<CarEntity> findCar = carRepository.findById(id);
        if (findCar.isPresent()) {
            CarEntity carToUpdate = findCar.get();
            carToUpdate.setBrand(carEntity.getBrand());
            carToUpdate.setModel(carEntity.getModel());
            carToUpdate.setPlate(carEntity.getPlate());
            carToUpdate.setActivated(carEntity.isActivated());
            return carRepository.save(findCar.get());
        }
        return null;
    }

    public CarEntity activateOrDeactivateCarByID(long id, CarEntity carEntity) {
        Optional<CarEntity> findCar = carRepository.findById(id);
        if (findCar.isPresent()) {
            findCar.get().setActivated(carEntity.isActivated());
            return carRepository.save(findCar.get());
        }
        return null;
    }

    public void deleteCarByID(Long id) {
        carRepository.deleteById(id);
    }
}
