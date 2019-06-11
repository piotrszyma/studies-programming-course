package com.company;

import javafx.application.Platform;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Główna klasa programu
 */

public class Main {


    /**
     * Metoda główna programu
     * @param args Argumenty programu
     */

    public static void main(String[] args) {


        try {
            Server server = new Server();
            server.run();
        } catch (IOException | NullPointerException e) {
            System.out.println("Błąd. Wyłączanie serwera... (" + e.toString() + ")");
        }


    }


}