package vues;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterExposition implements Vue, Initializable {


    public ListView oeuvres;
    public ListView salles;
    //public ListView oeuvres;
   // public Label exposition;
    //public TreeTableView<Car> treetable;
    @FXML
    TableView<Exposition> treeTableView = new TableView<>();

    @FXML
    TextField pseudo;

    @FXML
    Button go;

    private Stage stage;
    private Controleur controleur;


    private void setScene(Scene scene) {
        this.scene = scene;
    }
    private void setStage(Stage stage) {
        this.stage = stage;
    }

    private Scene scene;


    public static ConsulterExposition creer(Stage stage) {
        URL location = ConsulterExposition.class.getResource("consulterExposition.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConsulterExposition vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.chargerSalles();
        this.stage.show();
    }

    private void initialiserBouton() {
        //this.go.setOnAction(e -> connexion());
    }



    @Override
    public void chargerDonnees() {

    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }


    public void chargerSalles(){


        this.treeTableView.getItems().clear();

        List<Exposition> liste=new ArrayList<>();
        int cpt=0;
        facade facade2=new facade();
        for(Exposition salle:facade2.getAllExposition()){
            if(cpt==0){

                Exposition exposition= new Exposition();
                exposition.setId(salle.getId());
                exposition.setDate_debut(salle.getDate_debut());
                exposition.setDate_fin(salle.getDate_fin());
                exposition.setTitle(salle.getTitle());
                liste.add(exposition);
            }

        }

        treeTableView.getItems().addAll(liste);



        // Add columns to TreeTable.




        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                remlirdoublieliste(newValue));



    }

    private void remlirdoublieliste(Exposition newValue) {
        if(newValue!=null){
            salles.getItems().clear();
            oeuvres.getItems().clear();
            facade facade=new facade();
            Exposition exposition=facade.getExpoParId(newValue.getId().toString());
            List<Salle>salles=facade.getSalleExpo(exposition.getId().toString());
            for(Salle salle:salles){
                this.salles.getItems().add(salle);
            }

            List<Oeuvre>oeuvres=facade.getOeuvresExp(newValue.getId().toString());
            for(Oeuvre oeuvre:oeuvres){
                this.oeuvres.getItems().add(oeuvre);
            }



        }
    }

    public TableColumn<Exposition, String> expoId ;
    public  TableColumn<Exposition, String> expoTitre;
    public  TableColumn<Exposition, String> expoDateDeb;
    public  TableColumn<Exposition, String> expoDateFin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

/*
        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                remplirListe(newValue));

 */       // Create column EmpNo (Data type of String).
        expoId //
                = new TableColumn<Exposition, String>("id");

        expoTitre
                = new TableColumn<Exposition, String>("title");

        expoDateDeb=new TableColumn<Exposition,String>("date_debut");
        expoDateFin=new TableColumn<Exposition,String>("date_fin");




        // Defines how to fill data for each cell.
        // Get value from property of Employee.
        expoId.setCellValueFactory(new PropertyValueFactory<Exposition, String>("id"));

        expoTitre.setCellValueFactory(new PropertyValueFactory<Exposition,String>("title"));
        expoDateDeb.setCellValueFactory(new PropertyValueFactory<Exposition,String>("date_debut"));
        expoDateFin.setCellValueFactory(new PropertyValueFactory<Exposition,String>("date_fin"));


        //

        treeTableView.getColumns().addAll(expoId, expoTitre,expoDateDeb,expoDateFin);



    }

    private void remplirListe(SalleDto newValue) {
       /* if(newValue!=null){
            oeuvres.getItems().clear();
            facade facade=new facade();
            List<Oeuvre>list=facade.getOeuvresSalles(String.valueOf(newValue.getId()));
            for(Oeuvre oeuvre:list){
                this.oeuvres.getItems().add(oeuvre.getNomOeuvre()+" "+oeuvre.getDate());
            }

            Salle salle=facade.getSalleById(newValue.id);
            if(salle.getExposition()!=null){
                this.exposition.setText(salle.exposition.getTitle());
            }

        }
*/
    }

    private void showDetailsClient(SalleDto newValue) {

    }

    public void gotomenu(MouseEvent mouseEvent)
    {
   //     this.exposition.setText("");
        oeuvres.getItems().clear();
        salles.getItems().clear();
        controleur.afficherMenu();
    }
}
