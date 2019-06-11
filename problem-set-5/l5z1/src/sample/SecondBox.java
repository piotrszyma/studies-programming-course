package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by thomp on 04.04.2016.
 */
public class SecondBox {



    public static void Start(int arrayscope, int numofargs){

        Stage window = new Stage();

        //Stage settings - prevent from moving to different window, set title, min width/height, block screen adjustment
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Liczby Pierwsze");
        window.setMinWidth(350);
        window.setMinHeight(150);
        window.setResizable(false);

        //Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(20);
        grid.setVgap(20);

        //Label
        Label params = new Label("Parametry:");
        grid.add(params, 0, 1);

        //TextField
        TextField paramsText = new TextField();
        paramsText.setPromptText("Podaj parametry");
        grid.add(paramsText, 1, 1);


        //OK Button
        Button b1 = new Button();
        b1.setText("OK");
        grid.add(b1, 1, 3);
        grid.setHalignment(b1, HPos.RIGHT);


        b1.setOnAction(e -> {

            //Check if content in paramsText is valid
                if(paramsText.getText().equals(""))
                {

                    AlertBox.display("Błąd", "Podaj parametr!");
                } else if(paramsText.getText().charAt(0)==' ')
                {
                    AlertBox.display("Błąd", "Błędny parametr! Podaj poprawny parametr!");
                } else {
                    LiczbyPierwsze FirstNumbers = new LiczbyPierwsze(arrayscope);

                    //Converts string containing all params to vector array of strings containing one parameter each
                    String allparams = paramsText.getText();

                    allparams = allparams + " ";
                    while (allparams.length() > 1) {

                        if (allparams.charAt(0) == ' ') {
                            allparams = allparams.substring(1);
                        } else {

                            if (!LiczbyPierwsze.isInteger(allparams.substring(0, allparams.indexOf(32)))) {
                                FirstNumbers.output.add(allparams.substring(0, allparams.indexOf(32)) + " - nieprawidlowa dana");
                            } else {
                                FirstNumbers.liczba(Integer.parseInt(allparams.substring(0, allparams.indexOf(32))));
                            }

                        }
                        allparams = allparams.substring(allparams.indexOf(32) + 1);
                    }
                    //Checks if number of arguments provided by user is same as his previous declaration

                    if(FirstNumbers.output.size()!=numofargs)
                    {
                        AlertBox.display("Błąd", "Zadeklarowałeś inną liczbę argumentów! Popraw to.");
                    } else
                    {
                        //If it's ok, class sends vector array of strings with parameters to ResultBox class
                        ResultBox.display(FirstNumbers.output);
                    }

                }

        });


        //Adjust the scene style to style provided in settings by user
        grid.setStyle(  "-fx-background-color: " + Main.BACKGROUND_COLOR +";" +
                "-fx-font-size: " + Main.FONT_SIZE + ";");

        Scene scene1 = new Scene(grid);
        window.setScene(scene1);
        window.show();

    }



}
