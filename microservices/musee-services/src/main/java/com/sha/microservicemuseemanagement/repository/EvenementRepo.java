package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvenementRepo extends JpaRepository<Evenement,Long> {

    List<Evenement>findByIdOeuvre(long idOeuvre);
}

