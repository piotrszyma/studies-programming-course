package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa kontrolera z głównym oknem programu
 */


public class Controller implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private TextField commandLine;

    private DataOutputStream toServer;
    private BufferedReader fromServer;

    @FXML
    private Pane pane;


    /**
     * Metoda służąca do przesłania z głównej klasy do klasy kontrolera sceny.
     * @param stage Scena, na której tworzone jest główne okno programu
     */

    public void setStage(Stage stage)
    {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onCloseButton();
            }
        });
    }

    /**
     * Metoda wywołwana podczas inicjalizacji. W metodzie do poszczególnych elementów dodawane są odpowiednie actionListenery.
     */

    public void addListeners()
    {
        textArea.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                textArea.setScrollTop(1.0);
            }
        });

        commandLine.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(newValue.matches(".*?[ęółąśżźćń].*"))
                {
                    if(newValue.substring(newValue.length()-1).equals("ę")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "e");
                    else if(newValue.substring(newValue.length()-1).equals("ó")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "o");
                    else if(newValue.substring(newValue.length()-1).equals("ł")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "l");
                    else if(newValue.substring(newValue.length()-1).equals("ą")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "a");
                    else if(newValue.substring(newValue.length()-1).equals("ś")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "s");
                    else if(newValue.substring(newValue.length()-1).equals("ż")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "z");
                    else if(newValue.substring(newValue.length()-1).equals("ź")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "z");
                    else if(newValue.substring(newValue.length()-1).equals("ć")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "c");
                    else if(newValue.substring(newValue.length()-1).equals("ń")) commandLine.setText(newValue.substring(0, newValue.length()-1) + "n");
                } else if(newValue.matches(".*?[A-Z0-9].*"))
                {
                    commandLine.setText(newValue.toLowerCase());
                } else if(newValue.matches(".*?[^a-z\\s].*"))
                {
                    commandLine.setText(oldValue);
                }
            }

        });

        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
               {
                   try {
                       onSendButton();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
            }
        });
    }


    /**
     * Metoda wywołująca się podczas inicjalizacji kontrolera
     * @param location Lokalizacja
     * @param resources Źródła
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addListeners();

        try {
            Socket socket = new Socket("localhost", 1234);
            toServer = new DataOutputStream(socket.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Podłączono do serwera");
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setPrefSize(500, 150);
            alert.setHeaderText(null);
            alert.setContentText("Problem! Nie udało się podłączyć do serwera.");
            alert.showAndWait();
            Platform.exit();
        }



    }

    /**
     * Metoda wywołująca się, gdy użytkownik naciśnie "wyślij"
     * @throws IOException Wyjątki pojawiające się podczas przesyłania danych do serwera
     */

    @FXML
    public void onSendButton() throws IOException {

        if (!commandLine.getText().isEmpty()) {


            toServer.writeBytes(commandLine.getText() + '\n');
            textArea.appendText("<klient> " + commandLine.getText() + '\n');
            commandLine.clear();
            new Thread(task).run();


        }
    }



    private Runnable task = () -> {
        try {
            String input;
            while((input = fromServer.readLine()) != null)
            {
                if(input.equals("---END---")) break;
                textArea.appendText("<serwer> " + input + '\n');
            }

        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setPrefSize(500, 150);
            alert.setContentText("Połączenie z serwerem zostało przerwane. Zamykanie klienta.");
            alert.showAndWait();
            Platform.exit();
        }

        Thread.currentThread().interrupt();
    };

    /**
     * Metoda wywołująca się, gdy uzytkownik naciśnie na przycisk "pomoc" w menu górnym.
     * Pojawia się alertbox z informacjami nt. komend dostępnych w programie
     */

    @FXML
    public void onHelpButton()
    {


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.getDialogPane().setPrefSize(500, 230);
        alert.setGraphic(null);
        alert.setContentText("Dostępne komendy: " +
                "\n- new <typ> <nazwa> : tworzy drzewo typu <typ> o nazwie <nazwa>" +
                "\n- <nazwa> add <obiekt> : dodaje do drzewa o nazwie <nazwa> danę <obiekt>" +
                "\n- <nazwa> remove <obiekt> : usuwa z drzewa o nazwie <nazwa> danę <obiekt>" +
                "\n- <nazwa> find <obiekt> : szuka w drzewie o nazwie <nazwa> danej <obiekt>" +
                "\n- <nazwa> inorder : wyświetla drzewo <nazwa> w porządku inorder" +
                "\n- <nazwa> preorder : wyświetla drzewo <nazwa> w porządku preorder" +
                "\n- <nazwa> postorder : wyświetla drzewo <nazwa> w porzadku postorder");
        alert.showAndWait();
    }

    /**
     * Metoda wywołująca się, gdy użytkownik naciśnie na przycisk "zamknij" w menu głównym.
     * Pojawia się alertbox informujący o tym, że program zostanie zamknięty. Po naciśnięciu OK
     * program zamyka się.
     */
    @FXML
    public void onCloseButton()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.getDialogPane().setPrefSize(500, 150);
        alert.setContentText("Program zostanie zamknięty.");
        alert.showAndWait();
        try {
            toServer.writeBytes("---DONE---");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }

    /**
     * Metoda wywołująca się, gdy użytkownik naciśnie na przycisk "informacje" w menu głównym.
     * Pojawia się alertbox informujący o tym, o czym jest program oraz kto jest jego autorem.
     */

    @FXML
    public void onInfoButton()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setPrefSize(500, 150);
        alert.setHeaderText(null);
        alert.setContentText("Program służący do komunikacji między klientem, a serwerem w celu obsługi drzewa binarnego BST." +
                "\nAutor: Piotr Szyma" +
                "\nWersja: 1.0");

        alert.showAndWait();
    }


}

