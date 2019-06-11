package com.company;

/**
 * Created by thomp on 18.03.2016.
 */
public class Prostakat extends Czworokat {

    Prostakat(double bok1, double bok2, double bok3, double bok4, double kat)
    {

        this.kat = kat;
        this.nazwa = "Prostokąt";

        double tmp;

        double[] table = new double[4];

        table[0] = bok1;
        table[1] = bok2;
        table[2] = bok3;
        table[3] = bok4;

        int n = 4;
        do {

            for(int k = 0; k < n - 1; k++)
            {
                if(table[k] > table[k+1])
                {
                    tmp = table[k];
                    table[k] = table[k+1];
                    table[k+1] = tmp;
                }
            }
            n--;

        } while(n>1);

        this.bok1 = table[0];
        this.bok2 = table[1];
        this.bok3 = table[2];
        this.bok4 = table[3];

        Pole();
        Obwod();

    }

    public void Pole() { this.Pole = bok1 * bok3; }

    public void Obwod()
    {
        this.Obwod = bok1 + bok2 + bok3 + bok4;
    }

}
