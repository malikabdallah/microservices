package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Exposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpositionRepository extends JpaRepository<Exposition, Long> {

    Exposition findById(long id);

}
