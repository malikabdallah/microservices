package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Oeuvre;

import java.util.List;

public interface OeuvreService {

    List<Oeuvre> allOeuvre();
    Oeuvre getOeuvreById(long id);
    Oeuvre ajouterOeuvre(Oeuvre oeuvre);

    void supprimerOeuvre(Oeuvre oeuvre);

    List<Oeuvre> trouverOeuvresEnReserve();

    List<Oeuvre>trouveOeuvresExpose();

    List<Oeuvre> trouverPret();

    List<Oeuvre>trouverOeuvresEnRestauration();

}
