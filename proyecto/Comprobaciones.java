
import java.util.Arrays;

public class Comprobaciones 
{

        //MAIN DE PRUEBAS PARA LAS COMBINACIONES, NO LO BORRO POR SI PUDIERAN APARECER POSIBLES FALLOS DE COMPROBACIONES EN FUTURO.

    /* public static void main(String[] args) 
    {
        //21,8,34,39,45
        int prueba[] = {40,52,41,42,44};
        boolean hay = false;

        hay = hayPareja(prueba);
        System.out.println(hay + " pareja");
        
        hay = false;
        hay = hayTrio(prueba);
        System.out.println(hay + " trio");
        
        if (hay == false)
        {
            hay = hayDoblePar(prueba);
        }else{
            hay = false;
        }
        
        System.out.println(hay + "  Doble Par");
        
        hay = flush(prueba);
        System.out.println(hay + " flush");

        hay = hayEscalera(prueba);
        System.out.println(hay + " escalera");
        
        hay =false;
        hay = hayPoquer(prueba);
        System.out.println(hay + " poquer");
        
        if (hay == false)
        {
            hay = hayFullHouse(prueba);
        }else{
            hay = false;
        }
        System.out.println(hay + " fullhouse");

        hay = hayEscaleraReal(prueba);
        System.out.println(hay + " esc real");

        hay = hayEscaleraColor(prueba);
        System.out.println(hay + " esc color");
        
        

        int modulo = (44 % 13);
        System.out.println(modulo);
  
    }  */


    // comprueba si hay una pareja en el vector de 5 que le pasemos como argumento
    public static boolean hayPareja(int mano[])  
    
    {
        int a = 0;
        boolean hayPareja = false;
        int pareja;
        boolean seguir = true;
        Arrays.sort(mano);
        for (int i = 1; i < 53 && seguir == true; i++)
        {
            pareja = MiArrayInt.buscar(mano, (i)); //vemos si el elemento que buscamos esta en la mano
            if (pareja != -1) //si está
            {
                for (int j = 0; j < mano.length && seguir == true; j++) //comprobamos si algun elemento es igual que otro con incrementos de 13, que seria su pareja en baraja base 52
                {
                    for (int k = 13; k < 53; k+=13)
                    {
                        a = i+k;
                        if (a== (mano[j]))
                        {
                            hayPareja = true;
                            seguir = false;
                            break;
                        }
                    
                    } 
                }  
            }
        }
    return hayPareja;   
    }
    
    public static boolean hayTrio(int mano[])
    {
        boolean hayTrio = false;
        int max = 0;
        int pareja;
        boolean seguir = true;
        Arrays.sort(mano);
        int a = 0;
        
    //buscamos si hay dos numeros que forman una pareja y apartir de esta, buscamos otro numero más igual a estos

        for (int i = 1; i < 53 && seguir == true; i++)
        {
            pareja = MiArrayInt.buscar(mano, (i)); //vemos si el elemento que buscamos esta en la mano
            if (pareja != -1) //si está
            {
                for (int j = 0; j < mano.length && seguir == true; j++)
                {
                    for (int k = 13; k < 53; k+=13)
                    {
                        a = i+k;
                        if (a== (mano[j]))
                        {
                           max = a;
                           seguir = false;
                           break;
                        }
                    
                    } 
                }  
            }
        }

        max+=13;

        if (seguir == false)
        {
            for (int i = max; i <53 && hayTrio == false; i+=13)
            {
                int trio = MiArrayInt.buscar(mano,i);
                if (trio != -1)
                {
                    hayTrio = true;
                }
            }
        }
       
        return hayTrio;
    }

//si no hay un trio, un doble par se puede calcular quitando los repetidos de un vector {1,1,2,2,4} = {1,2,4}, y si la longitud es 3 significa que hay doble par

    public static boolean hayDoblePar(int mano[])
    {
        boolean hayDoblePar = false;
        int convertida[] = convertirMano(mano);
        int parejas[] = MiArrayInt.sinRepetidos(convertida);
        
        if (parejas.length == 3)
        {
            hayDoblePar = true;
        }
        
    return hayDoblePar;
    }

    public static boolean flush(int mano[])
    {

        boolean hayFlush = false;
        Arrays.sort(mano);
        int max = 0;
        int min = mano[0];
        int cont = 0;

    // ordenamos una mano y vemos si todos sus valores están entre el minimo y el minimo +13, que quiere decir que estamos dentro de un mismo palo (1-13 C, 14-26 P, 27-39 D, 40-52 T)    
        if (min <14)
        {
            max = 13;
        }else if (min >13 && min < 27)
        {
            max = 26;
        }else if (min >26 && min < 40)
        {
            max = 39;
        }else if (min >39 && min < 53)
        {
            max = 52;
        }
    
        for (int i = 0; i < 5; i++)
        {
            if (mano[i] >= min && mano[i] <=max)
            {
                cont++;
                
                if (cont == 5)
                {
                    hayFlush = true;
                }
            }
        }
    return hayFlush;
    }
    
