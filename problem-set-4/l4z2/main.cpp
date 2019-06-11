#include <iostream>
#include <cmath>
#include <string>
#include <stdlib.h>

using namespace std;


//Klasy

class Figury {
public:
    virtual void Pole() = 0;
    virtual void Obwod() = 0;
    double pole;
    double obwod;
    string nazwa;
};


//Klasa Okrag dziedziczaca z klasy Figury

class Okrag: public Figury {
private:
    double promien;
public:

    Okrag(double promien)
    {
        this->promien = promien;
        this->nazwa = "Okrag";
        Pole();
        Obwod();
    }

    void Pole()
    {
        this->pole = promien*promien*3.14;
    }
    void Obwod()
    {
        this->obwod = 6.28 * promien;
    }
};

class Czworokat: public Figury {
public:
    double bok1, bok2, bok3, bok4, kat;
};

class Pieciokat: public Figury {
public:
    double bok;
    Pieciokat(double bok)
    {
        this->bok = bok;
        this->nazwa = "Pieciokat";
        Pole();
        Obwod();
    }
    void Pole()
    {
        this->pole = (5.0/4.0) * bok * bok * (1.0/tan(0.628));
    }
    void Obwod()
    {
        this->obwod = 5 * bok;
    }
};

class Romb : public Czworokat {
public:
    Romb(double bok1, double bok2, double bok3, double bok4, double kat)
    {
        this->bok1 = bok1;
        this->bok2 = bok2;
        this->bok3 = bok3;
        this->bok4 = bok4;
        this->kat = kat;
        this->nazwa = "Romb";

        Pole();
        Obwod();
    }

    void Pole()
    {
        this->pole = bok1 * bok1 * sin(kat*0.01745);
    }

    void Obwod()
    {
        this->obwod = 4 * bok1;
    }

};


class Kwadrat: public Czworokat {
public:
    Kwadrat(double bok1, double bok2, double bok3, double bok4, double kat)
    {
        this->bok1 = bok1;
        this->bok1 = bok1;
        this->bok2 = bok2;
        this->bok3 = bok3;
        this->bok4 = bok4;
        this->kat = kat;
        this->nazwa = "Kwadrat";

        Pole();
        Obwod();
    }

    void Pole()
    {
        this->pole = bok1 * bok1;
    }

    void Obwod()
    {
        this->obwod = 4 * bok1;
    }

};

class Prostokat: public Czworokat {
public:

    Prostokat(double bok1, double bok2, double bok3, double bok4, double kat)
    {


        this->kat = kat;
        this->nazwa = "Prostokat";

        double tmp;

        double* table = new double[4];

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

        this->bok1 = table[0];
        this->bok2 = table[1];
        this->bok3 = table[2];
        this->bok4 = table[3];

        delete [] table;

        Pole();
        Obwod();

    }

    void Pole()
    {
        this->pole = bok1 * bok3;
    }

    void Obwod()
    {
        this->obwod = bok1 + bok2 + bok3 + bok4;
    }


};


bool firstArgCheck(string test)
{
    for(int i=0; i<test.length(); i++)
    {
        if(test[i]!='c'&&test[i]!='p'&&test[i]!='o') {
            return false;
        }
    }
    return true;
}


bool isInteger(char* n)
{
        int iter = 0;

        if(n[0]=='-')
        {
            n++;
        }

        if(n[0]=='\0') return false;

        while(n[iter])
        {
            if(!isdigit((int)n[iter++]))
            {
                return false;
            }
        }
        return true;
}

bool isFloat(char* n)
{
    int iter = 0;
    int pointer = 0;
        if(n[0]=='-')
        {
            n++;
        }

        if(n[0]=='\0') return false;

        while(n[iter])
        {
            if(n[iter]=='.')
            {
               pointer++;
            }
            else if(!isdigit((int)n[iter]))
            {
                return false;
            }
            iter++;
            if(pointer>1) return false;
        }
        if(pointer==1) return true;
        else return false;

}





