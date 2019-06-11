package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

    /**
     *  Program graficzny służący do rysowania prostych figur geometrycznych
     *  @author Piotr Szyma
     *  @version 1.0
     *  @since 22-04-2016
     *
     *  Klasa główna, od której rozpoczyna działanie cały program.
     *  Znajduje się w niej publiczna metoda start inicjująca otwarcie
     *  głównego okna programu - {@code FXMLMainWindow}.
     */

public class Main extends Application {

    Scene scene;
    public static Stage stage;


    @Override
    public void start(Stage stage) throws Exception{

        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainWindow.fxml"));
        stage.setTitle("Program graficzny 1.0");
        scene = new Scene(root);

        scene.getStylesheets().add("sample/style.css");

        stage.setScene(scene);
        stage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
