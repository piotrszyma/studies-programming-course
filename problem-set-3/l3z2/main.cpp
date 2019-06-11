#include <iostream>
#include <cstring>
#include <string>
#include <exception>
#include <stdlib.h>


using namespace std;


//Klasa wyjatku

class RzymArabException: public exception{
    public:
    RzymArabException() {};
};

//Klasa z funkcjami sprawdzajacymi

class Check
{
public:

static bool isInteger(char* n);

static bool isFloat(char* n);

static bool doesMatch(string pattern, string check);

};


bool Check::isInteger(char* n)
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

bool Check::isFloat(char* n)
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


bool Check::doesMatch(string pattern, string check)
{
    for(int i=0; i<pattern.length(); i++)
    {
        if(check[i]!=pattern[i])
        {
            return false;
        }
    }

    return true;

}




class RzymArab
{

const static string liczby[];
const static int liczbyArab[];

public:

    static int rzym2arab(string rzym)
    {

        string tmpRzym = rzym;

        int arabfromrzym = 0;

        int iter = 12;

        while(iter>=0)
            {

                    while(Check::doesMatch(liczby[iter],rzym))
                    {

                        rzym = rzym.substr(liczby[iter].length());
                        arabfromrzym+=liczbyArab[iter];

                    }

                    iter--;
            }

        if(tmpRzym!=arab2rzym(arabfromrzym))

        {
            RzymArabException ex;
            throw ex;
        }


        return arabfromrzym;

    }


    static string arab2rzym(int arab)
    {

        if(arab<0||arab>3999)
        {
            RzymArabException ex;
            throw ex;
        }


        string rzymfromarab = "";
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

};

const string RzymArab::liczby[13] = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

const int RzymArab::liczbyArab[13] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};


//Funkcja glowna

int main(int argc, char* argv[])
{

    for(int i=1; i<argc; i++)
    {
        if(Check::isFloat(argv[i]))
        {
            cout<< argv[i]<<" >>> jest liczba zmiennoprzecinkowa!"<<endl;
        }
        else if(Check::isInteger(argv[i]))
        {

            try
            {

                cout<< argv[i]<<" >>> "<<RzymArab::arab2rzym(atoi(argv[i]))<<endl;

            } catch(RzymArabException ex)
            {
                cout<<argv[i]<<" >>> Liczba arabska podana w argumencie funkcji jest spoza zakresu 1 - 3999!"<<endl;
            }

        } else
        {
            try
            {
                cout<< argv[i]<<" >>> "<<RzymArab::rzym2arab(string(argv[i]))<<endl;

            } catch(RzymArabException ex)
            {
                cout<< argv[i]<<" >>> Podany ciag znakow nie jest liczba rzymska!"<<endl;
            }
        }
    }

    return 0;
}