int main(int argc, char* argv[])
{
    if(argc==1)
    {
        cout<<"Brak argumentow!"<<endl;
        return 0;
    }
    if(!firstArgCheck(argv[1])) //Sprawdzenie czy pierwszy argument jest poprawny - zewnetrzna funkcja
    {
        cout<<"Bledny pierwszy argument!"<<endl;
        return 0;
    }


    for(int i=2; i<argc; i++)
    {
        if((!isInteger(argv[i]))&&(!isFloat(argv[i])))
        {
            cout<<"Bledne parametry - Drugi i kazdy kolejny parametr musi byc liczba!"<<endl;
            return 0;
        }
    }



    int argsAm = argc-2;
    string ins = argv[1];

    for(int i=0; i<ins.length(); i++) //Sprawdzenie czy ilosc arugmentow sie zgadza
    {
        if(ins[i]=='p'||ins[i]=='o')
        {
            argsAm--;
        } else if(ins[i]=='c')
        {
            argsAm-=5;
        }
    }

    if(argsAm<0)
        {
            cout<<"Blad - Za malo parametrow."<<endl;
            return 0;
        } else if(argsAm>0)
        {
            cout<<"Blad - Za duzo parametrow!"<<endl;
            return 0;
        }

    int NumOfFigs = ins.length();

    int Pointer = 2;

//*************************************************************************

    for(int i=0; i<NumOfFigs; i++)
        {
            if(ins[i]=='o')
            {
                //okr�g
                if(atof(argv[Pointer])<=0.0)
                {
                    cout<<"Bledne parametry! Okrag o promieniu mniejszym badz rownym zero!"<<endl;
                    return 0;
                }
                Pointer++;

            } else if(ins[i]=='c')
            {
                if(atof(argv[Pointer+4])!=90.0) {

                    if(atof(argv[Pointer])<0.0||atof(argv[Pointer+1])<0.0||atof(argv[Pointer+2])<0.0||atof(argv[Pointer+3])<0.0)
                    {
                        cout<<"Bledne parametry! Bok rombu musi byc wiekszy od zera!"<<endl;
                        return 0;
                    }
                    else if(atof(argv[Pointer+4])<=0.0||atof(argv[Pointer+4])>=180.0)
                    {
                        cout<<"Bledne parametry! Kat w rombie to liczba X z zakresu X > 0 i X < 180"<<endl;
                        return 0;
                    }
                    else if(atof(argv[Pointer])!=atof(argv[Pointer+1])||atof(argv[Pointer])!=atof(argv[Pointer+2])||atof(argv[Pointer])!=atof(argv[Pointer+3]))
                    {
                        cout<<"Bledne parametry! Romb musi miec wszystkie boki rowne!"<<endl;
                        return 0;
                    }

                } else if(atof(argv[Pointer])==atof(argv[Pointer+1])&&atof(argv[Pointer])==atof(argv[Pointer+2])&&atof(argv[Pointer])==atof(argv[Pointer+3]))
                {
                    //Kwadrat
                    //Nic nie r�b
                    continue;

                } else
                {
                    double* table = new double [4];

                    for(int j=0; j<4; j++) {
                        table[j] = atof(argv[Pointer+j]);
                    }

                    double tmp;

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

                    if(table[0]!=table[1]||table[2]!=table[3])
                    {
                        cout<<"Bledne parametry! Prostokat powinien miec dwie pary bokow rownych!"<<endl;
                        return 0;
                    }

                }
                Pointer+=5;
            } else if(ins[i]=='p')
            {
                if(atof(argv[Pointer])<=0.0)
                {
                    cout<<"Bledne parametry! Bok w pieciok�cie musi byc wiekszy od zera!"<<endl;
                    return 0;
                }
                Pointer+=1;
            }
        }

        //cout<<"Przeszlo"<<endl;

//*************************************************************************

    Pointer = 2;

    Figury** Table = new Figury*[NumOfFigs];


    for(int i=0; i<NumOfFigs; i++)
        {
            if(ins[i]=='o')
            {
                Table[i] = new Okrag(atof(argv[Pointer]));
                Pointer++;

            } else if(ins[i]=='c')
            {
                if(atof(argv[Pointer+4])!=90.0) {

                    Table[i] = new Romb(atof(argv[Pointer]), atof(argv[Pointer+1]), atof(argv[Pointer + 2]), atof(argv[Pointer+3]), atof(argv[Pointer+4]));


                } else if(atof(argv[Pointer])==atof(argv[Pointer+1])&&atof(argv[Pointer])==atof(argv[Pointer+2])&&atof(argv[Pointer])==atof(argv[Pointer+3]))
                {
                    Table[i] = new Kwadrat(atof(argv[Pointer]), atof(argv[Pointer+1]), atof(argv[Pointer + 2]), atof(argv[Pointer+3]), atof(argv[Pointer+4]));

                } else
                {
                    Table[i] = new Prostokat( atof(argv[Pointer]), atof(argv[Pointer+1]), atof(argv[Pointer + 2]), atof(argv[Pointer+3]), atof(argv[Pointer+4]));

                }
                Pointer+=5;
            } else if(ins[i]=='p')
K            {
                Table[i] = new Pieciokat(atof(argv[Pointer]));
                Pointer+=1;
            }
        }

//*************************************************************************


    for(int i=0; i<NumOfFigs; i++)
        {
             cout<<Table[i]->nazwa<<" - Pole: "<<Table[i]->pole<<" Obwod: "<<Table[i]->obwod<<endl;
        }


    return 0;
}
