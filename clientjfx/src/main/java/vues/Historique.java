package vues;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Evenement;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Historique implements Vue, Initializable {



    public static Historique creer(Stage stage) {
        URL location = Historique.class.getResource("historique.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Historique vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        return vue;
    }

    public void setScene(Scene scene){
        this.scene=scene;
    }


    public static  int idOeuvre;
    public ListView list;
    private Controleur controleur;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    private Stage stage;
    private Scene scene;
    @Override
    public void show() {

        this.stage.setScene(this.scene);
        chargerHistorique();
        this.stage.show();
    }

    private void chargerHistorique() {
        this.list.getItems().clear();
        facade facade;
        facade=new facade();
        for(Evenement evenement:facade.getHistoriqueOeuvre(idOeuvre)) {
            this.list.getItems().add(evenement.getEvenement());

        }
    }

    @Override
    public void chargerDonnees() {

    }

    @Override
    public void initialiserControleur(Controleur controleur) {

        this.controleur=controleur;
    }

    public void menu(MouseEvent mouseEvent) {
        idOeuvre=0;
        controleur.afficherMenu();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
