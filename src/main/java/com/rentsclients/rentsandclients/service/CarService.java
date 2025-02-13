package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO createACar(CarDTO carDTO) {
        CarEntity converterInEntity = new CarEntity();
        converterInEntity.setCarID(carDTO.getCarID());
        converterInEntity.setBrand(carDTO.getBrand());
        converterInEntity.setModel(carDTO.getModel());
        converterInEntity.setPlate(carDTO.getPlate());
        converterInEntity.setActivated(carDTO.isActivated());


        CarEntity carSaved = carRepository.save(converterInEntity);

        return new CarDTO(carSaved.getCarID(), carSaved.getBrand(), carSaved.getModel(), carSaved.getPlate(), carSaved.isActivated());
    }

    public List<CarEntity> getAllCars() {

        return carRepository.findAll();
    }

    public CarDTO getASpecifiqueCarID(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElse(null);
        if (carEntity==null){
            return null;
        }
        CarDTO carDTO = new CarDTO(carEntity.getCarID(), carEntity.getBrand(), carEntity.getModel(), carEntity.getPlate(), carEntity.isActivated());
        return carDTO;
    }

    public CarDTO updateCarByID(Long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElse(null);
        if (findCar == null) {
            return null;
        }
        findCar.setBrand(carDTO.getBrand());
        findCar.setModel(carDTO.getModel());
        findCar.setPlate(carDTO.getPlate());
        findCar.setActivated(carDTO.isActivated());

        CarEntity savedCar = carRepository.save(findCar);

        return new CarDTO(savedCar.getCarID(), savedCar.getBrand(), savedCar.getModel(), savedCar.getPlate(), savedCar.isActivated());
    }

    public CarDTO activateOrDeactivateCarByID(long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElse(null);

        if (findCar == null) {
            return null;
        }

        findCar.setActivated(carDTO.isActivated());
        carRepository.save(findCar);

        return new CarDTO(
                findCar.getCarID(),
                findCar.getBrand(),
                findCar.getModel(),
                findCar.getPlate(),
                findCar.isActivated()
        );
    }

    public void deleteCarByID(Long id) {
        carRepository.deleteById(id);
    }
}
