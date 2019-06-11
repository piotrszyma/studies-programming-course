package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;

    /**
     *  Klasa służąca do tworzenia wielokątów.
     */
public class PolygonDraw extends Polygon {

    public boolean movable = true;
    public Pane pane;

    private PolygonDraw polygon = this;

    /**
     * Konstruktor dla klasy PolygonDraw.
     * Tworzy on nowy obiekt typu wielokąt. Po wywołaniu konstruktora program oczekuje
     * na podanie przez użytkownika punktów, w których znajdować się będą wierzchołki
     * nowego wielokąta. Konstruktor zmienia kursor myszy na krzyżowy, aby użytkownik
     * mógł rozróżnić tryb podglądu figur od trybu tworzenia nowego wielokąta. W trybie
     * edycji użytkownik wybiera punkty, w których mają pojawić się wierzchołki poprzez
     * naciśnięcie na przestrzeń roboczą lewym przyciskiem myszy.Użytkownik sygnalizuje
     * programowi wyjście z trybu edycji poprzez naciśnięcie prawego przycisku myszy.
     *
     * @param workspace Parametr konstruktora, layout na którym ma pojawić się nowy obiekt.
     */

    PolygonDraw(Pane workspace)
    {
        workspace.setCursor(Cursor.CROSSHAIR);
        workspace.getChildren().add(this);
        this.pane = workspace;

        if(this.movable) {

            workspace.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if(polygon.movable) {

                        if(event.getButton() == MouseButton.PRIMARY)
                        {
                            polygon.setFill(javafx.scene.paint.Paint.valueOf(Settings.DEFAULT_FIGURES_COLOR));
                            polygon.getPoints().addAll(
                                    event.getX(),  event.getY() );
                        } else if(event.getButton() == MouseButton.SECONDARY)
                        {
                            polygon.movable = false;
                            workspace.setCursor(Cursor.DEFAULT);
                        }
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
                        ShowSettings();
                    });

                    item2.setOnAction(e -> {

                        pane.getChildren().remove(polygon);
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
        layout.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        layout.setHgap(10);
        layout.setVgap(10);

        javafx.scene.control.Label label3 = new javafx.scene.control.Label("Skala:");
        javafx.scene.control.Label label4 = new javafx.scene.control.Label("Kolor:");

        javafx.scene.control.TextField text3 = new javafx.scene.control.TextField();
        ComboBox combo = new ComboBox();

        text3.setText(Double.toString(polygon.getScaleX()));
        combo.setItems(options);
        combo.setValue(Settings.colorToString(polygon.getFill()));

        javafx.scene.control.Button closeButton = new javafx.scene.control.Button("OK");
        closeButton.setOnAction(e ->
        {
            if(Settings.isDouble(text3.getText()))
            {
                if(Double.compare(Double.parseDouble(text3.getText()), 0.0) <= 0)
                {
                    AlertBox.display("Błąd!", "Podaj liczby większe od zera!");
                } else
                {
                    polygon.setFill(javafx.scene.paint.Paint.valueOf(combo.getValue().toString()));
                    polygon.setScaleX(Double.parseDouble(text3.getText()));
                    polygon.setScaleY(Double.parseDouble(text3.getText()));
                    window.close();
                }
            } else
            {
                AlertBox.display("Błąd!", "Podaj liczby!");
            }

        });

        GridPane.setConstraints(label3, 1, 3);
        GridPane.setConstraints(label4, 1, 4);

        GridPane.setConstraints(text3, 2, 3);
        GridPane.setConstraints(combo, 2, 4);

        layout.setConstraints(closeButton, 2, 5);

        layout.getChildren().addAll(label3, label4, closeButton, text3, combo);

        Scene scene1 = new Scene(layout);
        window.setScene(scene1);
        window.showAndWait();
    }

}
