package modele;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exposition implements Serializable {


    private Long id;

    private List<Salle> salle=new ArrayList<>();

    private String title;


    private String category;


    private String date_debut;

    private String date_fin;


    private String Description;


    //@JoinColumn(name = "post_id")
    private List<Comment> commentList;


    /* getters and setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setSalles(List<Salle> salles) {
        this.salle = salles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Salle> getSalle() {
        return salle;
    }

    public void setSalle(List<Salle> salle) {
        this.salle = salle;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }
}
