package sample;

/**
 * Klasa wyjątków symulacji
 */


public class SymulationExceptions extends Exception {

    /**
     * Konstruktor wyjątków
     * @param msg Tekst komunikatu błędu wyświetlającego sie po pojawieniu się wyjątku.
     */
    SymulationExceptions(String msg)
    {
        super(msg);
    }
}
