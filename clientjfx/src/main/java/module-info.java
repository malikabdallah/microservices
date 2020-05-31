module exemplejfx {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.net.http;
    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    opens vues to javafx.fxml,org.controlsfx.controls;
    opens images to org.controlsfx.controls,javafx.fxml;
    opens modele to javafx.fxml,javafx.base;
    exports modele to com.fasterxml.jackson.databind,javafx.base;
    exports application;
}