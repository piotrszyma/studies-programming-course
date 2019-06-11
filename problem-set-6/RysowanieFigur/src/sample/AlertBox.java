package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

    /**
    * Klasa AlertBox zawiera okno błędów, które pojawia się wtedy, gdy program
    * komunikuje użytkownikowi o pojawiających się błędach.
    */

public class AlertBox {

    /**
     * Metoda display służy do wyświetlenia okienka błędu.
     * @param title Parametr tytuł pozwala przesłać do metody nazwę, jaką
     *              ma posiadać okno po wyświetleniu.
     * @param message Parametr wiadomość to treść, jaka zostanie wyświetlona
     *                użytkownikowi po pojawieniu się okna błędu.
     */

    public static void display(String title, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(150);
        window.setResizable(false);

        Label label1 = new Label();
        label1.setText(message);
        Button closeButton = new Button();
        closeButton.setText("OK");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout);
        window.setScene(scene1);
        window.showAndWait();
    }
}
