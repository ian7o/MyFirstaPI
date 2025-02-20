package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import com.rentsclients.rentsandclients.mapper.CarMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final CarMapper carMap;

    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, CarMapper carMap, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.carMap = carMap;
        this.clientRepository = clientRepository;
    }

    public void createACar(CarDTO carDTO) {
        if (carDTO == null) {
            throw new IllegalArgumentException("CarDTO cannot be null");
        }

        CarEntity carEntity = carMap.toCarEntity(carDTO);

        if (carRepository.existsByPlate(carEntity.getPlate())){
            throw new RuntimeException("The plate is already registered");
        }

        carRepository.save(carEntity);
    }

    //por alugm motivo o dto nao está pegando o id por isso estou passando aqui o car entity
    public List<CarEntity> getAllCars() {
        List<CarEntity> allCars = carRepository.findAll();
//        List<CarDTO> cars = carMapper.ToCarsDtoList(allCars);
        return allCars;
    }

    public CarDTO getASpecifiqueCarID(Long id) {
        return carMap.toCarDto(carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarID not found")));
    }

    public CarDTO updateCarByID(Long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("CarID not found"));

        CarEntity updateCar = carMap.toCarEntity(carDTO);

        if (carRepository.existsByPlate(updateCar.getPlate())){
            throw new RuntimeException("The plate is already registered ");
        }
        updateCar.setCarid(findCar.getCarid());

        CarEntity savedCar = carRepository.save(updateCar);
        return carMap.toCarDto(savedCar);
    }

    public CarDTO activateOrDeactivateCarByID(long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found. The car will not be updated"));

        findCar.setActivated(carDTO.isActivated());
        CarEntity savedCar = carRepository.save(findCar);

        return carMap.toCarDto(savedCar);
    }

    public CarDTO associateCarWithClient(long carId, Long userId) {
        CarEntity findCar = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("ID not found. The car will not be updated"));

        ClientEntity findUser = clientRepository.findById(userId).orElseThrow(() -> new ClientNotFoundException("Client not found. The car will not be updated"));

        findCar.setClient(findUser);

        CarEntity savedCar = carRepository.save(findCar);

        return carMap.toCarDto(savedCar);
    }

    public void deleteCarByID(Long id) {
        carRepository.deleteById(id);
    }
}