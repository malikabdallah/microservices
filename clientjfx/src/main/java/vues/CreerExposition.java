package vues;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Exposition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreerExposition implements Vue, Initializable {

    public TextField titre;
    public TextField categorie;
    public DatePicker datedebut;
    public DatePicker datefin;
    public TextArea description;
    private Controleur controleur;
    private Scene scene;
    private Stage stage;

    public void gotomenu(MouseEvent mouseEvent) {
        controleur.afficherMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public static CreerExposition creer(Stage stage) {
        URL location = CreerExposition.class.getResource("creerExposition.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreerExposition vue = fxmlLoader.getController();
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


    @Override
    public void show() {

        this.stage.setScene(this.scene);
        this.stage.show();

    }

    @Override
    public void chargerDonnees() {

    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur=controleur;

    }

    public void choisirSalle(MouseEvent mouseEvent) {
        Exposition exposition=new Exposition();
        exposition.setCategory(this.categorie.getText());
        exposition.setDescription(this.description.getText());
        exposition.setTitle(this.titre.getText());
        exposition.setDate_debut(this.datedebut.getValue().toString());
        exposition.setDate_fin(this.datefin.getValue().toString());
        ChoisirSalle.exposition=exposition;
        controleur.lancerChoisirSalle();
    }
}
