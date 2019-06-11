package com.company;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<String> paramsvec = new Vector<>();

        String allparams = "   1111 2 3 ";
        String temp;
        //System.out.println(allparams.indexOf(32));

        allparams = allparams + " ";
        while(allparams.length()>1)
        {

            if(allparams.charAt(0)==' ')
            {
                allparams = allparams.substring(1);
            } else
            {
                paramsvec.add(allparams.substring(0, allparams.indexOf(32)));
                allparams = allparams.substring(allparams.indexOf(32)+1);
            }

        }

        for(int i=0; i<paramsvec.size(); i++)
        {
            System.out.println(paramsvec.get(i));
        }


        System.out.println("XXXXXXX");




        if(!LiczbyPierwsze.isInteger(args[0]))
        {
            System.out.println(args[0] + " - nieprawidłowa dana");
            return;
        }else if(Integer.parseInt(args[0])<2)
        {
            System.out.println(args[0] + " - Nieprawidłowy zakres");
            return;
        }

        LiczbyPierwsze Array = new LiczbyPierwsze(Integer.parseInt(args[0]));

        for(int i=1; i<args.length; i++)
        {
                if(!LiczbyPierwsze.isInteger(args[i]))
                {
                    System.out.println(args[i] + " - nieprawidlowa dana");
                }
                 else
                {
                   LiczbyPierwsze.liczba(Integer.parseInt(args[i]));
                }

        }
    }

}

