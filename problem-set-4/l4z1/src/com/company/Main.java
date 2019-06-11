package com.company;

public class Main {

    public static void main(String[] args) {

        if(args.length==0)
        {
            System.out.println("Brak argumentów!");
            System.exit(0);
        }

        if(!firstArgCheck(args[0]))
        {
            System.out.println("Błędny pierwszy parametr!");
            System.exit(0);
        }

        int argsAm = args.length - 1;

        for(int i=1; i<=argsAm; i++)
        {
            if((!isInteger(args[i]))&&(!isFloat(args[i])))
            {
                System.out.println("Błędne parametry - Drugi i każdy kolejny parametr musi być liczbą!");
                System.exit(0);
            }
        }

        for(int i=0; i<args[0].length(); i++)
        {
            if(args[0].charAt(i)=='o'||args[0].charAt(i)=='p')
            {
                argsAm--;
            } else if(args[0].charAt(i)=='c')
            {
                argsAm-=5;
            }
        }

        if(argsAm<0)
        {
            System.out.print("Błąd - Za mało parametrów.");
            System.exit(0);
        } else if(argsAm>0)
        {
            System.out.print("Błąd - Pojawiły się zbędne parametry!");
            System.exit(0);
        }

        int NumOfFigs = args[0].length();



        int Pointer = 1;

        for(int i=0; i<NumOfFigs; i++)
        {
            if(args[0].charAt(i)=='o')
            {
                if(Double.parseDouble(args[Pointer])<=0.0)
                {
                    System.out.println("Błędne parametry! Okrąg o promieniu mniejszym bądź równym zero!");
                    System.exit(0);
                }
                Pointer++;

            } else if(args[0].charAt(i)=='c')
            {
                if(!args[Pointer+4].equals("90")) {

                    if(Double.compare(Double.parseDouble(args[Pointer]), 0.0d)<0||Double.compare(Double.parseDouble(args[Pointer+1]), 0.0d)<0||Double.compare(Double.parseDouble(args[Pointer+2]), 0.0d)<0||Double.compare(Double.parseDouble(args[Pointer+3]), 0.0d)<0)
                    {
                        System.out.println("Błędne parametry! Bok rombu musi być większy od zera!");
                        System.exit(0);
                    }
                    else if(Double.compare(Double.parseDouble(args[Pointer+4]), 0.0d)<=0||Double.compare(Double.parseDouble(args[Pointer+4]), 180.0d)>=0)
                    {
                        System.out.println("Błędne parametry! Kąt w rombie to liczba X z zakresu X > 0 i X < 180");
                        System.exit(0);
                    }
                    else if(Double.parseDouble(args[Pointer])!=Double.parseDouble(args[Pointer+1])||Double.parseDouble(args[Pointer])!=Double.parseDouble(args[Pointer+2])||Double.parseDouble(args[Pointer])!=Double.parseDouble(args[Pointer+3]))
                    {
                        System.out.println("Błędne parametry! Romb musi mieć wszystkie boki równe!");
                        System.exit(0);
                    }

                } else if(Double.parseDouble(args[Pointer])==Double.parseDouble(args[Pointer+1])&&Double.parseDouble(args[Pointer])==Double.parseDouble(args[Pointer+2])&&Double.parseDouble(args[Pointer])==Double.parseDouble(args[Pointer+3]))
                {
                    continue;

                } else
                {
                    double[] table;

                    table = new double [4];

                    for(int j=0; j<4; j++) {
                        table[j] = Double.parseDouble(args[Pointer+j]);
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
                        System.out.println("Błędne parametry! Prostokąt powinien mieć dwie pary boków równych!");
                        System.exit(0);
                    }

                }
                Pointer+=5;
            } else if(args[0].charAt(i)=='p')
            {
                if(Double.compare(Double.parseDouble(args[Pointer]), 0.0d)<=0)
                {
                    System.out.println("Błędne parametry! Bok w pięciokącie musi być większy od zera!");
                    System.exit(0);
                }
                Pointer+=1;
            }
        }

        Pointer = 1;

        Figura[] Table = new Figura[NumOfFigs];

        for(int i=0; i<NumOfFigs; i++)
        {
            if(args[0].charAt(i)=='o')
            {
                Table[i] = new Okrag(Integer.parseInt(args[Pointer]));
                Pointer++;

            } else if(args[0].charAt(i)=='c')
            {
                if(!args[Pointer+4].equals("90")) {

                    Table[i] = new Romb(Double.parseDouble(args[Pointer]), Double.parseDouble(args[Pointer+1]), Double.parseDouble(args[Pointer + 2]), Double.parseDouble(args[Pointer+3]), Double.parseDouble(args[Pointer+4]));


                } else if(Double.parseDouble(args[Pointer])==Double.parseDouble(args[Pointer+1])&&Double.parseDouble(args[Pointer])==Double.parseDouble(args[Pointer+2])&&Double.parseDouble(args[Pointer])==Double.parseDouble(args[Pointer+3]))
                {
                    Table[i] = new Kwadrat(Double.parseDouble(args[Pointer]), Double.parseDouble(args[Pointer+1]), Double.parseDouble(args[Pointer + 2]), Double.parseDouble(args[Pointer+3]), Double.parseDouble(args[Pointer+4]));

                } else
                {
                    Table[i] = new Prostakat(Double.parseDouble(args[Pointer]), Double.parseDouble(args[Pointer+1]), Double.parseDouble(args[Pointer + 2]), Double.parseDouble(args[Pointer+3]), Double.parseDouble(args[Pointer+4]));

                }
                Pointer+=5;
            } else if(args[0].charAt(i)=='p')
            {
                Table[i] = new Pieciakat(Double.parseDouble(args[Pointer]));
                Pointer+=1;
            }
        }

        for(int i=0; i<NumOfFigs; i++)
        {
            System.out.println(Table[i].nazwa + " - Pole: " + Math.round(Table[i].Pole*100.0)/100.0 + " Obwód: " + Math.round(Table[i].Obwod*100.0)/100.0);

        }

    }

    public static boolean firstArgCheck(String test)
    {
        for(int i=0; i<test.length(); i++)
        {
            if(test.charAt(i)!='c'&&test.charAt(i)!='p'&&test.charAt(i)!='o') {
                return false;
            }
        }
        return true;
    }

    public static boolean isInteger(String str) {

        return str.matches("^-?\\d+$");

    }

    public static boolean isFloat(String str) {

        return str.matches("^-?\\d+.+\\d$");

    }
}
