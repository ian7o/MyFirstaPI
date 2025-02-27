package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.CarPlateActivatedDto;
import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.CarNotFoundException;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Exceptions.DuplicateCarPlateException;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public void createACar(CarDTO carDTO) {
        if (carDTO == null) {
            throw new IllegalArgumentException("CarDTO cannot be null");
        }

        CarEntity converterInEntity = new CarEntity();
        converterInEntity.setBrand(carDTO.getBrand());
        converterInEntity.setModel(carDTO.getModel());
        converterInEntity.setPlate(carDTO.getPlate());
        converterInEntity.setActivated(carDTO.isActivated());

        if (carRepository.existsByPlate(converterInEntity.getPlate())) {
            throw new DuplicateCarPlateException("The plate is already registered");
        }

        carRepository.save(converterInEntity);

    }

    public CarDTO updateCarByID(Long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("CarID not found"));

        findCar.setBrand(carDTO.getBrand());
        findCar.setModel(carDTO.getModel());
        findCar.setPlate(carDTO.getPlate());
        findCar.setActivated(carDTO.isActivated());

        if (carRepository.existsByPlate(findCar.getPlate())) {
            throw new DuplicateCarPlateException("The plate is already registered ");
        }

        CarEntity savedCar = carRepository.save(findCar);

        return new CarDTO(
                savedCar.getBrand(),
                savedCar.getModel(),
                savedCar.getPlate(),
                savedCar.isActivated(),
                null
        );
    }

    public void activateOrDeactivateCarByID(long id, CarDTO carDTO) {
        CarEntity findCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("ID not found. The car will not be updated"));

        findCar.setActivated(carDTO.isActivated());
        carRepository.save(findCar);
    }


    public void associateCarWithClient(long carId, Long userId) {
        CarEntity findCar = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("ID not found. The car will not be updated"));

        ClientEntity findUser = clientRepository.findById(userId).orElseThrow(() -> new ClientNotFoundException("Client not found. The car will not be updated"));

        findCar.setClient(findUser);

        carRepository.save(findCar);
    }

    public List<CarDTO> getDeactivatedAccountsWithActiveVehicles() {
        List<CarEntity> findClients = carRepository.findByClientActivatedFalseAndActivatedTrue();
        List<CarDTO> result = new ArrayList<>();

//        findClients.forEach(clients -> result.add(CarMapper.INSTANCE.carEntityToCarDto(clients)));

        findClients.forEach(i -> result.add(
                new CarDTO(
                        i.getBrand(),
                        i.getModel(),
                        i.getPlate(),
                        i.isActivated(),
                        new ClientDTO(
                                i.getClient().getFirstName(),
                                i.getClient().getLastName(),
                                i.getClient().getNif(),
                                i.getClient().isActivated()
                        ))
        ));
        return result;
    }

    public List<CarPlateActivatedDto> getActiveVehicleLicensePlatesForDeactivatedUsers() {
        List<CarEntity> carEntities = carRepository.findByClientActivatedFalseAndActivatedTrue();

        List<CarPlateActivatedDto> result = new ArrayList<>();

        carEntities.forEach(i -> result.add(
                new CarPlateActivatedDto(
                        i.getPlate(),
                        i.isActivated(),
                        new ClientDTO(
                                i.getClient().getFirstName(),
                                i.getClient().getLastName(),
                                i.getClient().getNif(),
                                i.getClient().isActivated())
                ))
        );
        return result;
    }


    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public CarDTO getCarByID(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("CarID not found"));

        ClientDTO clientDTO = new ClientDTO();

        if (carEntity.getClient() != null) {
            clientDTO.setFirstName(carEntity.getClient().getLastName());
            clientDTO.setNif(carEntity.getClient().getNif());
            clientDTO.setActivated(carEntity.getClient().isActivated());
        }

        return new CarDTO(
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getPlate(),
                carEntity.isActivated(),
                clientDTO);
    }

    public void deleteCarByID(Long id) {
        List<CarEntity> findCars = carRepository.findAll();
        if (findCars.isEmpty()) {
            throw new CarNotFoundException("Car not found cannot be deleted");
        }
        carRepository.deleteById(id);
    }
}