package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasa przechowywująca domyślne wartości takie jak:
 * - domyślna średnica koła
 * - domyślna wysokość prostokąta
 * - domyślna szerokość prostokąta
 * - domyślny kolor figury
 *
 * Dodatkowo zawiera pomocniczą metodę sprawdzającą czy dany String jest liczbą zmiennoprzecinkową.
 */

public class Settings {
    public static double DEFAULT_CIRCLE_RADIUS = 50.0;
    public static double DEFAULT_RECTANGLE_WIDTH = 150.0;
    public static double DEFAULT_RECTANGLE_HEIGHT = 100.0;
    public static String DEFAULT_FIGURES_COLOR = "black";

    /**
     * Metoda isDouble() sprawdza, czy podany string jest liczbą zmiennoprzecinkową.
     * @param str String do sprawdzenia
     * @return Funkcja zwraca wartość true jeśli parametr jest liczbą zmiennoprzecinkową.
     * @return Funkcja zwraca wartość false jeśli parametr nie jest liczbą zmiennoprzecinkową.
     */

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Pomocniczna metoda interpretująca kolory występujące w programie
     * z ich oznaczeń w palecie RGB w systemie szesnastkowym na ich
     * odpowiedniki w postaci Stringów.
     *
     * @param color Jako parametr użytkownik podaje kolor typu Paint
     * @return Funkcja zwraca kolor podany jako parametr w postaci Stringa z nazwą w języku angielskim.
     */

    public static String colorToString(Paint color)
    {
        if(color.toString().equals("#000000ff")) {
            return "black";
        }
        else if(color.toString().equals("0x808080ff")) {
            return "grey";
        }else if(color.toString().equals("0x0000ffff")) {
            return "blue";
        }else if(color.toString().equals("0xff0000ff")) {
            return "red";
        }else if(color.toString().equals("0xffc0cbff")) {
            return "pink";
        }else if(color.toString().equals("0xa52a2aff")) {
            return "brown";
        }else if(color.toString().equals("0xffa500ff")) {
            return "orange";
        }else
        {
            return "black";
        }
    }
}
