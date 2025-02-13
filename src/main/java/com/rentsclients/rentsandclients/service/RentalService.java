package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.DTOS.RentalDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Entity.RentalsEntity;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import com.rentsclients.rentsandclients.Repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, ClientRepository clientRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    public RentalDTO createRent(RentalDTO rentalDTO) {
        RentalsEntity rentalsEntity = new RentalsEntity(rentalDTO.getRentalID(), rentalDTO.getCar(), rentalDTO.getClient(), rentalDTO.getStartTime(), rentalDTO.getEndTime());

        RentalsEntity clientSaved = rentalRepository.save(rentalsEntity);

        return new RentalDTO(clientSaved.getRentalID(), clientSaved.getCar(), clientSaved.getClient(), clientSaved.getStartTime(), clientSaved.getEndTime());
    }

    public List<RentalsEntity> getAllRents() {
        return rentalRepository.findAll();
    }

    public RentalDTO updateRentsByID(long id, RentalDTO rentalDTO) {
        Optional<RentalsEntity> findRent = rentalRepository.findById(id);
        if (findRent.isPresent()) {
            findRent.get().setClient(rentalDTO.getClient());
            findRent.get().setCar(rentalDTO.getCar());
            findRent.get().setStartTime(rentalDTO.getStartTime());
            findRent.get().setEndTime(rentalDTO.getEndTime());
            RentalsEntity savedRental = rentalRepository.save(findRent.get());
            return new RentalDTO(savedRental.getRentalID(), savedRental.getCar(), savedRental.getClient(), savedRental.getStartTime(), savedRental.getEndTime());
        }
        return null;
    }

    public String getAllActiveClientsAndCarsService() {
        List<ClientEntity> searchClients = clientRepository.findByActivatedTrue();
        List<CarEntity> searchCars = carRepository.findByActivatedTrue();

        String carPlates = " ";
        if (!searchCars.isEmpty()) {
            for (CarEntity searchCar : searchCars) {
                carPlates += searchCar.getPlate() + ", ";
            }
        }

        String firstsAndLastsNames = " ";
        if (!searchClients.isEmpty()) {
            for (ClientEntity searchClient : searchClients) {
                firstsAndLastsNames += searchClient.getFirstName() + " " + searchClient.getLastName() + ", ";
            }

            return "clients activated: " + firstsAndLastsNames + "cars activated" + carPlates;
        }
        return "not find";
    }

    public void deleteASpecifiqueRentByID(Long id) {
        rentalRepository.deleteById(id);
    }

    public String getAllActiveClientsWithRentals() {
        List<RentalsEntity> searchClient = rentalRepository.findByClientActivatedTrue();
        if (!searchClient.isEmpty()) {
            String firstnames = " ";
            for (RentalsEntity aa : searchClient) {
                firstnames += aa.getClient().getFirstName() + ", ";
            }
            return firstnames;
        }
        return null;
    }

//    public List<CarDTO> getPlates() {
//        List<RentalsEntity> entities = rentalRepository.findByClientActivatedFalse();
//        List<CarDTO> carDTOList = new ArrayList<>();
//        if (!entities.isEmpty()) {
//            for (RentalsEntity bb : entities) {
//                //vai voltar o está ativado nao sei pq
//                CarDTO carDTO = new CarDTO(null, null, null, bb.getCar().getPlate());
//                carDTOList.add(carDTO);
//            }
//            return carDTOList;
//        }
//        return null;
//    }

    public String getPlates(){
        List<RentalsEntity> entities = rentalRepository.findByClientActivatedFalse();
        if (!entities.isEmpty()){
            String plates= " ";
            for (RentalsEntity bb: entities) {
                plates += bb.getCar().getPlate();
            }
            return plates;
        }
        return null;
    }
}