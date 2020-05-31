package vues;

import controleur.Controleur;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.function.Predicate;

public class ConsulterOeuvre implements Vue, Initializable {

    public RadioButton expose;
    public RadioButton reserves;
    public RadioButton tout;
    public RadioButton restaurationRadioButton;


    public TableView <Oeuvre>treeTableView;
    public TableColumn<Oeuvre, String> idOeuvre ;
    public  TableColumn<Oeuvre, String> nomOeuvre;
    public  TableColumn<Oeuvre, String> dateAchat;

    public Label id;
    public Label nom;
    public Label date;
    public Label pret;
    public TextField recherche;
    public Button btnexpose;
    public Button btnreserves;
    public Button rendre;
    public Button restaure;
    private Controleur controleur;
    public void gotomenu(MouseEvent mouseEvent) {

        this.id.setText("");
        this.nom.setText("");
        this.date.setText("");
        this.pret.setText("");
        controleur.afficherMenu();
    }

    void chargerReserves(){

        this.treeTableView.getItems().clear();

        // Root Item
        List<Oeuvre> liste=new ArrayList<>();
        int cpt=0;
        facade facade2=new facade();
        for(Oeuvre salle:facade2.getReserves()){
            if(cpt==0){

                if(salle.isRestaure()==false) {
                    Oeuvre oeuvre = new Oeuvre();
                    oeuvre.setId(salle.getId());
                    oeuvre.setDate(salle.getDate());
                    oeuvre.setNomOeuvre(salle.getNomOeuvre());
                    liste.add(oeuvre);
                }

            }

        }

        treeTableView.getItems().addAll(liste);

    }


