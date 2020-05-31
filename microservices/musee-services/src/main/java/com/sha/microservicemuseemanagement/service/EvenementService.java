package com.sha.microservicemuseemanagement.service;


import com.sha.microservicemuseemanagement.model.Evenement;

import java.util.List;

public interface EvenementService {



    Evenement enregistrerEvenement(Evenement evenement);

    List<Evenement> getEvenementByOeuvresId(long idoeuvres);
}
