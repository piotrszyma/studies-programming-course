package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa FXMLInfoWindowController to okno zawierające
 * podstawowe informacje nt. programu - nazwę programu,
 * autora programu, wersję programu oraz krótki opis programu.
 */

public class FXMLInfoWindowController implements Initializable {


    @FXML
    private AnchorPane infoAnchorPane;

    /**
     * Metoda initialize wywoływana zaraz po uruchomieniu okna.
     * @param location URL - ścieżka do korzenia, NULL jeśli lokalizacja korzenia nieznana.
     * @param resources Zasoby służące do lokalizacji korzenia, NULL jeśli lokalizacja korzenia nie została odnaleziona.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * Metoda wywołuje się po naciśnięciu przycisku ZAMKNIJ w oknie
     * informacji. Po wywołaniu tej metody okno zamyka się.
     */

    @FXML
    public void OnCloseButton()
    {
        Stage stage = (Stage) infoAnchorPane.getScene().getWindow();
        stage.close();
    }

}
