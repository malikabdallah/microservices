package com.sha.microservicemuseemanagement.controller;

import com.sha.microservicemuseemanagement.model.Exposition;
import com.sha.microservicemuseemanagement.model.Oeuvre;
import com.sha.microservicemuseemanagement.model.Salle;
import com.sha.microservicemuseemanagement.service.ExpositionService;
import com.sha.microservicemuseemanagement.service.OeuvreService;
import com.sha.microservicemuseemanagement.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.util.*;

@RestController
public class OeuvresController {

    @Autowired
    private OeuvreService oeuvreService;

    @Autowired
    private SalleService salleService;

    @Autowired
    private ExpositionService expositionService;


    @DeleteMapping(value = "/oeuvres/{id}")
    public ResponseEntity<?>detruit(@PathVariable long id){
        Oeuvre oeuvre=oeuvreService.getOeuvreById(id);
        oeuvreService.supprimerOeuvre(oeuvre);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/oeuvres")
    public ResponseEntity<?> findAllOeuvre()
    {
        return ResponseEntity.ok(oeuvreService.allOeuvre());
    }

    @GetMapping(value = "/reserves")
    public ResponseEntity<?>getReserves(){
        return ResponseEntity.ok(oeuvreService.trouverOeuvresEnReserve());
    }

    @GetMapping(value = "/exposes")
    public ResponseEntity<?>getExposes(){
        return ResponseEntity.ok(oeuvreService.trouveOeuvresExpose());

    }


    @PutMapping("/restauration/{id}")
    public ResponseEntity<?>restaure(@PathVariable long id){
        Oeuvre oeuvre=oeuvreService.getOeuvreById(id);
        oeuvre.setRestaure(true);
        oeuvre.setIsExpose(false);
        return  ResponseEntity.ok(oeuvreService.ajouterOeuvre(oeuvre));
    }



    @PutMapping(value = "/reserves/{id}")
    public ResponseEntity<?>reservesspe(@PathVariable long id)
    {
        Oeuvre oeuvre=oeuvreService.getOeuvreById(id);
        Salle salle=salleService.getSalleById(oeuvre.getSalle().getId());
        salle.getOeuvres().remove(oeuvre);
        salleService.ajouterSalle(salle);
        oeuvre.setIsExpose(false);
        oeuvre.setSalle(null);
        return ResponseEntity.ok(oeuvreService.ajouterOeuvre(oeuvre));
    }

    @GetMapping(value = "/oeuvres/{oeuvreId}")
    public ResponseEntity<?> findOeuvreById(@PathVariable long oeuvreId)
    {
        return ResponseEntity.ok(oeuvreService.getOeuvreById(oeuvreId));
    }

    @PostMapping(value = "/prets")
    public ResponseEntity<?>enregistrerPret(@RequestBody Oeuvre oeuvre){
        oeuvre.setIsExpose(false);
        oeuvre.setDate( new Date().toString());
        oeuvre.setIsAchete(false);
        return new ResponseEntity<>(oeuvreService.ajouterOeuvre(oeuvre), HttpStatus.CREATED);
    }

    @GetMapping(value = "/prets")
    public ResponseEntity<?>getPrets(){
        return ResponseEntity.ok(oeuvreService.trouverPret());
    }

    @PostMapping(value = "/oeuvres")
    public ResponseEntity<?> enregistrerOeuvre(@RequestBody Oeuvre oeuvre)
    {
        oeuvre.setIsExpose(false);
        oeuvre.setDate( new Date().toString());
        oeuvre.setIsAchete(true);
        return new ResponseEntity<>(oeuvreService.ajouterOeuvre(oeuvre), HttpStatus.CREATED);
    }


    @GetMapping(value = "/salles/{id}/oeuvres")
    public ResponseEntity<?>oeuvresdunesalle(@PathVariable long id){
        Salle salle=salleService.getSalleById(id);
        return ResponseEntity.ok(salle.getOeuvres());
    }

    @GetMapping(value = "/expositions/{id}/oeuvres")
    public ResponseEntity<?>oeuvresduneexposition(@PathVariable long id){
        List<Salle> list=new ArrayList<>();
        Exposition exposition=expositionService.trouverExpositionParId(id);
        List<Oeuvre>oeuvres=new ArrayList<>();

        for(Salle salle:exposition.getSalles()){
            for(Oeuvre oeuvre:salle.getOeuvres())
                oeuvres.add(oeuvre);

        }
        return ResponseEntity.ok(oeuvres);

    }

    @PostMapping(value = "/salles/{salleid}/oeuvres/{oeuvresid}")
    public ResponseEntity<?>lieoeuvressalles(@PathVariable long salleid,@PathVariable long oeuvresid){
        Salle salle=salleService.getSalleById(salleid);
        Oeuvre oeuvre=oeuvreService.getOeuvreById(oeuvresid);
        salle.getOeuvres().add(oeuvre);
        salleService.ajouterSalle(salle);
        oeuvre.setSalle(salle);
        if(map.containsKey(oeuvre.getId()))
        {
            map.get(oeuvresid).onNext(oeuvre);
        }

        return ResponseEntity.ok(oeuvreService.ajouterOeuvre(oeuvre));
    }

    @PostMapping(value = "/oeuvres/{oeuvresid}/salles/{salleid}")
    public ResponseEntity<?>liesalleoeuvre(@PathVariable long salleid,@PathVariable long oeuvresid){
        Salle salle=salleService.getSalleById(salleid);
        Oeuvre oeuvre=oeuvreService.getOeuvreById(oeuvresid);
        salle.getOeuvres().add(oeuvre);
        oeuvre.setSalle(salle);
        oeuvreService.ajouterOeuvre(oeuvre);
        return ResponseEntity.ok(        salleService.ajouterSalle(salle));
    }

    private Map<Long,ReplayProcessor<Oeuvre>> map = new HashMap<>();
    @GetMapping(value = "/oeuvres/{oeuvresid}/sortiereserve",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Oeuvre> notification(@PathVariable long oeuvresid)
    {
        if(!map.containsKey(oeuvresid))
        {
            map.put(oeuvresid,ReplayProcessor.create(0,false));
        }
        return Flux.from(map.get(oeuvresid));
    }




    @GetMapping("/restauration/oeuvres")
    public ResponseEntity oeuvreEnRestauration(){
        return ResponseEntity.ok(oeuvreService.trouverOeuvresEnRestauration());
    }



}
