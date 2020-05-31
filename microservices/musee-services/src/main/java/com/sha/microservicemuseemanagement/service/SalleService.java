package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Salle;

import java.util.List;

public interface SalleService {
    List<Salle> allSalle();
    Salle getSalleById(long id);
    Salle ajouterSalle(Salle salle);
    List<Salle> getSallesDisponibles();


}
