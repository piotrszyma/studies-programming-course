package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

    /**
     *  Klasa służąca do tworzenia prostokątów.
     */

public class RectangleDraw extends javafx.scene.shape.Rectangle {

    public boolean movable = true;
    public Pane pane;

    private RectangleDraw rectangle = this;

    /**
     * Tworzy on nowy obiekt typu prostokąt. Po wywołaniu konstruktora program oczekuje
     * na podanie przez użytkownika punktu, w którym znajdować się będzie środek generowanego
     * prostokąta. Konstruktor zmienia kursor myszy na krzyżowy, aby użytkownik mógł rozróżnić
     * tryb podglądu figur od trybu tworzenia nowego koła. W trybie edycji użytkownik wybiera
     * punkt, w którym ma znajdować się środek prostokąta poprzez naciśnięcie na przestrzeń roboczą
     * lewym przyciskiem myszy. Użytkownik sygnalizuje programowi wyjcie z trybu edycji
     * poprzez naciśnięcie prawego przycisku myszy.
     *
     * @param workspace Parametr konstruktora, layout na którym ma pojawić się nowy obiekt.
     */

    RectangleDraw(Pane workspace)
    {
        workspace.setCursor(Cursor.CROSSHAIR);
        workspace.getChildren().add(this);
        this.pane = workspace;

        if(this.movable) {

            workspace.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (rectangle.movable) {
                        rectangle.setX(event.getX());
                        rectangle.setY(event.getY());
                        rectangle.setWidth(Settings.DEFAULT_RECTANGLE_WIDTH);
                        rectangle.setHeight(Settings.DEFAULT_RECTANGLE_HEIGHT);
                        rectangle.setFill(javafx.scene.paint.Paint.valueOf(Settings.DEFAULT_FIGURES_COLOR));
                        rectangle.movable = false;
                        workspace.setCursor(Cursor.DEFAULT);
                    }
                }
            });

        }


        this.setOnMouseEntered(EventHandlers.figureOnMouseEntered);
        this.setOnMouseExited(EventHandlers.figureOnMouseExited);
        this.setOnScroll(EventHandlers.figureOnScrolling);
        this.setOnMousePressed(EventHandlers.figureOnMousePressed);
        this.setOnMouseDragged(EventHandlers.figureOnMouseDragged);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getButton() == MouseButton.SECONDARY)
                {
                    ((Shape)event.getSource()).setDisable(true);
                    ContextMenu context = new ContextMenu();

                    MenuItem item1 = new MenuItem("Opcje obiektu");
                    MenuItem item2 = new MenuItem("Usuń obiekt");

                    item1.setOnAction(e -> {
                        rectangle.ShowSettings();
                    });

                    item2.setOnAction(e -> {

                        pane.getChildren().remove(rectangle);
                    });

                    context.getItems().addAll(item1, item2);

                    context.show((Shape)event.getSource(), event.getScreenX(), event.getScreenY());

                    context.setOnHiding(e -> {
                        ((Shape)event.getSource()).setDisable(false);
                    });

                }

            }

        });

        this.setCursor(Cursor.HAND);
    }

    /**
     * Metoda, która wywołuje się po kliknięciu na opcję "Opcje figury" w menu
     * pojawiającym się po kliknięciu PPM na danej figurze. Po wywołaniu metody
     * na ekranie pojawia się menu ustawień figury, w któym użytkownik
     * może - w zależności od tego jaka to figura - zmieniać jej parametry.
     * Parametry to m. in. kolor, wysokość, szerokość, promień, skala.
     * (w zależności od tego jaka to figura)
     */

    public void ShowSettings()
    {
        Stage window = new Stage();

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "black",
                        "grey",
                        "blue",
                        "red",
                        "pink",
                        "brown",
                        "orange"
                );

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ustawienia");
        window.setMinWidth(350);
        window.setMinHeight(150);
        window.setResizable(false);


        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setHgap(10);
        layout.setVgap(10);

        Label label1 = new Label("Wysokość:");
        Label label2 = new Label("Szerokość:");
        Label label3 = new Label("Skala:");
        Label label4 = new Label("Kolor:");



        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        ComboBox combo = new ComboBox();

        text1.setText(Double.toString(rectangle.getWidth()));
        text2.setText(Double.toString(rectangle.getHeight()));
        text3.setText(Double.toString(rectangle.getScaleX()));
        combo.setItems(options);
        combo.setValue(Settings.colorToString(rectangle.getFill()));

        Button closeButton = new Button("OK");
        closeButton.setOnAction(e ->
        {

            if(Settings.isDouble(text1.getText()) && Settings.isDouble(text2.getText()) && Settings.isDouble(text3.getText()) )
            {
                if(  (Double.compare(Double.parseDouble(text1.getText()), 0.0) <= 0) ||
                     (Double.compare(Double.parseDouble(text2.getText()), 0.0) <= 0) ||
                     (Double.compare(Double.parseDouble(text3.getText()), 0.0) <= 0))
                {
                    AlertBox.display("Błąd!", "Podaj liczby większe od zera!");
                } else
                {
                    rectangle.setFill(Paint.valueOf(combo.getValue().toString()));
                    rectangle.setWidth(Double.parseDouble(text1.getText()));
                    rectangle.setHeight(Double.parseDouble(text2.getText()));
                    rectangle.setScaleX(Double.parseDouble(text3.getText()));
                    rectangle.setScaleY(Double.parseDouble(text3.getText()));
                    window.close();
                }
                } else
                {
                    AlertBox.display("Błąd!", "Podaj liczby!");
                }

        });

        GridPane.setConstraints(label1, 1, 1);
        GridPane.setConstraints(label2, 1, 2);
        GridPane.setConstraints(label3, 1, 3);
        GridPane.setConstraints(label4, 1, 4);

        GridPane.setConstraints(text1, 2, 1);
        GridPane.setConstraints(text2, 2, 2);
        GridPane.setConstraints(text3, 2, 3);
        GridPane.setConstraints(combo, 2, 4);

        GridPane.setConstraints(closeButton, 2, 5);

        layout.getChildren().addAll(label1, label2, label3, label4, closeButton, text1, text2, text3, combo);

        Scene scene1 = new Scene(layout);
        window.setScene(scene1);
        window.showAndWait();
    }
}