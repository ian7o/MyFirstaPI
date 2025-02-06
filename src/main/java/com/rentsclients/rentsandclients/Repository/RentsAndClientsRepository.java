package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentsAndClientsRepository extends JpaRepository<CarEntity, Long> {

}