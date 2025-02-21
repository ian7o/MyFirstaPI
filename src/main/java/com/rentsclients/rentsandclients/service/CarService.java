package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public CarDTO createACar(CarDTO carDTO) {
        if (carDTO == null) {
            throw new IllegalArgumentException("CarDTO cannot be null");
        }

        CarEntity converterInEntity = new CarEntity();
        converterInEntity.setCarid(carDTO.getCarid());
        converterInEntity.setBrand(carDTO.getBrand());
        converterInEntity.setModel(carDTO.getModel());
        converterInEntity.setPlate(carDTO.getPlate());
        converterInEntity.setActivated(carDTO.isActivated());

        if (carRepository.existsByPlate(converterInEntity.getPlate())) {
            throw new RuntimeException("The plate is already registered");
        }

        CarEntity carSaved = carRepository.save(converterInEntity);

        return new CarDTO(
                carSaved.getCarid(),
                carSaved.getBrand(),
                carSaved.getModel(),
                carSaved.getPlate(),
                carSaved.isActivated(),
                null
        );
    }

    public CarDTO updateCarByID(Long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("CarID not found"));

        findCar.setBrand(carDTO.getBrand());
        findCar.setModel(carDTO.getModel());
        findCar.setPlate(carDTO.getPlate());
        findCar.setActivated(carDTO.isActivated());

        if (carRepository.existsByPlate(findCar.getPlate())) {
            throw new RuntimeException("The plate is already registered ");
        }

        CarEntity savedCar = carRepository.save(findCar);

        return new CarDTO(
                savedCar.getCarid(),
                savedCar.getBrand(),
                savedCar.getModel(),
                savedCar.getPlate(),
                savedCar.isActivated(),
                null
        );
    }

    public void activateOrDeactivateCarByID(long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found. The car will not be updated"));

        findCar.setActivated(carDTO.isActivated());
        carRepository.save(findCar);
    }


    public void associateCarWithClient(long carId, Long userId) {
        CarEntity findCar = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("ID not found. The car will not be updated"));

        ClientEntity findUser = clientRepository.findById(userId).orElseThrow(() -> new ClientNotFoundException("Client not found. The car will not be updated"));

        findCar.setClient(findUser);

        carRepository.save(findCar);
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

//
//    public CarDTO getASpecifiqueCarID(Long id) {
//        return carMap.toCarDto(carRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("CarID not found")));
//    }

    public void deleteCarByID(Long id) {
        List<CarEntity> findCars = carRepository.findAll();
        if (findCars.isEmpty()) {
            throw new RuntimeException("Car not found cannot be deleted");
        }
        carRepository.deleteById(id);
    }
}