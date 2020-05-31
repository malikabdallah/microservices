package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Restauration;

import java.util.List;

public interface RestaurationService {

    List<Restauration> allRestauration();
    Restauration getRestaurationById(long id);
    Restauration ajouterRestauration(Restauration restauration);

}
