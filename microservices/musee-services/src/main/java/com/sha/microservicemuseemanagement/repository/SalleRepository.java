package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalleRepository  extends JpaRepository<Salle,Long> {

    Salle findById(long id);


    List<Salle> findByIsDisponibleTrue();


}
