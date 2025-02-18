package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Mappers.CarMapp;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO createACar(CarDTO carDTO) {
        CarEntity carEntity = CarMapp.Instance.carDtoToCar(carDTO);

        if (carRepository.existsByPlate(carEntity.getPlate())){
            throw new RuntimeException("The plate is already registered ");
        }

        CarEntity carSaved = carRepository.save(carEntity);
        return CarMapp.Instance.carToCardto(carSaved);
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public CarDTO getASpecifiqueCarID(Long id) {
        return CarMapp.Instance.carToCardto(carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarID not found")));
    }

    public CarDTO updateCarByID(Long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("CarID not found"));

        CarEntity updateCar = CarMapp.Instance.carDtoToCar(carDTO);

        if (carRepository.existsByPlate(updateCar.getPlate())){
            throw new RuntimeException("The plate is already registered ");
        }

        updateCar.setCarID(findCar.getCarID());
        CarEntity savedCar = carRepository.save(updateCar);
        return CarMapp.Instance.carToCardto(savedCar);
    }

    public CarDTO activateOrDeactivateCarByID(long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found. The car will not be updated"));

        findCar.setActivated(carDTO.isActivated());
        CarEntity savedCar = carRepository.save(findCar);

        return CarMapp.Instance.carToCardto(savedCar);
    }

    public CarDTO deleteCarByID(Long id) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found. The car will not be deleted"));
        carRepository.deleteById(id);
        return CarMapp.Instance.carToCardto(findCar);
    }
}