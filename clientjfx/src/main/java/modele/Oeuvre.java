package modele;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Oeuvre implements Serializable {


    @JsonProperty("id")
    private Long id;

    /*
    @ManyToOne
    @JsonIgnore
    private Exposition exposition;
*/

    @JsonProperty("salle")
    private Salle salle;




    @JsonProperty("nomOeuvre")
    private String nomOeuvre;


    @JsonProperty("date")
    private String date;


    @JsonProperty("isExpose")
    private Boolean isExpose;

    @JsonProperty("isAchete")
    private Boolean isAchete;

    @JsonProperty("restaure")
    private boolean isRestaure;

    public Long getId() {
        return id;
    }

    public Salle getSalle() {
        return salle;
    }

    public String getNomOeuvre() {
        return nomOeuvre;
    }

    public String getDate() {
        return date;
    }

    public Boolean getIsExpose() {
        return isExpose;
    }

    public Boolean getIsAchete() {
        return isAchete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setNomOeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsExpose(Boolean expose) {
        isExpose = expose;
    }

    public void setIsAchete(Boolean prete) {
        isAchete = prete;
    }

    public boolean isRestaure() {
        return isRestaure;
    }

    public void setRestaure(boolean restaure) {
        isRestaure = restaure;
    }

    @Override
    public String toString() {
        return "Oeuvre{" +
                "id=" + id +
                ", salle=" + salle +
                ", nomOeuvre='" + nomOeuvre + '\'' +
                ", date='" + date + '\'' +
                ", isExpose=" + isExpose +
                ", isAchete=" + isAchete +
                ", isRestaure=" + isRestaure +
                '}';
    }

    /* getters and setters */


}
