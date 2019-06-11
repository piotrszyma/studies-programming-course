package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa FXMLSettingsWindowController to okno ustawień programu.
 * W tym oknie użytkownik może modyfikować ustawienia domyślnych
 * rozmiarów figur i ich kolorów.
 */


public class FXMLSettingsWindowController implements Initializable {
    @FXML
    private AnchorPane settingsAnchorPane;
    @FXML
    private ComboBox colorComboBox;
    @FXML
    private TextField radiusTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;

    private ObservableList<String> options =
            FXCollections.observableArrayList(
                    "black",
                    "grey",
                    "blue",
                    "red",
                    "pink",
                    "brown",
                    "orange"
            );

    /**
     * Metoda initialize wywoływana zaraz po uruchomieniu okna.
     * @param location URL - ścieżka do korzenia, NULL jeśli lokalizacja korzenia nieznana.
     * @param resources Zasoby służące do lokalizacji korzenia, NULL jeśli lokalizacja korzenia nie została odnaleziona.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colorComboBox.setItems(options);
        radiusTextField.setText(Double.toString(Settings.DEFAULT_CIRCLE_RADIUS));
        widthTextField.setText(Double.toString(Settings.DEFAULT_RECTANGLE_WIDTH));
        heightTextField.setText(Double.toString(Settings.DEFAULT_RECTANGLE_HEIGHT));
        colorComboBox.setValue(Settings.DEFAULT_FIGURES_COLOR);
    }


    /**
     * Metoda OnOkButton() wywołuje się po naciśnięciu przycisku OK w oknie ustawień.
     * Odczytuje ona z pól w oknie ustawień wartości wpisane przez użytkownika.
     * Metoda sprawdza również, czy wartości podane przez użytkownika są odpowiednie.
     * Wymiary muszą być liczbami zmiennoprzecinkowymi większymi od zera.
     */

    @FXML
    public void OnOkButton() {
        if (!(Settings.isDouble(heightTextField.getText()) && Settings.isDouble(widthTextField.getText()) && Settings.isDouble(radiusTextField.getText()))) {
            AlertBox.display("Błąd!", "Podaj liczby!");
        } else if ( Double.compare(Double.parseDouble(radiusTextField.getText()), 0.0) <= 0 ||
                    Double.compare(Double.parseDouble(heightTextField.getText()), 0.0) <= 0 ||
                    Double.compare(Double.parseDouble(widthTextField.getText()), 0.0) <= 0)
        {
            AlertBox.display("Błąd!", "Podaj wartości większe od zera!");
        } else {

            Settings.DEFAULT_FIGURES_COLOR = colorComboBox.getSelectionModel().getSelectedItem().toString();
            Settings.DEFAULT_RECTANGLE_HEIGHT = Double.parseDouble(heightTextField.getText());
            Settings.DEFAULT_RECTANGLE_WIDTH = Double.parseDouble(widthTextField.getText());
            Settings.DEFAULT_CIRCLE_RADIUS = Double.parseDouble(radiusTextField.getText());

            Stage stage = (Stage) settingsAnchorPane.getScene().getWindow();
            stage.close();


        }
    }

}
