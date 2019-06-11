package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *  Program do generowania wątków, przedstawia je za pomocą kwadratów
 *  @author Piotr Szyma
 *  @version 1.0
 *  @since 11-05-2016
 *
 *  Klasa główna, od której rozpoczyna działanie cały program.
 *  Znajduje się w niej publiczna metoda start inicjująca otwarcie
 *  głównego okna programu - {@code Controller}.
 */

public class Main extends Application {


    /**
     * Metoda wywołująca okno główne programu z ustawieniami.
     * @param primaryStage Główna scena programu
     * @throws Exception Wyrzuca wyjątki
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Generator wątków");
        primaryStage.getIcons().add(new Image("sample/icon.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
