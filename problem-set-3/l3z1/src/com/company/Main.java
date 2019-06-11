package com.company;

public class Main {

    public static void main(String[] args) {

        for(String s: args)
        {
            if(isFloat(s))
            {
                System.out.println(s + " >>> Liczba zmiennoprzecinkowa!");
            }
            else if(isInteger(s)) {
                try {
                    System.out.println(s + " >>> " + RzymArab.arab2rzym(Integer.parseInt(s)));
                } catch (RzymArabException e) {
                    System.out.println(s + " >>> Liczba arabska podana w argumencie funkcji jest spoza zakresu 1 - 3999!");
                }
            } else
            {
                try {
                    System.out.println(s + " >>> " + RzymArab.rzym2arab(s));
                } catch (RzymArabException e) {
                    System.out.println(s + " >>> Podany ciąg znaków nie jest liczbą rzymską!");
                }
            }
        }


    }

    public static boolean isInteger(String str) {

        return str.matches("^-?\\d+$");

    }

    public static boolean isFloat(String str) {

        return str.matches("^-?\\d+.+\\d$");

    }

}
