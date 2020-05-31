package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    Participation findByIdAndUserId(long id, long userId);
}
