package com.company;

import javax.xml.crypto.Data;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        //System.out.println(isFirst(6));
        //pierwsze(10);
       //System.out.println(pierwsze(5));



        long start = new Date().getTime();

        System.out.println(primesCount(1000000000));

       // System.out.println(pierwsze(1000000000));

        long end = new Date().getTime();


        System.out.println(end - start);

        long start2 = new Date().getTime();

        // System.out.println(primesCount(1000000));

        System.out.println(pierwsze(1000000000));

        long end2 = new Date().getTime();


        System.out.println(end2 - start2);




    }

    public static int pierwsze(int n)
    {
        if(n<2) return 0;

        int ilosc = 1;

        if(n==2) return 1;

        boolean pierwsza = true;

        for(int i=3; i<=n; i+=2)
        {
            for(int j=3; j*j<=i; j+=2)
            {
                if(i%j==0)
                {
                    pierwsza = false;
                    break;
                }
            }
            if(pierwsza) ilosc++;
            pierwsza = true;
        }

        return ilosc;

    }

    public static int primesCount(int n) {
        int count = 0;
        boolean[] primes = new boolean[n+1];

        int i = 2;
        while(i < Math.sqrt(n)+1) {
            if(!primes[i])
            {
                count++;
                for (int j = 2 * i; j <= n; j+=i) primes[j] = true;
            }
            i++;
        }
        for(; i <= n; i++) if(!primes[i]) count++;

        return count;
    }

}
