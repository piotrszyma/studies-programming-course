package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    /**
     * Klasa FXMLMainWindowController to główne okno programu.
     * W tym oknie użytkownik moze generować figury geometryczne.
     */

public class FXMLMainWindowController implements Initializable {

    public Pane WindowPane;
    public Pane workspace;

    /**
     * Metoda initialize wywoływana zaraz po uruchomieniu okna.
     * @param location URL - ścieżka do korzenia, NULL jeśli lokalizacja korzenia nieznana.
     * @param resources Zasoby służące do lokalizacji korzenia, NULL jeśli lokalizacja korzenia nie została odnaleziona.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.stage.setResizable(false);

    }

    /**
     * Metoda OnSettingsButton() wywołuje się po kliknięciu na przycisk
     * ustawień znajdujący się w menu w górnym pasku okna głównego.
     * Po wywołaniu tej metody na ekranie pojawia się okno ustawień
     * w którym użytkownik może zmienić domyślne ustawienia takie jak
     * rozmiar prostokąta czy domyślny kolor.
     */

    @FXML
    public void OnSettingsButton()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLSettingsWindowController.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ustawienia");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(IOException e)
        {
            AlertBox.display("Błąd", "Problem z wczytaniem sekcji ustawień");
        }

    }

    /**
     * Metoda OnInfoButton() wywołuje się po kliknięciu na przycisk
     * ustawień znajdujący się w menu w górnym pasku okna głównego.
     * Po wywołaniu tej metody na ekranie pojawia się okno zawierające
     * podstawowe informacje nt. programu - nazwę, wersję, autora oraz
     * krótki opis funkcjonalności programu.
     */

    @FXML
    public void OnInfoButton()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLInfoWindowController.fxml"));
            Parent root2 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ustawienia");
            stage.setScene(new Scene(root2));
            stage.show();
        } catch(IOException e)
        {
            AlertBox.display("Błąd", "Problem z wczytaniem sekcji informacji");
        }

    }

    /**
     * Metoda OnRectMouseClick() wywołuje się po naciśnięciu przycisku prostokąta
     * na górnym panelu w oknie głównym. Po uruchomieniu tej metody kursor zmienia swój
     * wygląd i oczekuje na wybór punktu w którym ma zostać wygenerowany nowy prostokąt
     * przez użytkownika. Po wygenerowaniu prostokąta kursor ponownie wraca do standardowego
     * wyglądu, a w przestrzeni roboczej pojawia się nowa figura.
     */
    @FXML
    public void OnRectMouseClick() { RectangleDraw figure = new RectangleDraw(workspace); }

    /**
     * Metoda OnCircleMouseClick() wywołuje się po naciśnięciu przycisku okręgu
     * na górnym panelu w oknie głównym. Po uruchomieniu tej metody kursor zmienia swój
     * wygląd i oczekuje na wybór punktu w którym ma zostać wygenerowany nowy okrąg
     * przez użytkownika. Po wygenerowaniu okregu kursor ponownie wraca do standardowego
     * wyglądu, a w przestrzeni roboczej pojawia się nowa figura.
     */

    @FXML
    public void OnCircleMouseClick() {
        CircleDraw figure = new CircleDraw(workspace);
    }

    /**
     * Metoda OnPolygonMouseClick() wywołuje się po naciśnięciu przycisku wielokąta
     * na górnym panelu w oknie głównym. Po uruchomieniu tej metody kursor zmienia swój
     * wygląd i oczekuje na wybór serii punktów, w których będą znajdować się wierzchołki
     * wielokąta. Po wygenerowaniu odpowiedniej ilości wierzchołków użytkownik naciska prawy
     * przycisk myszy. Program wychodzi wtedy z trybu tworzenia wielokąta i kursor
     * wraca do standardowego wyglądu, a w przestrzeni roboczej pojawia się nowa figura.
     */

    @FXML
    public void OnPolygonMouseClick() {
        PolygonDraw figure = new PolygonDraw(workspace);
    }




}


