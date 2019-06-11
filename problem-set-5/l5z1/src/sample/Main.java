package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    //Strings for actual background color and font size
    static String BACKGROUND_COLOR = "white";
    static String FONT_SIZE = "14px";

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        //Set main screen title and block user window adjustment
        primaryStage.setTitle("Liczby Pierwsze");
        primaryStage.setResizable(false);

        //Scene with main app screen
        Scene scene = new Scene(loadMainScreen(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    //Main stage
    public GridPane loadMainScreen()
    {
        //Create new GridPane
        GridPane grid = new GridPane();

        //Set background color and font size
        grid.setStyle(  "-fx-background-color: " + BACKGROUND_COLOR +";" +
                "-fx-font-size: " + FONT_SIZE + ";");

        //Padding, H&V gaps
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(20);
        grid.setVgap(20);

        //Scope label
        Label scope = new Label("Zakres:");
        grid.add(scope, 0, 1);

        //Scope TextField
        TextField scopeText = new TextField();
        scopeText.setPromptText("Podaj zakres");
        grid.add(scopeText, 1, 1);

        //Other parameters label
        Label params = new Label("Ilość:");
        grid.add(params, 0, 2);

        //Other parameters TextField
        TextField paramsText = new TextField();
        paramsText.setPromptText("Podaj liczbę parametrów");
        grid.add(paramsText, 1, 2);

        //OK Button
        Button b1 = new Button("OK");
        grid.add(b1, 1, 3);
        grid.setHalignment(b1, HPos.RIGHT);

        //OK Button - on action
        b1.setOnAction(e -> {
            if(paramsText.getText().equals("")||scopeText.getText().equals(""))
            {
                AlertBox.display("Błąd", "Wpisz poprawnie wszystkie dane.");
            } else if(!isInteger(scopeText.getText())||Integer.parseInt(scopeText.getText())<2)
            {
                AlertBox.display("Błąd", scopeText.getText() + " - nieprawidłowa dana");
            }else if (!isInteger(paramsText.getText())||Integer.parseInt(paramsText.getText())<1)
            {
                AlertBox.display("Błąd", paramsText.getText() + " - nieprawidłowa dana");
            } else {
                SecondBox.Start(Integer.parseInt(scopeText.getText()), Integer.parseInt(paramsText.getText()));
            }

        });

        //Menu Button
        Button b2 = new Button("Ustawienia");
        grid.add(b2, 1, 3);
        grid.setHalignment(b2, HPos.LEFT);

        b2.setOnAction(e -> {
            b2.getScene().setRoot(loadSettingsScreen());;
        });


        return grid;

    }

    //Settings stage
    public GridPane loadSettingsScreen()
    {

        //Create a new stage
        Stage window = new Stage();

        //Stage settings - prevent from moving to different window, set title, min width/height, block screen adjustment
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Liczby Pierwsze");
        window.setMinWidth(350);
        window.setMinHeight(150);
        window.setResizable(false);

        //Grid
        GridPane grid = new GridPane();

        //Grid style - background color and font size, padding, H&V gaps
        grid.setStyle(  "-fx-background-color: " + BACKGROUND_COLOR +";" +
                "-fx-font-size: " + FONT_SIZE + ";");
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(20);
        grid.setVgap(20);



        //Label
        Label params = new Label("Kolor tła:");
        grid.add(params, 0, 1);

        //Color ComboBox
        ComboBox<String> colorCB = new ComboBox<>();
        colorCB.getItems().addAll("white", "gray", "blue", "red", "orange", "pink");
        colorCB.setValue(BACKGROUND_COLOR);
        grid.add(colorCB, 1, 1);

        //Label
        Label params2 = new Label("Rozmiar czcionki:");
        grid.add(params2, 0, 2);

        //Color ComboBox
        ComboBox<String> fontCB = new ComboBox<>();
        fontCB.getItems().addAll("10px", "12px", "14px", "16px");
        fontCB.setValue(FONT_SIZE);
        grid.add(fontCB, 1, 2);


        //OK Button
        Button b1 = new Button();
        b1.setText("OK");
        grid.add(b1, 2, 3);
        grid.setHalignment(b1, HPos.RIGHT);

        //ComboBox color change
        colorCB.setOnAction( e -> {
            grid.setStyle(  "-fx-background-color: " + colorCB.getValue() +";" +
                    "-fx-font-size: " + fontCB.getValue() + ";");
        });

        //ComboBox font change
        fontCB.setOnAction( e -> {
            grid.setStyle(  "-fx-background-color: " + colorCB.getValue() +";" +
                    "-fx-font-size: " + fontCB.getValue() + ";");
        });

        //Change program background color and font size
        b1.setOnAction(e -> {
            BACKGROUND_COLOR = colorCB.getValue();
            FONT_SIZE = fontCB.getValue();
            b1.getScene().setRoot(loadMainScreen());

        });

        return grid;

    }

    //Additional function that checks if string is an integer
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
