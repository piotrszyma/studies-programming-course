package com.company;

/**
 * Created by thomp on 18.03.2016.
 */
class Okrag extends Figura {

    double promien;



    Okrag(double promien)
    {
        this.promien = promien;
        this.nazwa = "Okrag";
        Pole();
        Obwod();
    }

    void Pole()
    {
        this.Pole = promien*promien*Math.PI;
    }

    void Obwod()
    {
        this.Obwod = 2 * 3.14 * promien;
    }

}
