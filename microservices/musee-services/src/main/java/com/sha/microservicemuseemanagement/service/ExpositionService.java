package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Exposition;
import com.sha.microservicemuseemanagement.model.Oeuvre;

import java.util.List;

public interface ExpositionService {

    List<Exposition> allCourses();



    Exposition trouverExpositionParId(long id);


    Exposition enregistreExposition(Exposition transaction);

    void ajouerOeuvreAUneExposition(long expositionId, Oeuvre oeuvre);

}
