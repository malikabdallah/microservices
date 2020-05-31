package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OeuvreRepository extends JpaRepository<Oeuvre,Long> {

    Oeuvre findById(long id);




    List<Oeuvre> findByIsExposeFalse();


    List<Oeuvre>findByIsExposeTrue();


    List<Oeuvre>findByisAcheteFalse();

    List<Oeuvre> findByIsRestaureTrue();
}
