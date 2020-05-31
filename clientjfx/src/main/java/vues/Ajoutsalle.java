package vues;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Salle;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ajoutsalle implements Vue, Initializable {
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



    public static Ajoutsalle creer(Stage stage) {
        URL location = Ajoutsalle.class.getResource("ajoutsalle.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ajoutsalle vue = fxmlLoader.getController();
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
            salle.setDisponible(true);
            facade.creerUneSalle(salle);

        System.out.println(this.nom.getText());
    }
}
