package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.CarDtoOnlyForActivated;
import com.rentsclients.rentsandclients.DTOS.CarPlateActivatedDto;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.*;
import com.rentsclients.rentsandclients.Mappers.CarMapper;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    private final CarMapper carMapper;

    public void validateInfosSize(String brand, String model, String plate) {
        if (brand.strip().replace(" ", "").length() < 3 || brand.strip().length() > 50) {
            throw new CarBrandException("The car first brand size do not approved");
        }
        if (model.strip().replace(" ", "").length() < 3 || model.strip().length() > 50) {
            throw new CarModelException("The car model size do not approved");
        }

        if (plate.strip().replace(" ", "").length() < 7 || plate.strip().length() > 8) {
            throw new CarPlateException("The car plate size do not approved");
        }
    }


    public CarService(CarRepository carRepository, ClientRepository clientRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.carMapper = carMapper;
    }

    public void createACar(CarDTO carDTO) {
        validateInfosSize(carDTO.getBrand(), carDTO.getModel(), carDTO.getPlate());

        CarEntity converterInEntity = carMapper.carDtoToCar(carDTO);
        if (carRepository.existsByPlate(converterInEntity.getPlate())) {
            throw new DuplicateCarPlateException("The plate is already registered");
        }

        carRepository.save(converterInEntity);
    }

    public void updateCarByID(Long id, CarDTO carDTO) {
        validateInfosSize(carDTO.getBrand(), carDTO.getModel(), carDTO.getPlate());

        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("CarID not found"));
        carMapper.updateCarEntityFromCarDTO(carDTO, findCar);

        if (carRepository.existsByPlate(findCar.getPlate())) {
            throw new DuplicateCarPlateException("The plate is already registered ");
        }
        carRepository.save(findCar);
    }

    public void activateOrDeactivateCarByID(long id, CarDtoOnlyForActivated carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("ID not found. The car will not be updated"));

        carMapper.updateCarEntityFromCarDtoOnlyForActivated(carDTO, findCar);
        carRepository.save(findCar);
    }


    public void associateCarWithClient(long carId, Long userId) {
        CarEntity findCar = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("ID not found. The car will not be updated"));
        ClientEntity findUser = clientRepository.findById(userId).orElseThrow(() -> new ClientNotFoundException("Client not found. The car will not be updated"));
        findCar.setClient(findUser);

        carRepository.save(findCar);
    }

    public List<CarDTO> getDeactivatedAccountsWithActiveVehicles() {
        List<CarEntity> carEntities = carRepository.findByClientActivatedFalseAndActivatedTrue();
        List<CarDTO> result = new ArrayList<>();

        for (CarEntity car : carEntities) {
            CarDTO carDTO = carMapper.carToCarDto(car);
            result.add(carDTO);
        }
        return result;
    }

    public List<CarPlateActivatedDto> getActiveVehicleLicensePlatesForDeactivatedUsers() {
        List<CarEntity> carEntities = carRepository.findByClientActivatedFalseAndActivatedTrue();
        List<CarPlateActivatedDto> result = new ArrayList<>();

        for (CarEntity carEntity : carEntities) {
            CarPlateActivatedDto dto = carMapper.carEntityToCarPlateActivatedDto(carEntity);

            result.add(dto);
        }
        return result;
    }


    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public CarDTO getCarByID(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("CarID not found"));

        return carMapper.carToCarDto(carEntity);
    }

    public void deleteCarByID(Long id) {
        List<CarEntity> findCars = carRepository.findAll();
        if (findCars.isEmpty()) {
            throw new CarNotFoundException("Car not found cannot be deleted");
        }
        carRepository.deleteById(id);
    }
}