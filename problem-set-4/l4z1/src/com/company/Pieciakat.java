package com.company;

/**
 * Created by thomp on 18.03.2016.
 */
public class Pieciakat extends Figura {

    double bok;

    Pieciakat(double bok)
    {
        this.bok = bok;
        this.nazwa = "Pięciokąt";
        Pole();
        Obwod();
    }

    public void Pole()
    {
        this.Pole = (5.0/4.0) * bok * bok * (1.0/Math.tan(Math.toRadians(36)));
    }

    public void Obwod()
    {
        this.Obwod = 5 * bok;
    }




}
