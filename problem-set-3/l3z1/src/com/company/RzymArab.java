package com.company;

/**
 * Created by thomp on 05.03.2016.
 */
public class RzymArab {
    private static String[] liczby = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    private static int[] liczbyArab = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public static int rzym2arab (String rzym) throws RzymArabException {

        String tmpRzym = rzym;

        int arabfromrzym = 0;

        int iter = 12;

        while(iter>=0)
        {
                while(rzym.startsWith(liczby[iter]))
                {

                    rzym = rzym.substring(liczby[iter].length());
                    arabfromrzym+=liczbyArab[iter];

                }
           iter--;
        }

        if(!tmpRzym.equals(arab2rzym(arabfromrzym)))

        {
            throw new RzymArabException();
        }


        return arabfromrzym;
    }

    public static String arab2rzym (int arab) throws RzymArabException {


        if(arab<0||arab>3999)
        {
            throw new RzymArabException("Liczba spoza zakresu");
        }
        String rzymfromarab = "";
        int iter = 12;
        while(arab>0)
        {
            while(arab-liczbyArab[iter]>=0)
            {
                rzymfromarab+=liczby[iter];
                arab-=liczbyArab[iter];
            }
            iter--;
        }



        return rzymfromarab;
    }
}
