package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

}