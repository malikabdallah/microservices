package com.sha.microservicemuseemanagement.controller;

import com.sha.microservicemuseemanagement.model.Exposition;
import com.sha.microservicemuseemanagement.model.Oeuvre;
import com.sha.microservicemuseemanagement.model.Salle;
import com.sha.microservicemuseemanagement.service.ExpositionService;
import com.sha.microservicemuseemanagement.service.OeuvreService;
import com.sha.microservicemuseemanagement.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalleController {


    @Autowired
    private SalleService salleService;

    @Autowired
    private OeuvreService oeuvreService;

    @Autowired
    private ExpositionService expositionService;

    /*salle*/
    @GetMapping(value = "/salles")
    public ResponseEntity<?> findAllSalle() {
        return ResponseEntity.ok(salleService.allSalle());
    }

    @GetMapping(value = "/salles/{salleId}")
    public ResponseEntity<?> findSalleById(@PathVariable long salleId) {
        return ResponseEntity.ok(salleService.getSalleById(salleId));
    }

    @PutMapping(value = "/salles/{id}")
    public ResponseEntity<?> miseajoursalle(@PathVariable long id,@RequestBody Salle salle)
    {
        Salle salle1=salleService.getSalleById(id) ;
        salle1.setIsDisponible(false);


        return new ResponseEntity<>(salleService.ajouterSalle(salle1), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/salles")
    public ResponseEntity<?> enregistrerSalle(@RequestBody Salle salle)
    {
        salle.setIsDisponible(true);
        return new ResponseEntity<>(salleService.ajouterSalle(salle), HttpStatus.CREATED);
    }

    @GetMapping(value = "/expositions/{idexpo}/salles")
    public ResponseEntity<?>retournesallesexpo(@PathVariable long idexpo){
        Exposition exposition=expositionService.trouverExpositionParId(idexpo);
        return ResponseEntity.ok(exposition.getSalles());
    }

    @GetMapping(value = "/oeuvres/{id}/salle")
    public ResponseEntity<?>getSallesOeuvre(@PathVariable long id){
        Oeuvre oeuvre=oeuvreService.getOeuvreById(id);
        return ResponseEntity.ok(oeuvre.getSalle());
    }




    @GetMapping("/salles/disponible")
    public ResponseEntity<?>getSallesDispo(){
        return ResponseEntity.ok(salleService.getSallesDisponibles());
    }





}
