package sample;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by thomp on 08.04.2016.
 */
public class SettingsBox {

    static String BACKGROUND_COLOR = "white";
    static String FONT_SIZE = "14px";

    public static void display(){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Liczby Pierwsze");
        window.setMinWidth(350);
        window.setMinHeight(150);
        window.setResizable(false);

        //Grid
        GridPane grid = new GridPane();

        grid.setStyle(  "-fx-background-color: " + SettingsBox.BACKGROUND_COLOR +";" +
                "-fx-font-size: " + SettingsBox.FONT_SIZE + ";");


        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(20);
        grid.setVgap(20);



        //Label
        Label params = new Label("Kolor tła:");
        grid.add(params, 0, 1);

        //Color Choice Box
        ChoiceBox<String> colorCB = new ChoiceBox<>();
        colorCB.getItems().addAll("white", "black", "gray");
        colorCB.setValue(BACKGROUND_COLOR);
        grid.add(colorCB, 1, 1);

        //Label
        Label params2 = new Label("Rozmiar czcionki:");
        grid.add(params2, 0, 2);

        //Color Choice Box
        ChoiceBox<String> fontCB = new ChoiceBox<>();
        fontCB.getItems().addAll("10px", "12px", "14px");
        fontCB.setValue(FONT_SIZE);
        grid.add(fontCB, 1, 2);


        //OK Button
        Button b1 = new Button();
        b1.setText("OK");
        grid.add(b1, 2, 3);
        grid.setHalignment(b1, HPos.RIGHT);

        b1.setOnAction(e -> {
            BACKGROUND_COLOR = colorCB.getValue();
            FONT_SIZE = fontCB.getValue();
            window.close();

        });


        Scene scene1 = new Scene(grid);
        window.setScene(scene1);
        window.show();


    }



}
