package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
