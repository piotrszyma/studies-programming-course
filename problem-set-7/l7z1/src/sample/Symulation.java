package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa stworząca okno symulacji oraz generująca poszczególne wątki.
 */

public class Symulation {

    private Random rand = new Random();

    private Stage window = new Stage();

    private int grid_x, grid_y;
    private double prob;
    private int time;
    public static boolean running;

    /**
     * Konstruktor klasy symulacji.
     * @param columns Liczba kolumn.
     * @param rows Liczba wierszy.
     * @param prob Prawdopodobieństwo związane ze zmianą koloru.
     * @param time Czas oczekiwania poszczególnych procesów.
     * @throws SymulationExceptions Wyjątek symulacji
     */

    Symulation(int columns, int rows, double prob, int time) throws SymulationExceptions
    {
        running = true;
        System.out.println("generating");
        if(columns<0||rows<0)
        {
            throw new SymulationExceptions("Liczba kolumn i rzędów musi być dodatnia!");

        } else if(prob<0.0 || prob>1.0)
        {
            throw new SymulationExceptions("Prawdopodobieństwo musi być liczbą z zakresu od zer do jedynki");
        } else if(time<=0)
        {
            throw new SymulationExceptions("Czas musi być większy od zera!");
        } else if(columns*rows>150*150)
        {
            throw new SymulationExceptions("Zmniejsz ilość kolumn/wierszy! (Program nie pozwoli na wygenerowanie ilości wątków większej niż 22500)");
        } else if(columns*rows>120*120&&time<1000)
        {
            throw new SymulationExceptions("Dla tak dużej ilości wątków minimalny czas to 1 sekunda (1000 ms)");
        }

        if(columns*rows>10000)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uwaga");
            alert.setHeaderText(null);
            alert.setContentText("Wygenerowanie tak dużej ilości procesów może powodować znaczące spowolnienie pracy " +
                    "programu jak i samego komputera!");

            alert.showAndWait();

        }

        this.prob = prob;
        this.time = time;
        this.grid_x = columns;
        this.grid_y = rows;


        try{
            start(window);
        } catch(Exception e)
        {
            System.out.println("Nie udało się wygenerować symulacji.");
        }

    }


    /**
     * Metoda start() tworzy okno zawierające tabelę z poszczególnymi wątkami w postaci kolorowych prostokątów.
     * @param stage Parametr ten to scena, na której ma pojawić się okno.
     * @throws Exception Wyjątek powstały podczas generowania okna
     */

    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        window.initModality(Modality.APPLICATION_MODAL);

        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        if(window.fullScreenProperty().getValue())
                        {
                            window.setFullScreen(false);
                        } else
                        {
                            window.setFullScreen(true);
                        }

                    }
                }
            }
        });

        Rectangle[][] rectangleArray = new Rectangle[grid_x][grid_y];

        for (int x = 0; x < grid_x; x++) {

            for (int y = 0; y < grid_y; y++) {

                sample.Rectangle rectangle = new sample.Rectangle(x, y, prob, time);
                rectangle.fillProperty().setValue(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                rectangle.xProperty().bind(root.widthProperty().multiply(x).divide(grid_x));
                rectangle.yProperty().bind(root.heightProperty().multiply(y).divide(grid_y));
                rectangle.widthProperty().bind(root.widthProperty().divide(grid_x).add(1));
                rectangle.heightProperty().bind(root.heightProperty().divide(grid_y).add(1));
                root.getChildren().add(rectangle);
                rectangleArray[x][y] = rectangle;


            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(grid_x*grid_y);

        for (int x = 0; x < grid_x; x++) {

            for (int y = 0; y < grid_y; y++) {

                rectangleArray[x][y].setLeft(rectangleArray[(x-1+grid_x)%grid_x][y]);
                rectangleArray[x][y].setRight(rectangleArray[(x+1+grid_x)%grid_x][y]);
                rectangleArray[x][y].setDown(rectangleArray[x][(y-1+grid_y)%grid_y]);
                rectangleArray[x][y].setUp(rectangleArray[x][(y+1+grid_y)%grid_y]);
            }
        }

        if(grid_x>grid_y)
        {
            stage.setScene(new Scene(root, 800, (800/grid_x)*grid_y));
        } else
        {
            stage.setScene(new Scene(root, ((800/grid_y)*grid_x-37), 800));
        }

        for(int x = 0; x < grid_x; x++)
        {
            for(int y = 0; y < grid_y; y++)
            {
                executor.execute(rectangleArray[x][y]);
            }
        }


        stage.getIcons().add(new Image("sample/icon.png"));
        stage.setTitle("Symulacja");
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("shutting down threads");
                running = false;

                executor.shutdown();

                if(executor.isShutdown()){
                    System.out.println("shut down");
                }

            }
        });






    }




}
