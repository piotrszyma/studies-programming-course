package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by thomp on 04.04.2016.
 */
public class AlertBox {

    public static void display(String title, String message){

        Stage window = new Stage();


        //Stage settings - prevent from moving to different window, set title, min width/height, block screen adjustment
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

        //Adjust the scene style to style provided in settings by user
        layout.setStyle(  "-fx-background-color: " + Main.BACKGROUND_COLOR +";" +
                "-fx-font-size: " + Main.FONT_SIZE + ";");

        Scene scene1 = new Scene(layout);
        window.setScene(scene1);
        window.showAndWait();
    }
}
