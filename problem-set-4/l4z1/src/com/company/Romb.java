package com.company;

/**
 * Created by thomp on 18.03.2016.
 */
class Romb extends Czworokat {

    Romb(double bok1, double bok2, double bok3, double bok4, double kat)
    {
        this.bok1 = bok1;
        this.bok2 = bok2;
        this.bok3 = bok3;
        this.bok4 = bok4;
        this.kat = kat;
        this.nazwa = "Romb";


        Pole();
        Obwod();

    }

    void Pole()
    {
        this.Pole = bok1*bok1*Math.sin(Math.toRadians(kat));
    }

    void Obwod()
    {
        this.Obwod = 4 * bok1;
    }



}
