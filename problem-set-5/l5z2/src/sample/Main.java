package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Konwersja liczb");


        //GridPane with 10px padding on each sides
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);


        //Name Label
        Label paramLabel = new Label();
        paramLabel.setText("Parametry:");
        GridPane.setConstraints(paramLabel, 0, 0);


        //Name TextField
        TextField paramTextField = new TextField();
        paramTextField.setPromptText("Wpisz to parametry");
        GridPane.setConstraints(paramTextField, 1, 0);

        //Start Button
        Button startButton = new Button();
        startButton.setText("Konwersja");
        GridPane.setConstraints(startButton, 1, 2);
        gridPane.setHalignment(startButton, HPos.RIGHT);

        //Actions after clicking Start Button
        startButton.setOnAction(e -> {

            try {

                if(paramTextField.getText().equals(""))
                {

                } else {

                    //Run the programme
                    Process p = Runtime.getRuntime().exec("C:\\Users\\thomp\\Desktop\\PROGRAMOWANIE\\lista5\\l5z2\\src\\sample\\l3z2.exe " + paramTextField.getText());

                    //Reader buffer creation
                    BufferedReader stdInput = new BufferedReader(new
                            InputStreamReader(p.getInputStream()));

                    //Read program output
                    String s = null;
                    Vector<String> result = new Vector<String>();

                    //Save output to vector of strings
                    while ((s = stdInput.readLine()) != null) {
                        result.add(s);
                    }

                    //Display results in external window
                    ResultBox.display(result);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });

        //Add all objects to gridPane
        gridPane.getChildren().addAll(paramLabel, paramTextField, startButton);

        //Create a scene containing gridPane
        Scene scene = new Scene(gridPane, 320, 200);

        window.setScene(scene);
        window.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