    public  void chargeExpose(){


        this.treeTableView.getItems().clear();

        // Root Item

        int cpt=0;
        facade facade2=new facade();
        List<Oeuvre> liste=new ArrayList<>();

        for(Oeuvre salle:facade2.getExposes()){
            if(cpt==0){

                Oeuvre oeuvre=new Oeuvre();
                oeuvre.setId(salle.getId());
                oeuvre.setDate(salle.getDate());

                oeuvre.setNomOeuvre(salle.getNomOeuvre());
                liste.add(oeuvre);
            }

        }

        treeTableView.getItems().addAll(liste);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        expose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reserves.setSelected(false);
                tout.setSelected(false);
                restaurationRadioButton.setSelected(false);
                //  remplirListeClient();
                    //Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                    //RemplirClientSejour(sejour);

                chargeExpose();


            }
        });



        reserves.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                expose.setSelected(false);
                tout.setSelected(false);
                restaurationRadioButton.setSelected(false);

                chargerReserves();





            }


        });


        tout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                expose.setSelected(false);
                reserves.setSelected(false);
                restaurationRadioButton.setSelected(false);

                 chargerOeuvres();


            }
        });


        restaurationRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                expose.setSelected(false);
                reserves.setSelected(false);
                tout.setSelected(false);

                chargerRestauration();


            }
        });



        dateAchat=new TableColumn<Oeuvre,String>("date");

        idOeuvre
                    = new TableColumn<Oeuvre, String>("id");

        nomOeuvre
                = new TableColumn<Oeuvre, String>("nom");




        // Defines how to fill data for each cell.
        // Get value from property of Employee.
        idOeuvre.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("id"));
        nomOeuvre.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("nomOeuvre"));
        dateAchat.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("date"));


        //

        treeTableView.getColumns().addAll(idOeuvre, nomOeuvre,dateAchat);


        tout.setSelected(true);

        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailOeuvre(newValue));




        optimiserrechercheoeuvre();



    }

    private void chargerRestauration() {


        this.treeTableView.getItems().clear();

        // Root Item
        List<Oeuvre> liste=new ArrayList<>();
        int cpt=0;
        facade facade2=new facade();
        for(Oeuvre salle:facade2.oeuvreEnRestauration()){
            if(cpt==0){

                Oeuvre oeuvre=new Oeuvre();
                oeuvre.setId(salle.getId());
                oeuvre.setDate(salle.getDate());

                oeuvre.setNomOeuvre(salle.getNomOeuvre());
                liste.add(oeuvre);
            }

        }

        treeTableView.getItems().addAll(liste);
    }

    private void optimiserrechercheoeuvre() {
        recherche.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                if (recherche.textProperty().get().isEmpty()) {
                    facade facade=new facade();
                    ObservableList<Oeuvre> oListStavaka = FXCollections.observableArrayList(facade.getAllOeuvres());

                    treeTableView.setItems(oListStavaka);
                    return;
                }
                facade facade=new facade();
                ObservableList<Oeuvre> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Oeuvre, ?>> cols = treeTableView.getColumns();
                List<Oeuvre>list=facade.getAllOeuvres();
                for (int i = 0; i <list.size() ; i++) {


                    if(list.get(i).getNomOeuvre().toLowerCase().contains(recherche.textProperty().get())
                    ||list.get(i).getDate().toLowerCase().contains(recherche.textProperty().get())
                    ||list.get(i).getId().toString().toLowerCase().contains(recherche.textProperty().get())){
                            tableItems.add(list.get(i));
                        }
                    }

                treeTableView.setItems(tableItems);

            }
        });
    }



    private void showDetailOeuvre(Oeuvre newValue) {
        if(newValue!=null) {
            facade facade = new facade();

            Oeuvre oeuvre = facade.getOeuvreById(newValue.getId());
            this.id.setText(String.valueOf(oeuvre.getId()));
            this.nom.setText(oeuvre.getNomOeuvre());
            this.date.setText(String.valueOf(oeuvre.getDate()));
            this.pret.setText(String.valueOf(oeuvre.getIsAchete()));



            if(oeuvre.getIsExpose()==false && oeuvre.isRestaure()==false){
                this.rendre.setDisable(false);
            }else{
                this.rendre.setDisable(true);
            }


            if(oeuvre.getIsExpose()==true){
                System.out.println("1");
                this.btnreserves.setDisable(false);
                this.restaure.setDisable(true);
                this.btnexpose.setDisable(true);
            }else{
                if(oeuvre.isRestaure()==false){
                    //reserves
                    System.out.println("2");
                    this.btnreserves.setDisable(true);

                    if(oeuvre.getIsAchete()==true){
                        this.rendre.setDisable(true);
                        System.out.println("3");

                    }else{
                        System.out.println("4");


                        this.rendre.setDisable(false);
                    }
                    this.btnexpose.setDisable(false);
                    this.restaure.setDisable(false);
                }else{

                    System.out.println("5");

                    //restauration

                    this.btnreserves.setDisable(false);
                    this.rendre.setDisable(true);
                    this.btnexpose.setDisable(true);
                    this.restaure.setDisable(true);
                }
            }




        }else{
            this.btnreserves.setDisable(true);
            this.btnexpose.setDisable(true);
            this.restaure.setDisable(true);

        }
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        chargerOeuvres();
        this.stage.show();

    }

    private void chargerOeuvres() {


        this.treeTableView.getItems().clear();

        // Root Item
        List<Oeuvre> liste=new ArrayList<>();
        int cpt=0;
        facade facade2=new facade();
        for(Oeuvre salle:facade2.getAllOeuvres()){
            if(cpt==0){

                Oeuvre oeuvre=new Oeuvre();
                oeuvre.setId(salle.getId());
                oeuvre.setDate(salle.getDate());

                oeuvre.setNomOeuvre(salle.getNomOeuvre());
                liste.add(oeuvre);
            }

        }

        treeTableView.getItems().addAll(liste);
    }

    @Override
    public void chargerDonnees() {

    }


    public static ConsulterOeuvre creer(Stage stage) {
        URL location = ConsulterOeuvre.class.getResource("consulterOeuvre.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConsulterOeuvre vue = fxmlLoader.getController();
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

    public void mettreenreservd(MouseEvent mouseEvent) {
        if(!this.btnreserves.isDisabled()){
            if(this.id.getText()!="") {
                facade facade=new facade();

                System.out.println("mise en reserve");
                facade.MettreEnReserves(Long.parseLong(this.id.getText()));
                this.chargerOeuvres();
            }
        }else{
        }

    }

    public void exposeoeuvre(MouseEvent mouseEvent) {

        if(!this.btnexpose.isDisabled()){
            if(this.id.getText()!="") {
                Expose.ID=this.id.getText();
                controleur.exposerOeuvre();


            }
        }else{
        }


    }

    public void restaure(MouseEvent mouseEvent) {

        facade facade=new facade();
        System.out.println("id ="+this.id.getText());
        Oeuvre oeuvre=facade.getOeuvreById(Long.parseLong(this.id.getText()));
        oeuvre.setIsExpose(false);
        facade.mettreEnRestauration(oeuvre.getId());


        this.nom.setText("");
        this.id.setText("");
        this.pret.setText("");
        this.date.setText("");
        controleur.afficherMenu();

    }

    public void historique(MouseEvent mouseEvent) {
        if(this.id.getText()!=null) {
            Historique.idOeuvre = Integer.parseInt(this.id.getText());
            controleur.lancerHistorique();
        }
    }
}
