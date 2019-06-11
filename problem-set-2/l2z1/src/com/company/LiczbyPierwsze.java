package com.company;

import java.util.Vector;

/**
 * Created by thomp on 27.02.2016.
 */
public class LiczbyPierwsze {

    static int[] Array;

    public static int firstAmount;

    public static boolean isInteger(String str) {

        return str.matches("^-?[0-9]+(\\.[0-9]+)?$");

    }

    LiczbyPierwsze(int n)
    {
        Vector<Integer> vectarr = new Vector<>();

        int j = 0;

        for(int i=2; i<=n; i++) {

            if(isFirst(i))
            {
                vectarr.add(i);
                j++;
            }

        }

        firstAmount = j;

        int k = 0;
        Array = new int[vectarr.size()];
        for(int i: vectarr) Array[k++] = i;

    }



    public static boolean isFirst(int n)
    {
        if(n<=1) return false;

        for(int i=2; i*i<=n; i++)
        {
            if(n%i==0) return false;
        }

        return true;
    }

    public static int liczba(int m)
    {
        if(m<0||m>=firstAmount)
        {
            System.out.println(m + " - liczba spoza zakresu");
            return 0;
        } else {
            System.out.println(m + " - " + Array[m]);
            return Array[m];
        }


    }




}