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
import modele.Exposition;
import modele.Salle;
import modele.SalleDto;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrganiserExposition implements Vue, Initializable {
    public TableView<SalleDto> treeTableView;









    public Button creer;
    public Label nomsalle;
    public Label nomexpo;
    public TableView<Exposition> expositions;
    public Button btnexpose;

    private String idExpo;
    public void gotomenu(MouseEvent mouseEvent) {

        this.controleur.afficherMenu();
    }







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


    public static OrganiserExposition creer(Stage stage) {
        URL location = ChoisirSalle.class.getResource("organiserExposition.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OrganiserExposition vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        //this.creer.setDisable(true);
        this.stage.setScene(this.scene);
        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailSalle(newValue));

        if(ChoisirSalle.exposition!=null) {

        }
        chargerSalles();
        chargerExpositions();
        this.stage.show();
    }



    private String idSalle;

    private void showDetailSalle(SalleDto newValue) {
        if(newValue!=null){

            this.idSalle=String.valueOf(newValue.id);
            this.nomsalle.setText(newValue.getNom());
           // this.creer.setDisable(false);

        }
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


    private void chargerExpositions() {
        expositions.getItems().clear();
        List<Exposition>list=new ArrayList<>();
        facade facade=new facade();
        for(Exposition exposition:facade.getAllExposition()){
            list.add(exposition);
        }
        expositions.getItems().addAll(list);


        expositions.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsExpo(newValue));
    }

    private void showDetailsExpo(Exposition newValue) {
        if(newValue!=null){
            this.idExpo=newValue.getId().toString();
            this.nomexpo.setText(newValue.getTitle());
        }
    }

    public void chargerSalles(){


        this.treeTableView.getItems().clear();

        // Root Item
        List<SalleDto> liste=new ArrayList<>();
        facade facade2=new facade();
        for(Salle salle:facade2.getSallesDisponible()){

            liste.add(new SalleDto(salle.id,salle.nomSalle));


        }

        treeTableView.getItems().addAll(liste);



        // Add columns to TreeTable.




        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailSalle(newValue));

    }

    public TableColumn<SalleDto, String> empNoCol ;
    public  TableColumn<SalleDto, String> lastNameCol;


    public  TableColumn<Exposition,String> expoId;
    public TableColumn<Exposition,String> expoTitre;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        expoId=new TableColumn<Exposition,String>("id");
        expoTitre=new TableColumn<Exposition,String>("title");


        // Create column EmpNo (Data type of String).
        empNoCol //
                = new TableColumn<SalleDto, String>("nom");

        lastNameCol
                = new TableColumn<SalleDto, String>("id");




        // Defines how to fill data for each cell.
        // Get value from property of Employee.
        empNoCol.setCellValueFactory(new PropertyValueFactory<SalleDto, String>("nom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<SalleDto, String>("id"));



        expoId.setCellValueFactory(new PropertyValueFactory<Exposition,String>("id"));
        expoTitre.setCellValueFactory(new PropertyValueFactory<Exposition,String>("title"));


        //

        treeTableView.getColumns().addAll(empNoCol, lastNameCol);

        expositions.getColumns().addAll(expoId,expoTitre);


    }

    public void expose(MouseEvent mouseEvent) {
        if(this.nomexpo.getText()!="" && this.nomexpo.getText()!=null && this.nomsalle.getText()!="" &&
        this.nomsalle!=null){
            System.out.println("id salle "+this.idSalle);


            System.out.println("id expo "+this.idExpo);
            facade facade=new facade();
            Salle salle=facade.getSalleById(Long.parseLong(idSalle));
            Exposition exposition=facade.getExpoParId(this.idExpo);

            salle.setExposition(exposition);
            salle.setDisponible(false);

            facade.updatesalle(salle);
            controleur.afficherMenu();

        }
    }
}
