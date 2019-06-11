package sample;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Vector;

/**
 * Created by thomp on 06.04.2016.
 */
public class ResultBox {



    public static void display(Vector<String> result){

        //Create new stage,
        Stage window = new Stage();

        //Prevent user from moving to differend windows
        window.initModality(Modality.APPLICATION_MODAL);

        //Set the title of window, make window unable to be resized by user
        window.setTitle("Wynik");
        window.setResizable(false);

        //Create GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(20);
        grid.setVgap(20);

        //Create ScrollPane to let user scroll thru wide input
        ScrollPane sp = new ScrollPane();

        //Add GridPane to ScrollPane
        sp.setContent(grid);

        //GridPane content
        Label text = new Label("Wyniki:");
        grid.add(text, 0, 0);
        for(int i=0; i<result.size(); i++)
        {
            Label label = new Label(result.get(i) + "\n");
            grid.add(label, 1, i);
            grid.setHalignment(label, HPos.LEFT);
        }

        //Close button
        Button closeButton = new Button();
        grid.add(closeButton, 1, result.size()+1);
        grid.setHalignment(closeButton, HPos.RIGHT);
        closeButton.setText("Zakończ program");

        closeButton.setOnAction(e -> {
            Platform.exit();
        });


        //Create scene and add ScrollPane (sp) to it
        Scene scene1 = new Scene(sp, 600, 500);
        window.setScene(scene1);
        window.show();

    }
}
