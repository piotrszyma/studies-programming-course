#include <iostream>
#include <cstdlib>
#include <cctype>
#include <string.h>

using namespace std;


class LiczbyPierwsze {
private:

    int* Array;
    int firstAmount;

public:

    LiczbyPierwsze(int n)
    {
        int ArrSize = n;
        if(ArrSize%2!=0) ArrSize++;
        Array = new int[ArrSize/2];
        int j = 0;

        for(int i=2; i<=n; i++) {

            if(isFirst(i))
            {
                Array[j] = i;
                j++;
            }
        }
        firstAmount = j;

    }

    ~LiczbyPierwsze()
    {
        delete[] Array;
    }

    bool isFirst(int n)
    {
        if(n<=1) return false;

        for(int i=2; i*i<=n; i++)
        {
            if(n%i==0) return false;
        }

        return true;
    }

    static bool isInteger(char* n)
    {
        int iter = 0;

        if(n[0]=='-')
        {
            n++;
        }

        if(n[0]==NULL) return false;

        while(n[iter])
        {
            if(!isdigit((int)n[iter++]))
            {
                return false;
            }
        }
        return true;
    }

    int liczba(char* n)
    {
        if(!isInteger(n))
        {
            cout<<n<<" - nieprawidlowa dana"<<endl;
            return 0;
        }
        else if(atoi(n)<0||atoi(n)>=firstAmount)
        {
            cout<<n<<" - liczba spoza zakresu"<<endl;
            return 0;
        } else
        {
            cout<<n<<" - "<<Array[atoi(n)]<<endl;
            return atoi(n);
        }
    }
};


int main(int argc, char* argv[])
{

    if(!(LiczbyPierwsze::isInteger(argv[1])))
    {
        cout<<argv[1]<<" - nie jest liczba"<<endl;
        return 0;
    } else if(atoi(argv[1])<0)
    {
        cout<<argv[1]<<" - Nieprawidlowy zakres"<<endl;
        return 0;
    }

    LiczbyPierwsze Array(atoi(argv[1]));

    int i = 2;
    while(argv[i])
    {
        Array.liczba(argv[i++]);
    }

    return 0;
}
