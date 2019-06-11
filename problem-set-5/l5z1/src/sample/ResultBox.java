package sample;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Vector;

/**
 * Created by thomp on 06.04.2016.
 */
public class ResultBox {
    public static void display(Vector<String> result){

        Stage window = new Stage();

        //Stage settings - prevent from moving to different window, set title, min width/height, block screen adjustment
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wynik");
        window.setMinWidth(400);
        window.setMinHeight(400);
        window.setResizable(false);

        //GridPane creation
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(20);
        grid.setVgap(20);


        //Results display
        Label text = new Label("Wyniki:");
        grid.add(text, 0, 0);
        for(int i=0; i<result.size(); i++)
        {
            Label label = new Label(result.get(i) + "\n");
            grid.add(label, 1, i);
            grid.setHalignment(label, HPos.LEFT);
        }


        //Adjust the scene style to style provided in settings by user
        grid.setStyle(  "-fx-background-color: " + Main.BACKGROUND_COLOR +";" +
                "-fx-font-size: " + Main.FONT_SIZE + ";");

        //Creating scene
        Scene scene1 = new Scene(grid, 200, 300);
        window.setScene(scene1);
        window.showAndWait();

        //Program ends
        Platform.exit();
    }
}
