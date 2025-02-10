//package com.rentsclients.rentsandclients.service;
//
//import com.rentsclients.rentsandclients.Entity.RentalsEntity;
//import com.rentsclients.rentsandclients.Repository.RentalRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class RentalService {
//    private final RentalRepository rentalRepository;
//    private final RentalsEntity rentalsEntity;
//
//    public RentalService(RentalRepository rentalRepository, RentalsEntity rentalsEntity) {
//        this.rentalRepository = rentalRepository;
//        this.rentalsEntity = rentalsEntity;
//    }
//
//    public void updateRentById(Long id){
//        Optional<RentalsEntity> findClient = rentalRepository.findById(id);
//        if (findClient.isPresent()) {
//            RentalsEntity rentsToUpDate = findClient.get();
//            rentsToUpDate.setCar(rentalsEntity.getCar());
//            rentsToUpDate.setClient(rentalsEntity.getClient());
//            rentalRepository.save(findClient.get());
//        }
//    }
//}
