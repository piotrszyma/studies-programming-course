package company;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Platform;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Klasa serwera. Serwer służy do obsługi jednego klienta. Kiedy klient nie jest podłączony,
 * nasłuchuje on port o numerze 1234.
 */
public class Server {

    private boolean running = false;

    /**
     * Konstruktor klasy serwer. W nim wywołanie metody run uruchamiającej serwer.
     * @throws IOException
     */

    public Server() throws IOException {

        run();
    }

    /**
     * Metoda run uruchamiająca serwer.
     * @throws IOException Wyrzuca wyjątki pojawiające się podczas uruchamiania serwera
     */

    ServerSocket serverSocket;
    Socket connection;
    BufferedReader fromClient;
    DataOutputStream toClient;
    PrintStream old;

    public void run() throws IOException
    {

        old = System.out;
        running = true;

        while(running)
        {
            System.out.println("Rozpoczynam nasłuch...");


            serverSocket = new ServerSocket(1234);

            connection = serverSocket.accept();


            fromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            toClient = new DataOutputStream(connection.getOutputStream());
            System.out.println("Ustanowiono połączenie!");

            System.setOut(new PrintStream(toClient));

            Terminal terminal = new Terminal();
            String input;
            try{
                while((input = fromClient.readLine()) != null)
                {
                    terminal.line(input);
                    System.out.println("---END---");
                }
            } catch(SocketException e)
            {
                fromClient.close();
                toClient.close();
                System.setOut(old);
                System.out.println("Utracono połączenie...");
            }

            serverSocket.close();
            System.out.println("Zamykam połączenie...");


        }




    }

    /**
     * Metoda stop służy do zatrzymania pracy serwera.
     */

    public void stop()
    {
        running = false;
    }
}