    public static int[] convertirMano(int mano[])
    {
        int valores[]= new int[5];
        Arrays.sort(mano);
        for (int i= 0; i < 5; i++)
        {
            if (mano[i] > 13)
            {
                if (mano[i] == 24) //J PICAS
                    {
                        valores[i] = 11;
                    }
                    else if (mano[i] == 25) // Q PICAS
                    {
                        valores[i] = 12;
                    }
                    else if (mano[i] == 26) //K PICAS
                    {
                        valores[i] = 13;
                    }
                    else if (mano[i] == 37) // J DIA
                    {
                        valores[i] = 11;
                    }
                    else if (mano[i] == 38) //Q DIA
                    {
                        valores[i] = 12;
                    }
                    else if (mano[i] == 39) // K DIA
                    {
                        valores[i] = 13;
                    }
                    else if (mano[i] == 50) //J TREBOLES
                    {
                        valores[i] = 11;
                    }
                    else if (mano[i] == 51) // Q TREBOLES
                    {
                        valores[i] = 12;
                    }
                    else if (mano[i] == 52) //K TREBOLES
                    {
                        valores[i] = 13;
                    }
                    else
                    {
                        valores[i] = mano[i] %13;
                    }   
            }
            else
                {
                    valores[i] = mano[i];
                }  
        } 
        Arrays.sort(valores);
    return valores;
    }

    public static boolean hayFullHouse(int mano[]) // SI NO HAY POQUER 
    {
        boolean siHay = false;
        int convertido[] = convertirMano(mano);
        convertido = MiArrayInt.sinRepetidos(convertido);
        
        if (convertido.length == 2)
        {
            siHay= true;
        }
    
    return siHay;
    }

    public static boolean hayEscaleraColor(int mano[])
    {
        boolean siHayEscColor = false;
        
        if (hayEscalera(mano) && flush(mano))
        {
            siHayEscColor = true;
        }
    return siHayEscColor;
    }

    public static boolean hayEscaleraReal(int mano[])
    {
        int convertida[] = convertirMano(mano);
        boolean siHay = false;
        
        if (convertida[0] == 1)
        {
            convertida[0] = 14;
            Arrays.sort(convertida);
        }

        if (flush(mano))
        {
            if (convertida[0] == 10 && convertida[4] == 14)
            {
            siHay = true;
            }
        }
        
        return siHay;
    }
    public static boolean hayEscalera(int mano[])
    {
        boolean hayEscalera = false;
        int seguir = 0;
        int cont = 0;
        int valores[] = convertirMano(mano);
        int j =1;
        
        while (seguir < 2)
        {
            for (j= 1; j < 5; j++)
            {
                if (valores[j] == (valores[(j-1)]+1))
                {
                  
                }
                else
                {
                    break;
                }
            }
            if (j==5)
            {
                hayEscalera = true;
            }
            else if (valores[0] == 1)
            {
                valores[0] = 14;
                Arrays.sort(valores);
                cont = 0;
            }    
            seguir ++;
        }
        if (hayPareja(mano))
        {
            hayEscalera = false;
        }

    return hayEscalera;
    }
    public static boolean hayPoquer(int mano[])
    {
        boolean hayPoquer = false;
        int convertido[] = convertirMano(mano);
        int cont = 0;
        int i=0;

        while (cont ==0 && hayPoquer ==false)
        {
            for (i = 0; i < 3; i++)
            {
                if (convertido[i] == convertido[(i+1)])
                {

                }
                else
                {
                    MiArrayInt.reverseProc(convertido);
                    break;
                }
            }
            
            if (i==3)
            {
                hayPoquer =true;
            }
        cont++;
        }

    return hayPoquer;
    }

    public static int comprobarMano(int mano[])
    {
        int puntos = 0;
        
        if(hayPareja(mano))
        {
            puntos = 200;
        }
        else
        {
            if(hayEscalera(mano))
            {
            puntos = 0;
            puntos = 1000;
            }
        }
        if (hayTrio(mano))
        {
            puntos = 0;
            puntos = 800;
        }
        else 
        {
            if (hayDoblePar(mano))
            {
                puntos = 0;
                puntos = 400;
            }   
        }
        
        if (flush(mano))
        {
            puntos = 0;
            puntos = 1400;
        }
        
        if (hayPoquer(mano))
        {
            puntos = 0;
            puntos = 2000;
        }
        else
        {
            if (hayFullHouse(mano))
            {
                puntos = 0;
                puntos = 1800;
            }
        }

        if (hayEscaleraColor(mano))
        {
            puntos = 0;
            puntos = 2400;
        }
        if (hayEscaleraReal(mano))
        {
            puntos = 0; 
            puntos = 2800;
        }

    return puntos; 
    }


    //COLORES
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    //FONDOS
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

}

    