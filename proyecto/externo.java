
import java.util.Arrays;

public class externo 
{
    public static String[][] generarTableroExterno()
    {
        String convertido[][] = new String[5][5];
    return convertido;
    }

    public static String[][] MatrizMontones(int m[][]) //muestra una matriz pasada como argumento por pantalla
    {
        int j = 0;
        int columnas = m[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = m.length;        //longitud de las filas
        String montones[][] = new String[fila][columnas];

        for (int i = 0; i < fila; i++ )
        {
            for (j = 0; j < columnas; j++)
            {
                if(m[i][j]==0)
                {
                    montones[i][j] = new String(Character.toChars(0x1F0A0)); 
                }
                else
                {
                    montones[i][j] = "  ";
                }
            }
            System.out.println();
        }
    return montones;
    }
    public static void mostrarMatrizMontones(String m[][]) //muestra una matriz pasada como argumento por pantalla
    {
        
        int j = 0;
        int columnas = m[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = m.length;        //longitud de las filas

        String espacios ="";
        
        if (columnas == 4)
        {
            espacios = espacios + "      ";
        }
        else if (columnas == 3)
        {
            espacios = espacios + "         ";
        }
        else if (columnas == 2)
        {
            espacios = espacios + "            ";
        }
        else if (columnas == 1)
        {
            espacios = espacios + "               ";
        }

        for (int i = 0; i < fila; i++ )
        {
            System.out.print(espacios);
            for (j = 0; j < columnas; j++)
            {
                System.out.print(m[i][j]+ "    ");
            }
            System.out.println();
        }
    }

    public static void tablaPuntuaciones(String nombre[], int puntos[], long tiempo[],int filas[],int cols[])
    {
        int iEspaciosPuntos = 0;
        int iEspaciosTiempo = 0;
        String espaciosTiempo[] = new String[1];
        String espaciosPuntos[] = new String[1];
        
            System.out.println("      _______________________________________________________________");
            System.out.println("     /                                                               \\");
            System.out.println("    /     > DIFICULTAD    > NOMBRE      > PUNTOS       > TIEMPO       \\");
            System.out.println("   ||      -----------      ------        ------         ------       ||");
            
            for (int cont = 0; cont < nombre.length; cont++) // lo hacemos hasta nombre.length porque es la cantidad de jugadores que guarda el string de nombres
            {
                if (tiempo[cont] >100 && tiempo[cont] < 1000) // con esto controlamos el numero de espacios que es necesario escribir dependiendo de las cifras del tiempo
                {
                    espaciosTiempo[iEspaciosTiempo] = "        ";
                }
                else if (tiempo[cont] >= 1000 && tiempo[cont] <= 10000)
                {
                    espaciosTiempo[iEspaciosTiempo] = "      ";
                }
                else if (tiempo[cont] < 100)
                {
                    espaciosTiempo[iEspaciosTiempo] = "         ";
                }
                //________________________________________________
                
                if (puntos[cont] >100 && puntos[cont] < 1000) // con esto controlamos el numero de espacios que es necesario escribir dependiendo de las cifras de puntos
                {
                    espaciosPuntos[iEspaciosPuntos] = "            ";
                }
                else if (puntos[cont] >= 1000 && puntos[cont] <= 10000)
                {
                    espaciosPuntos[iEspaciosPuntos] = "           ";
                }
                else if (puntos[cont] < 100)
                {
                    espaciosPuntos[iEspaciosPuntos] = "             ";
                }
                
                System.out.println("   ||      "+ "F :" +filas[cont]+ "   C :"+cols[cont] + "      "+ nombre[cont] + "  "+ ANSI_YELLOW+ puntos[cont]+ ANSI_WHITE + espaciosPuntos[iEspaciosPuntos] + tiempo[cont] + " s" + espaciosTiempo[iEspaciosTiempo] + "||");
                iEspaciosTiempo++;
                iEspaciosPuntos++;
                espaciosPuntos = Arrays.copyOf(espaciosPuntos,espaciosPuntos.length+1);
                espaciosTiempo = Arrays.copyOf(espaciosTiempo,espaciosTiempo.length+1);
            }
             
            System.out.println("   ||                                                                 ||");
            System.out.println("   ||                                                                 ||");
            System.out.println("   ||                                                                 ||");
            System.out.println("   ||                                                                 ||");
            System.out.println("   ||                                                                 ||");
            System.out.println("   ||                                                                 ||");
            System.out.println("   ||                                                                 ||");
            System.out.println("   | \\_____________________-> - 1 para volver <-_____________________/ |");
            System.out.println("   \\___________________________________________________________________/");
    }

    public static String[][] generarTableroArriba(int filas, int cols)
    {
        String convertido[][] = new String[filas][cols];
    return convertido;
    }

    public static void limpiarConsola()
    {
        for (int i = 0; i < 30; i++)
        {
            System.out.println();
        }
    }

    public static void mostrarMatrizArriba(String m[][]) //muestra una matriz pasada como argumento por pantalla
    {
        int j = 0;
        int columnas = m[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = m.length;        //longitud de las filas
        String diseño = "";
        String flecha = "";
        String espacios = "";


        //ESPACIOS DEL TABLERO DE ARRIBA PARA QUE QUEDE CENTRADO
            if (columnas == 4)
            {
                espacios = espacios + "   ";
            }
            else if (columnas == 3)
            {
                espacios = espacios + "      ";
            }
            else if (columnas == 2)
            {
                espacios = espacios + "         ";
            }
            else if (columnas == 1)
            {
                espacios = espacios + "            ";
            }

            //MOSTRAMOS LA MATRIZ DE ARRIBA (Extracción)
            for (int i = 0; i < columnas-1; i++)
            {
                diseño = diseño + "-----|";
            }
                                        
        System.out.println(espacios +"|-----|" + diseño);
        
        for (int i = 0; i < fila; i++ )
        {
            System.out.print(espacios);
            for (j = 0; j < columnas; j++)
            {
                System.out.print("|"+ANSI_WHITE_BACKGROUND + " " + m[i][j] + " " +ANSI_BLACK_BACKGROUND);
            }

            System.out.println("|");
            System.out.print(espacios +"|"+ANSI_WHITE_BACKGROUND +"     " +ANSI_BLACK_BACKGROUND+"|");
            
            for(int k =0; k < columnas-1; k++)
            {
                System.out.print(ANSI_WHITE_BACKGROUND +"     " +ANSI_BLACK_BACKGROUND+"|");
            }
            System.out.println();
            System.out.println(espacios +"|-----|" + diseño);
        }
            flecha="";
        
            for (int i = 0; i < columnas-1; i++)
            {
                flecha =flecha + "     |";
            }

        System.out.println(espacios +"   |"+flecha);
            flecha="";
        
            for (int i = 0; i < columnas-1; i++)
            {
                flecha =flecha + "     v";
            }
        System.out.println(espacios +"   v"+flecha);
        
        System.out.println(espacios +"|-----|" + diseño);
        
        //mostramos la interfaz de seleccion de columnas dependiendo del numero de estas
        System.out.print(espacios +"|- 1 -|");
            
            if (columnas == 2)
            {
                System.out.print("- 2 -|");
            }
            else if (columnas == 3 )
            {
                System.out.print("- 2 -|");
                System.out.print("- 3 -|");
            }
            else if (columnas == 4 )
            {
                System.out.print("- 2 -|");
                System.out.print("- 3 -|");
                System.out.print("- 4 -|");
            }

            System.out.println();
        System.out.println(espacios +"|-----|" + diseño);
    }

    public static void conversor(String convertido[][],int tablero5x5[][])  // convierte los numeros a la carta que hace referencia, modificando una matriz String, para que funcione hay que activar el "chcp 65001" en la consola
    //recibe la matriz de numeros con la que estamos jugando
    {
        int j = 0;
        int columnas = tablero5x5[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = tablero5x5.length;        //longitud de las filas
        
        for (int i = 0; i < fila; i++ )
        {
            for (j = 0; j < columnas; j++) 
            {
                // la diferencia entre los subcasos que hay dentro de cada palo viene de si el número tiene dos cifras, o solo una, para controlar los espacios

                if(tablero5x5[i][j] == 0)   // caso que ese espacio esté vacío
                {
                    convertido[i][j] = "   ";
                }

                if (tablero5x5[i][j] < 14 && tablero5x5[i][j] != 0) //caso que sean corazones
                {
                    if(tablero5x5[i][j] >1 && tablero5x5[i][j] < 10)
                    {
                        convertido[i][j] = ANSI_PURPLE + tablero5x5[i][j] + " \u2665" + ANSI_WHITE;
                    }
                        else if (tablero5x5[i][j] == 10){
                            convertido[i][j] = ANSI_PURPLE + "10\u2665" + ANSI_WHITE;
                        }
                        else if (tablero5x5[i][j] == 1){
                            convertido[i][j] = ANSI_PURPLE +"A \u2665"+ ANSI_WHITE;
                        }
                        else if (tablero5x5[i][j] == 11){
                            convertido[i][j] = ANSI_PURPLE +"J \u2665"+ ANSI_WHITE;
                        }
                        else if (tablero5x5[i][j] == 12){
                            convertido[i][j] = ANSI_PURPLE +"Q \u2665"+ ANSI_WHITE;
                        }
                        else if (tablero5x5[i][j] == 13){
                            convertido[i][j] = ANSI_PURPLE +"K \u2665"+ ANSI_WHITE;
                        }
                    
                }
                
                if ( tablero5x5[i][j] > 13 &&tablero5x5[i][j] < 27 ) //caso que sean picas
                { 
                    if (tablero5x5[i][j] == 14)
                    {
                        convertido[i][j] = ANSI_BLACK +"A \u2660"+ ANSI_WHITE;
                    }

                    if (tablero5x5[i][j] == 20)
                    {
                        convertido[i][j] = ANSI_BLACK + "7 \u2660" + ANSI_WHITE;
                    }

                    if (tablero5x5[i][j] > 14 &&tablero5x5[i][j] < 20)
                    {
                        convertido[i][j] = ANSI_BLACK +((tablero5x5[i][j] % 10)-3) + " \u2660" + ANSI_WHITE;          
                    }

                    if (tablero5x5[i][j] < 24 && tablero5x5[i][j] > 20)
                    { 
                        if (tablero5x5[i][j] == 23)
                            {
                                convertido[i][j] = ANSI_BLACK +"10\u2660" + ANSI_WHITE;
                            }
                                else
                                {
                                    convertido[i][j] = ANSI_BLACK +((tablero5x5[i][j] % 20)+7) + " \u2660" + ANSI_WHITE;          
                                }
                    
                    }else if (tablero5x5[i][j] == 24){
                        convertido[i][j] = ANSI_BLACK +"J \u2660" + ANSI_WHITE;
                    }
                    else if (tablero5x5[i][j] == 25){
                        convertido[i][j] = ANSI_BLACK +"Q \u2660" + ANSI_WHITE;
                    }
                    else if (tablero5x5[i][j] == 26){
                        convertido[i][j] = ANSI_BLACK +"K \u2660" + ANSI_WHITE;
                    }
                }

                if (tablero5x5[i][j] < 40 && tablero5x5[i][j] > 26) //caso que sean diamantes
                {

                    if (tablero5x5[i][j] == 27)
                    {
                        convertido[i][j] =ANSI_CYAN +"A \u2666"+ ANSI_WHITE;
                    }

                    if(tablero5x5[i][j] >27 &&tablero5x5[i][j] < 30){
                        convertido[i][j] =ANSI_CYAN + ((tablero5x5[i][j] % 20)-6) + " \u2666"+ ANSI_WHITE;          
                    }
                    
                    if (tablero5x5[i][j] == 30){
                        convertido[i][j] =ANSI_CYAN +"4 \u2666"+ ANSI_WHITE;
                    }

                    if(tablero5x5[i][j] < 37 && tablero5x5[i][j] > 30)
                        
                        if(tablero5x5[i][j] == 36)
                        {
                            convertido[i][j] = ANSI_CYAN + "10\u2666"+ ANSI_WHITE;
                        }
                            else
                            {
                                convertido[i][j] = ANSI_CYAN + ((tablero5x5[i][j] % 30)+4) + " \u2666"+ ANSI_WHITE;          
                            }

                    else if (tablero5x5[i][j] == 37){
                        convertido[i][j] = ANSI_CYAN +"J \u2666"+ ANSI_WHITE;
                    }
                    else if (tablero5x5[i][j] == 38){
                        convertido[i][j] = ANSI_CYAN +"Q \u2666"+ ANSI_WHITE;
                    }
                    else if (tablero5x5[i][j] == 39){
                        convertido[i][j] = ANSI_CYAN + "K \u2666"+ ANSI_WHITE;
                    }
                }

                if (tablero5x5[i][j] < 53 && tablero5x5[i][j] > 39) //caso que sean tréboles
                {
                    if (tablero5x5[i][j] == 40)
                    {
                        convertido[i][j] = ANSI_GREEN +"A \u2663"+ ANSI_WHITE;
                    }
                    if(tablero5x5[i][j] > 40 && tablero5x5[i][j] < 50)
                    {
                        if(((tablero5x5[i][j] % 40)+1) < 10)
                        {
                        convertido[i][j] = ANSI_GREEN +((tablero5x5[i][j] % 40)+1) + " \u2663" + ANSI_WHITE;
                        }
                            else{
                                convertido[i][j] = ANSI_GREEN +((tablero5x5[i][j] % 40)+1) + "\u2663" + ANSI_WHITE;
                            }
                                  
                    }else if (tablero5x5[i][j] == 50){
                            convertido[i][j] = ANSI_GREEN +"J \u2663" + ANSI_WHITE;
                        }
                        else if (tablero5x5[i][j] == 51){
                            convertido[i][j] = ANSI_GREEN +"Q \u2663" + ANSI_WHITE;
                        }
                        else if (tablero5x5[i][j] == 52){
                            convertido[i][j] = ANSI_GREEN +"K \u2663" + ANSI_WHITE;
                        }
                }
            }
        }
    }

    public static String mostrarJugada(int mano[])
    {
        String jugada ="000";
        
        if(Comprobaciones.hayPareja(mano))
        {
            jugada ="1PR";
        }
        else
        {
            if(Comprobaciones.hayEscalera(mano))
            {
            jugada ="STR";
            }
        }
        if (Comprobaciones.hayTrio(mano))
        {
            jugada ="3 K";
        }
        else 
        {
            if (Comprobaciones.hayDoblePar(mano))
            {
                jugada ="2PR";
            }   
        }
        
        if (Comprobaciones.flush(mano))
        {
            jugada =" FL";
        }
        
        if (Comprobaciones.hayPoquer(mano))
        {
            jugada =" 4K";
        }
        else
        {
            if (Comprobaciones.hayFullHouse(mano))
            {
                jugada =" FH";
            }
        }

        if (Comprobaciones.hayEscaleraColor(mano))
        {
            jugada =" SF";
        }
        if (Comprobaciones.hayEscaleraReal(mano))
        {
            jugada = " RF" ;
        }

    return jugada; 
    }
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
