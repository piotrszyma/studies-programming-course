package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Główna klasa programu, zawiera metody main oraz start
 * */

public class Main extends Application {

    Stage stage;

    /**
     * Metoda start wywołuje kontroler - główne okno programu
     * @param stage Scena z głównym programem
     * @throws Exception Wyrzuca wyjątki pojawiające się podczas tworzenia okna z głównym programem
     */
    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller myController = loader.getController();
        myController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.setTitle("Klient BST");
        stage.getScene().getStylesheets().add("sample/stylesheet.css");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();


    }

    /**
     * Metoda main wywoływana zaraz po uruchomieniu programu
     * @param args Argumenty wywołania programu
     */

    public static void main(String[] args) {
        launch(args);
    }
}
