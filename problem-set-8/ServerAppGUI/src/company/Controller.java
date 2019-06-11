package company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Server server = new Server();
            server.run();
        } catch (IOException | NullPointerException e) {
            System.out.println("Błąd. Wyłączanie serwera... (" + e.toString() + ")");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Błąd. Wyłączanie serwera... (" + e.toString() + ")");
            alert.showAndWait();
            Platform.exit();
        }
    }
}
