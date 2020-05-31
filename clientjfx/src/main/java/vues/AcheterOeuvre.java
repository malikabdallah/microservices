package vues;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Oeuvre;
import modele.Salle;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AcheterOeuvre implements Vue, Initializable {
    public TextField nom;
    private Controleur controleur;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @Override
    public void chargerDonnees() {

    }



    public static AcheterOeuvre creer(Stage stage) {
        URL location = AcheterOeuvre.class.getResource("acheterOeuvre.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AcheterOeuvre vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        return vue;
    }



    private void setScene(Scene scene) {
        this.scene = scene;
    }
    private void setStage(Stage stage) {
        this.stage = stage;
    }


    private Scene scene;
    private Stage stage;

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur=controleur;
    }

    public void goconsultersalle(MouseEvent mouseEvent) {
        this.controleur.lancerConsulterSalle();
    }

    public void ajoutersalle(MouseEvent mouseEvent) {
        this.controleur.lancerVueAjouterSalle();
    }

    public void gotomenu(MouseEvent mouseEvent) {
        controleur.afficherMenu();
    }

    public void ajouter(MouseEvent mouseEvent) {
        facade facade=new facade();
        Salle salle=new Salle();
        salle.nomSalle=this.nom.getText();
        facade.creerUneSalle(salle);

        System.out.println(this.nom.getText());
    }

    public void acheter(MouseEvent mouseEvent) {

        facade facade=new facade();
        Oeuvre oeuvre=new Oeuvre();
        oeuvre.setNomOeuvre(this.nom.getText());
        facade.creerUneOeuvre(oeuvre);

    }
}
