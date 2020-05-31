package vues;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class Notification {




    public static void affichageSucces(String titre, String text){
        Image image=new Image("images/mooo.png");
        Notifications notification=Notifications.create()
                .title(titre)
                .text(text)
                //.hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();
    }




    public static void affichageEchec(String titre, String text){
         //Image image=new Image("images/delete.png");
        Notifications notification=Notifications.create()
                .title(titre)
                .text(text)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new Pane(new Text("probleme de connexion")));
        notification.darkStyle();
        notification.show();
    }
}
