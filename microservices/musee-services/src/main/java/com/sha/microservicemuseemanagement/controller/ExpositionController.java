package com.sha.microservicemuseemanagement.controller;

import com.sha.microservicemuseemanagement.model.Comment;
import com.sha.microservicemuseemanagement.model.Exposition;
import com.sha.microservicemuseemanagement.model.Participation;
import com.sha.microservicemuseemanagement.model.Salle;
import com.sha.microservicemuseemanagement.repository.CommentRepo;
import com.sha.microservicemuseemanagement.repository.ParticipationRepository;
import com.sha.microservicemuseemanagement.repository.SalleRepository;
import com.sha.microservicemuseemanagement.service.ExpositionService;
import com.sha.microservicemuseemanagement.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class ExpositionController {


    @Autowired
    private ExpositionService expositionService;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SalleService salleService;

    /*expositions */
    @GetMapping(value = "/expositions")
    public ResponseEntity<?> findAllExposition() {
        return ResponseEntity.ok(expositionService.allCourses());
    }

    @PostMapping(value = "/expositions")
    public ResponseEntity<?> enregisterExposition(@RequestBody Exposition exposition) {
        return new ResponseEntity<>(expositionService.enregistreExposition(exposition), HttpStatus.CREATED);
    }

    @GetMapping(value = "/expositions/{expositionId}")
    public ResponseEntity<?> findExpositionById(@PathVariable long expositionId) {
        return ResponseEntity.ok(expositionService.trouverExpositionParId(expositionId));
    }
    @PutMapping(value = "/expositions/{id}")
    public ResponseEntity<?>updateExposition(@PathVariable long id,@RequestBody Exposition exposition){

        Exposition exposition1=expositionService.trouverExpositionParId(id);
        exposition1.setSalles(exposition.getSalles());
        return new ResponseEntity<>(expositionService.enregistreExposition(exposition), HttpStatus.CREATED);

    }
    @PostMapping(value = "/commentaires/{idexpo}")
    public ResponseEntity<?> enregistrerCommentaire(@PathVariable long idexpo,
                                                    @RequestBody Comment comment) {
        Exposition exposition = expositionService.trouverExpositionParId(idexpo);
        comment.setPostedDate(new Date());
        exposition.getCommentList().add(comment);
        return new ResponseEntity<>(this.commentRepo.save(comment), HttpStatus.CREATED);
    }

    @PostMapping(value = "/participations")
    public ResponseEntity<?> enregistrerParticipation(@RequestBody Participation participation) {
        System.out.println("to string participation :" + participation);

        participation.setDateOfIssue(LocalDateTime.now());
        participation.setExposition(expositionService.trouverExpositionParId(participation.getExposition().getId()));
        return new ResponseEntity<>(participationRepository.save(participation), HttpStatus.CREATED);
    }

    @GetMapping(value = "/salles/{idsalle}/exposition")
    public ResponseEntity<?> renvoieexpo(@PathVariable long idsalle){
        return ResponseEntity.ok(salleService.getSalleById(idsalle).getExposition());
    }

    /* ajout d une salle a une exposition */
    @PostMapping(value = "/expositions/{id}/salle/{idsalle}")
    public ResponseEntity<?>enregistrerSalle(@PathVariable long id,@PathVariable long idsalle){
        Salle salle=salleService.getSalleById(idsalle);
        Exposition exposition=expositionService.trouverExpositionParId(id);
        salle.setExposition(exposition);
        exposition.getSalles().add(salle);
        salleService.ajouterSalle(salle);
        return ResponseEntity.ok(expositionService.enregistreExposition(exposition));
    }


    @GetMapping(value = "/expositions/commentaire/{id}")
    public ResponseEntity<?> trouverCommentaireExpo(@PathVariable Long id) {
        Exposition exposition = expositionService.trouverExpositionParId(id);
        return ResponseEntity.ok(exposition.getCommentList());
    }

    @GetMapping(value = "/expositions/{id}/commentaires")
    public ResponseEntity<?> commentaireexpo(@PathVariable Long id) {
        Exposition exposition = expositionService.trouverExpositionParId(id);
        return ResponseEntity.ok(exposition.getCommentList());
    }





}
