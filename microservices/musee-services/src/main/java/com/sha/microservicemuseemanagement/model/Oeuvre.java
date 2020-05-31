package com.sha.microservicemuseemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name ="oeuvre")
public class Oeuvre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @ManyToOne
    @JsonIgnore
    private Exposition exposition;
*/

    @ManyToOne
    private Salle salle;





    @Column(name = "nom_oeuvre")
    private String nomOeuvre;


    @Column(name = "date")
    private String date;


    @Column(name = "is_expose")
    private Boolean isExpose;

    @Column(name = "is_achete")
    private Boolean isAchete;



    @Column(name="is_restaure")
    private boolean isRestaure;




}
