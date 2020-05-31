package modele;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class Salle  implements Serializable {


    @JsonProperty("id")
    public Long id;

    @JsonProperty("exposition")
    public Exposition exposition;


    @JsonProperty("oeuvres")
    public   List<Oeuvre> oeuvres;



    @JsonProperty("isDisponible")
    public Boolean isDisponible;


    @JsonProperty("nomSalle")
    public String nomSalle;

    @Override
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", exposition=" + exposition +
                ", oeuvres=" + oeuvres +
                ", isDisponible=" + isDisponible +
                ", nomSalle='" + nomSalle + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public String getNomSalle() {
        return this.nomSalle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public List<Oeuvre> getOeuvres() {
        return oeuvres;
    }

    public void setOeuvres(List<Oeuvre> oeuvres) {
        this.oeuvres = oeuvres;
    }

    public Boolean getDisponible() {
        return isDisponible;
    }

    public void setDisponible(Boolean disponible) {
        isDisponible = disponible;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }
}
