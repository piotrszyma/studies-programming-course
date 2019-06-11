package sample;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import java.util.Random;

/**
 * Klasa prostokąta rozszerzająca klasę prostokąta javafx implementuje interfejs Runnable
 * Służy jednocześnie do stworzenia prostokątu jak i do stworzenia wątku.
 */

public class Rectangle extends javafx.scene.shape.Rectangle implements Runnable{

    private Rectangle left, right, up, down;
    private int x, y;


    /**
     * Konstruktor klasy Rectangle
     * @param x Pozycja x-owa tworzonego prostokąta w tablicy prostokątów
     * @param y Pozycja y-owa tworzonego prostokąta w tablicy prostokątów
     * @param prob Parametr określający prawdopodobieństwo wystąpienia danego zachowania wyjątku (zmiana koloru losowa/względem sąsiadów)
     * @param time Czas zwłoki danego wątku.
     */
    Rectangle(int x, int y, double prob, int time) {
        this.x = x;
        this.y = y;
        this.prob = prob;
        this.time = time;
    }


    private final double prob;
    private final int time;
    private double aver_red, aver_green, aver_blue;

    static Random randomGenerator = new Random();


    /**
     * Metoda z interfejsu Runnable, metoda wywoływana po uruchomieniu wątku
     */

    @Override
    public void run() {

        setRandomColor();
        randomWaitTime();


        while(Symulation.running)
        {

            if (prob - randomGenerator.nextDouble() > 0) {
                setRandomColor();
            } else {
                setNeighboursColor();
            }

            randomWaitTime();

        }

    }

    /**
     * Metoda ustala losowy kolor dla prostokąta
     */

    private void setRandomColor()
    {
        Platform.runLater(() ->
        {
            this.fillProperty().setValue(Color.rgb(generateColor(), generateColor(), generateColor()));
        });

    }

    /**
     * Metoda wstrzymuje proces na określony czas
     */

    private void randomWaitTime() {
        try {
            Thread.sleep(Math.round((randomGenerator.nextDouble() + 0.5) * time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda ustala kolor dla prostokąta uśredniony z kolorów sąsiadów
     */

    private void setNeighboursColor()
    {

        aver_red = (((Color)(this.left.fillProperty().getValue())).getRed() +
                ((Color)(this.right.fillProperty().getValue())).getRed() +
                ((Color)(this.up.fillProperty().getValue())).getRed() +
                ((Color)(this.down.fillProperty().getValue())).getRed())/4.0;


        aver_green =  (((Color)(this.left.fillProperty().getValue())).getGreen() +
                ((Color)(this.right.fillProperty().getValue())).getGreen() +
                ((Color)(this.up.fillProperty().getValue())).getGreen() +
                ((Color)(this.down.fillProperty().getValue())).getGreen())/4.0;


        aver_blue =  (((Color)(this.left.fillProperty().getValue())).getBlue() +
                ((Color)(this.right.fillProperty().getValue())).getBlue() +
                ((Color)(this.up.fillProperty().getValue())).getBlue() +
                ((Color)(this.down.fillProperty().getValue())).getBlue())/4.0;

        Platform.runLater(() ->
        {
            this.fillProperty().setValue(Color.rgb((int)(aver_red*255), (int)(aver_green*255), (int)(aver_blue*255)));
        });

    }

    /**
     * Metoda generuje liczby pseudolosowe z zakresu 0 - 255
     * @return Zwraca pseudolosową liczbę typu int z zakresu 0-255
     */

    private static int generateColor() {
        int newColor = randomGenerator.nextInt(256);
        return newColor;
    }



    //************************************************************************************************************

    /**
     * Setter dla zmiennej left obiektu typu Rectangle
     * @param left Prostokąt znajdujący się na lewo od tego prostokąta
     */
    public void setLeft(Rectangle left) {
        this.left = left;
    }

    /**
     * Setter dla zmiennej right obiektu typu Rectangle
     * @param right Prostokąt znajdujący się na prawo od tego prostokąta
     */
    public void setRight(Rectangle right) {
        this.right = right;
    }

    /**
     * Setter dla zmiennej up obiektu typu Rectangle
     * @param up Prostokąt znajdujący się nad tym prostokątem
     */
    public void setUp(Rectangle up) {
        this.up = up;
    }

    /**
     * Setter dla zmiennej down obiektu typu Rectangle
     * @param down Prostokąt znajdujący się pod tym prostokątem
     */
    public void setDown(Rectangle down) {
        this.down = down;
    }

}
