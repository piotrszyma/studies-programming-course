package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Klasa kontrolera głównego okna programu. W oknie tym użytkownik podaje parametry wejścia programu
 * tj. wymiary tabelki z prostokątami, parametr czasu oraz prawdopodobieństwo.
 */

public class Controller {

    @FXML private TextField columnsField;
    @FXML private TextField rowsField;
    @FXML private TextField probField;
    @FXML private TextField timeField;

    /**
     * Funkcja wywołująca się po wpisaniu tekstu do któregokolwiek z pól tekstowych. Kontroluje znaki wpisywane
     * przez użytkownika za pomocą klawiatury. W odpowiednich oknach pozwala na wpisanie jedynie ciągów cyfr.
     */

    @FXML public void onKeyTyped()
    {
        columnsField.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")||!(newValue.length()<=3)) {
                    columnsField.setText(oldValue);
                }
            }
        });

        rowsField.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")||!(newValue.length()<=3)) {
                    rowsField.setText(oldValue);
                }
            }
        });

        timeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")||!(newValue.length()<=5)) {
                    timeField.setText(oldValue);
                }
            }
        });
    }

    /**
     * Funkcja wywołująca się po naciśnięciu przycisku rozpoczynającego symulację.
     * Po wywołaniu program zczytuje dane wpisane w polach tekstowych i uruchamia klasę symulacji
     * z podanymi w polach tekstowych przez użytkownika parametrami.
     */

    @FXML public void onStartButton()
    {
        if(isDouble(probField.getText())) {
            try {
                Symulation process = new Symulation(
                        Integer.parseInt(columnsField.getText()),
                        Integer.parseInt(rowsField.getText()),
                        Double.parseDouble(probField.getText()),
                        Integer.parseInt(timeField.getText()));

            } catch (SymulationExceptions e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Podaj poprawne parametry!");
            alert.showAndWait();
        }
    }

    /**
     * Dodatkowa funkcja sprawdzająca czy dany ciąg znaków jest typu double
     * @param str Ciąg znaków do sprawdzenia.
     * @return Zwraca prawdę, gdy ciąg jest typu double, w innym wypadku fałsz.
     */

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
