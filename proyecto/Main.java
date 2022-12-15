
import java.io.IOException;
import java.util.Arrays;
import java.util.Currency;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args)  //chcp 65001 - escribir eso en la consola antes de empezar
    {
        Scanner sc = new Scanner (System.in);
    //vectores para los datos de juego de los diferentes jugadores (nombre,puntos totales y tiempo)
        long diferenciaTiempo[] = new long[0];
        String nombre[] = new String[0];
        int puntos[] = new int[0];
        int contador = 0;
        int filasTabla[] = new int[0];
        int colsTabla[] = new int[0];

        int datos[] =new int[0]; //Los datos que recogeremos del menu
        int mano[] = new int[5];
        int numVeces[] = {0}; //veces que mostramos el mensaje de bienvenida
        
        do{ //bucle para el juego
            datos = Menu(nombre, puntos, diferenciaTiempo,numVeces,filasTabla,colsTabla);  // este es el mensaje de bienvenida que a su vez pide los datos de dificultad
            
            if (datos[2] != -1)
            {   

            // aqui no se muestra nada son solo declaraciones de variables y creacion de las tablas que usaremos en el juego

                String jugadaCols[] = new String[5];     // vector para controlar la posicion de las columnas donde tenemos que poner la jugada correspondiente
                Arrays.fill(jugadaCols,"   ");

                String jugada[] = new String[5];         // jugada de las filas
                Arrays.fill(jugada,"   ");

            //Strings para guardar las jugadas de las dos diagonales
                String jugadaDiaDerecha = "   ";
                String jugadaDiaIzquierda = "   ";

                int columaLlena[] = new int[5]; //variable donde guardaremos el numero de columna que está llena en cada jugada
                Arrays.fill(columaLlena,9); //la inicializamos a un valor distinto de 0 puesto que lo necesito así para su funcionamiento, ya que el 0 tambien es una columna
                
                int manoDiagonales[] = new int[5];
                int contColumnas = 0;   
                int contFilas = 4;
                
                long firstTime = 0;
                long lastTime = 0;
                
                int filasDeDificultad = datos[0]; // este dato hace referencia a la cantidad de filas que queremos ver
                int colsDeDificultad = datos[1];  //cantidadd de columnas
             
            //variables de control para la comprobación de las dos diagonales
                boolean diagonalDerecha = false;
                boolean diagonalIzquierda = false;
            
            //significa que se incluye otro jugador por lo que necesitamos agrandar nuestros vectores de datos de este, esto lo hacemos en cada "nueva partida"
                puntos = Arrays.copyOf(puntos, puntos.length+1);
                diferenciaTiempo = Arrays.copyOf(diferenciaTiempo, diferenciaTiempo.length+1);
                nombre = Arrays.copyOf(nombre, nombre.length+1);
                filasTabla = Arrays.copyOf(filasTabla,filasTabla.length+1);
                colsTabla = Arrays.copyOf(colsTabla,colsTabla.length+1);

                int baraja[] = interno.crearBaraja();       //creamos la baraja, con nums del 0 al 52
                int tableroArriba[][] = interno.verCartas(filasDeDificultad, colsDeDificultad, baraja); //creamos el tablero de arriba donde vamos eligiendo las cartas, con la dificultad indicada (interno)
                int tableroJuego[][] = interno.generarTableroJuego();   //generamos el tablero de juego (matriz 5x5) (interno)
                
                int montones[] = new int[colsDeDificultad];                     //cantidad de montones de los que podremos extraer cartas dependiendo de las filas de dificultad
                int numCartas = 52 - (filasDeDificultad *colsDeDificultad);     //calculamos la cantidad de cartas restantes después de rellenar la matriz de extracción (arriba)
                    //{
                        numCartas = numCartas/colsDeDificultad;
                        Arrays.fill(montones, numCartas);
                        int matrizMontones[][] = new int[numCartas][colsDeDificultad];
                    //}
                    
                    
                String tableroArribaConvertido[][] = externo.generarTableroArriba(filasDeDificultad,colsDeDificultad);      //generamos el tablero de arriba en formato String, que es el que verá el usuario (externo)
                String tableroJuegoConvertido[][] = externo.generarTableroExterno();        //generamos el tablero de juego (5x5) que verá el usuario
                boolean seguir = true;
                
                firstTime = System.currentTimeMillis();
                        
            // empieza la parte interactiva y visible del juego

                 while (seguir == true && datos[2] != -1)     //con este bucle controlamos que seguimos jugando hasta que el tablero 5x5 este lleno
                {
                    externo.limpiarConsola();
                    
                    externo.conversor(tableroArribaConvertido, tableroArriba);  //convertimos el tablero de arriba (interno) a la manera visual (externo) que ve el usuario
                    
                    externo.mostrarMatrizMontones(externo.MatrizMontones(matrizMontones)); 
                    externo.mostrarMatrizArriba(tableroArribaConvertido);       //mostramos el tablero de arriba convertido, donde vemos que cartas vamos a elegir
                    
                    System.out.println();   // Separacion entre la matriz de arriba y la de juego (5x5)

                    externo.conversor(tableroJuegoConvertido,tableroJuego);     // convertimos el tablero de juego interno (números) a la forma visual que ve el usuario, en este caso es una matriz de 5x5 vacía, con espacios en blanco
                    
                    mostrarMatriz(tableroJuegoConvertido,puntos,contador,jugada,jugadaCols,jugadaDiaDerecha,jugadaDiaIzquierda);          //mostramos la matriz de juego, que en este caso está vacía
                    
                    boolean valida = false; //variable para controlar si el numero de extracción de carta o posicionamiento de esta está entre los valores requeridos
                    int colElegida = 0;
                    
                    String mensaje = "Elige carta : ";
                    
                    while (valida == false)
                    {
                        System.out.print(mensaje);
                        colElegida = sc.nextInt();          //leemos la columna de la que el usuario quiere extraer la carta
                       
                        if (colElegida > 0 && colElegida < colsDeDificultad+1)
                        {
                            valida = true;
                        }
                    }
                    valida = false;
                    colElegida--;

                    int carta = interno.pedirCarta(tableroArriba, colElegida);      //sacamos el numero (interno) que hace referencia a la carta elegida
                    
                //interfaz para posicionar la carta en el tablero 5x5

                    System.out.println("   \u2191     \u2191     \u2191     \u2191     \u2191");
                    System.out.println("   1     2     3     4     5");
                    
                //comprobar que el numero de posicionamiento es correcto
                    int posPonerCarta = 0;
                    
                    while (valida == false)
                    {
                        posPonerCarta = sc.nextInt();   //variable que guarda la posición (columna) del tablero 5x5 en el que queremos poner la carta
                        
                        if (posPonerCarta  > 0 && posPonerCarta < 6)
                            {
                                valida = true;
                            }
                    }
                    valida = false;
                    posPonerCarta--;

                    if (tableroJuego[0][posPonerCarta] == 0)    //controlamos que la columna está llena y por tanto no insertamos nada
                    {
                        tableroArriba = interno.bajarPosiciones(colElegida, tableroArriba,baraja,filasDeDificultad,colsDeDificultad,montones);     //eliminamos la carta insertada de la matriz de eleccion y bajamos todas las cartas una fila, e insertamos otra nueva arriba
                        
                        for (int i =0; i < numCartas;i++)
                        {
                            if (matrizMontones[i][colElegida] == 0) //quitamos una carta de la matriz de la interfaz de los montones
                            {
                                matrizMontones[i][colElegida] = -1;
                                break;
                            }
                        }
                        externo.mostrarMatrizMontones(externo.MatrizMontones(matrizMontones));
                    }
                    
                    interno.tableroJuego(tableroJuego, carta, posPonerCarta);           //inserta la carta (num) de forma interna en el tablero 5x5
                    externo.conversor(tableroJuegoConvertido, tableroJuego);            //actualiza la forma visual del tablero 5x5 con la nueva carta insertada
                    
                    //-------------------------------------------------------------------
                    int contFilasCopia = contFilas;
                    for (int i = 0; i < 5; i++) //controlar cuando una fila está llena para ver cuando tenemos que hacer el recuento de puntos
                    {
                        if (tableroJuego[contFilas][i] != 0)
                        {
                            mano[i] = tableroJuego[contFilas][i]; //guardamos en la mano la fila que está llena
                        }
                        
                        int encontrado = MiArrayInt.buscar(mano,0); //buscamos un cero en la fila para ver si aun no está llena
                        
                        if (encontrado == -1 && contFilas >= 0) // si la fila está llena, es decir que no contiene ningun 0
                        {
                            puntos[contador] = puntos[contador] + Comprobaciones.comprobarMano(mano); //comprobamos que combinación tiene la mano y se la asignamos a los puntos del jugador (contador)
                            jugada[contFilasCopia] = externo.mostrarJugada(mano);
                            Arrays.fill(mano,0); // reiniciamos la mano
                            contFilas--;
                            break;
                        } 
                    } 
                    Arrays.fill(mano,0); 
                    //-------------------------------------------------------------------
                    //controlar que una columna esta llena 
                    int elemento = 0;
                    boolean comprobar = false;

                    for (int i = 0; i < 5 && comprobar == false; i++)   //nos movemos por las columnas en busca de una llena
                    {
                        elemento = (MiArrayInt.buscar(columaLlena,i));

                        if (tableroJuego[0][i] != 0 && elemento == -1) // si la columna está llena, y no la hemos revisado anteriormente
                        {
                            columaLlena[contColumnas] = i; // con esto guardamos que columna ha sido completada (indice i)
                            
                            for (int j = 0; j < 5; j++)
                                {
                                    mano[j] = tableroJuego[j][i];
                                }
                            comprobar = true;
                            jugadaCols[(columaLlena[contColumnas])] = externo.mostrarJugada(mano);
                            contColumnas++; //avanzamos de posicion en el vector de contadores de columnas
                            break;
                        }
                    } 
                    
                    if (comprobar == true)
                    {
                        puntos[contador] = puntos[contador] + Comprobaciones.comprobarMano(mano);
                        Arrays.fill(mano,0); // reiniciamos la mano
                    }

                    //-------------------------------------------------------------------
                    //controlar que una diagonal esta llena (DERECHA)
                    
                    int fila = 4;
                    int col = 0;
                    int contDiagonales = 0;
                    
                    for (contDiagonales = 0; contDiagonales < 5 && diagonalDerecha == false; contDiagonales++)
                    {
                        if (tableroJuego[fila][col] != 0)
                        {
                            manoDiagonales[contDiagonales] = tableroJuego[fila][col];
                            fila--;
                            col++;
                        }
                        else
                        {
                            break;
                        }
                    }

                    if (contDiagonales == 5) //si esta llena
                    {
                        diagonalDerecha = true; //cambiamos el booleano a true para no volver a comprobar en proximas iteraciones la misma diagonal
                        puntos[contador] = puntos[contador] + Comprobaciones.comprobarMano(manoDiagonales); //comprobamos que combinación tiene la mano y se la asignamos a los puntos del jugador (contador)
                        jugadaDiaDerecha = externo.mostrarJugada(manoDiagonales);  //guarda la jugada que tiene esa diagonal, por ejemplo STR
                        Arrays.fill(manoDiagonales,0); // reiniciamos la mano
                    }
                    //diagonal izquierda
                    fila = 0;
                    col = 0;
                    contDiagonales =0;
                    for (contDiagonales = 0; contDiagonales < 5 && diagonalIzquierda == false; contDiagonales++)
                    {
                        if (tableroJuego[fila][col] != 0)
                        {
                            manoDiagonales[contDiagonales] = tableroJuego[fila][col];
                            fila++;
                            col++;
                        }
                        else //significa que no está llena
                        {
                            break;
                        }
                    }

                    if (contDiagonales == 5) //si esta llena
                    {
                        diagonalIzquierda = true;
                        puntos[contador] = puntos[contador] + Comprobaciones.comprobarMano(manoDiagonales); //comprobamos que combinación tiene la mano y se la asignamos a los puntos del jugador (contador)
                        jugadaDiaIzquierda = externo.mostrarJugada(manoDiagonales);
                        Arrays.fill(manoDiagonales,0); // reiniciamos la mano
                    }

                    
                    mostrarMatriz(tableroJuegoConvertido,puntos,contador,jugada,jugadaCols,jugadaDiaDerecha,jugadaDiaIzquierda);                     //mostramos la nueva forma visual del tablero 5x5 actualizada
                    
                    baraja = interno.actualizaBaraja(tableroJuego,baraja);                     //actualizamos la baraja después de haber insertado una carta en la matriz de elección
                    
                    int v[] = new int[5];

                    for (int i = 0; i < 5; i++) // controlamos cuando está llena la matriz viendo si las filas de arriba están todas llenas
                    {
                        v[i] = tableroJuego[0][i];
                        int encontrado = MiArrayInt.buscar(v,0);
                        
                        if (encontrado == -1)
                        {
                            seguir = false;
                            externo.limpiarConsola();
                            mostrarMatriz(tableroJuegoConvertido,puntos,contador,jugada,jugadaCols,jugadaDiaDerecha,jugadaDiaIzquierda);
                        }
                    }
                } 
                lastTime = System.currentTimeMillis(); // medimos el tiempo final de juego

                diferenciaTiempo[contador] = (lastTime - firstTime)/1000;   //calculamos la diferencia (el tiempo de juego real ) de cada jugador 
                
                System.out.println();
                System.out.println("Tu tiempo fue de : " + diferenciaTiempo[contador] + " segundos");
                 
                System.out.println("¿Cúal es tu nombre? ");
                    sc.nextLine();
                
                    nombre[contador] = sc.nextLine();
                
                int longitudNombres = nombre[contador].length(); // ajustamos el nombre al tamaño de String que necesito para que mi tabla no se descuadre Ej: "Enrique    " - "Paco       "
                
                if (longitudNombres < 12) //añadimos espacios al nombre para que se ajuste a las dimensiones de la tabla de puntuaciones
                {
                    for (int i = longitudNombres; i < 12; i++)
                    {
                        nombre[contador] = nombre[contador] + " ";
                    }
                }

            //credenciales de juego del jugador x

                filasTabla[contador] = filasDeDificultad;
                colsTabla[contador] = colsDeDificultad;
                externo.tablaPuntuaciones(nombre,puntos,diferenciaTiempo,filasTabla,colsTabla); //mostramos la tabla de puntuaciones para que el jugador vea los resultados de su partida
                contador++;
                
                //______________________________________________________________________________________________________
                firstTime =(System.currentTimeMillis());
                long tiempoEspera = 0;

                System.out.println();
                while(tiempoEspera < 7)  // con este bucle esperamos 7 segundos antes de darle la entrada al juego
                {
                    lastTime =(System.currentTimeMillis());
                    tiempoEspera = (lastTime - firstTime) / 1000;
                    System.out.print("             Volviendo automáticamente en : " + ((7 - tiempoEspera))  + " s" + "\r");
                } 
            }
            
        }while (datos[2] != -1);         
            
    }

    //FIN DEL MAIN

    public static int[] Menu(String nombre[], int puntos[],long tiempo[],int numVeces[],int filasTabla[], int colsTabla[]) // muestra el menu y devuelve los datos obtenidos de filas y columnas elegidas
    {
        Scanner sc = new Scanner (System.in);
    int filas = 3;      // declaracion de variables que vamos a usar 
    int columnas = 4;
    int datos[] = new int[3];
    int menu;
    

            interfazMenu(filas, columnas);

            if (numVeces[0] == 0) // mostrar  solo una vez el mensaje de bienvenida
            {
                interfazTitulo();
                numVeces[0] = 1;
            }
            
            menu = sc.nextInt();
            
            while (menu != 1)
            {
                switch(menu)
                {
                    case 2 -> 
                        {
                            System.out.println("Cambiar número de FILAS de ("+ filas + ")" + " a :" );
                            filas = sc.nextInt();
                            
                            if (filas > 3 || filas == 0){
                                filas = 3;
                            }
                            menu = -1;
                        }
                    case 3 -> 
                        { 
                            System.out.println("Cambiar número de COLUMNAS de ("+ columnas + ")" + " a :" );
                            columnas = sc.nextInt();
                            
                            if (columnas > 4 || columnas == 0){
                                columnas = 4;
                            }
                            
                            menu = -1;
                        }
                    case 4 -> //tabla puntuaciones
                        { 
                            while (menu != -1)
                            {
                                externo.limpiarConsola();
                                externo.tablaPuntuaciones(nombre,puntos,tiempo,filasTabla,colsTabla);
                                menu = sc.nextInt();
                            }
                        }
                    case 5 -> //salir
                        {
                            System.out.println(" •._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.•• ");
                            System.out.println();
                            System.out.println("|>> (っ◔◡◔)っ ♥ Ha sido un placer! Juega pronto..! ♥  <<|");
                            System.out.println();
                            System.out.println(" •._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.•• ");
                            System.out.println();

                            datos[2] = -1;
                            menu = 1;
                            sc.close();
                        }
                    case -1 -> //caso que sea salir
                        {
                            externo.limpiarConsola();
                            interfazMenu(filas,columnas);
                            menu = sc.nextInt();
                        }
                    default -> menu = -1;
                }
            }

            datos[0] = filas;
            datos[1] = columnas;
            
    return datos;
    }
    
    public static void interfazMenu(int filas, int columnas)
    {
        externo.limpiarConsola();
        System.out.println();   
        System.out.println(ANSI_BLUE+"    /"+ANSI_CYAN_BACKGROUND+ "_____________________________________________________/"+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   "+ "/ |"+ANSI_WHITE_BACKGROUND + "                                                 " +ANSI_BLACK_BACKGROUND+ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   | |" +ANSI_WHITE_BACKGROUND + ANSI_CYAN + "     1 . Comenzar a jugar !                      "+ ANSI_CYAN_BACKGROUND +ANSI_BLUE+"||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   | |" +ANSI_WHITE_BACKGROUND + "                                                 "+ ANSI_BLACK_BACKGROUND +ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE + "   | |"+ANSI_WHITE_BACKGROUND  + ANSI_BLACK +"        2 . Cambiar dificultad (FILAS) " + ANSI_RED + "(" + filas + ")       " + ANSI_BLACK_BACKGROUND+ANSI_BLUE+ ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   | |"+ANSI_WHITE_BACKGROUND + "                                                 " +ANSI_BLACK_BACKGROUND+ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE + "   | |" +ANSI_WHITE_BACKGROUND + ANSI_BLACK +"        3 . Cambiar dificultad (COLUMNAS) " + ANSI_RED + "(" + columnas + ")    " +ANSI_BLACK_BACKGROUND +ANSI_BLUE+ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   | |"+ANSI_WHITE_BACKGROUND + "                                                 " +ANSI_CYAN_BACKGROUND+"||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE +"   | |"+ANSI_WHITE_BACKGROUND+ ANSI_CYAN + "     4 . Tabla de puntuaciones                   " +ANSI_BLACK_BACKGROUND + ANSI_BLUE+ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   | |"+ANSI_WHITE_BACKGROUND + "                                                 " +ANSI_CYAN_BACKGROUND+"||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE + "   | |" + ANSI_WHITE_BACKGROUND+ ANSI_RED + "     5 . Salir                                   "+ ANSI_BLACK_BACKGROUND+ANSI_BLUE+ANSI_CYAN_BACKGROUND+ "||  "+ANSI_BLACK_BACKGROUND);
        System.out.println(ANSI_BLUE+"   | |"+ANSI_WHITE_BACKGROUND + "_________________________________________________" +ANSI_CYAN_BACKGROUND+"||  "+ANSI_BLACK_BACKGROUND);
        System.out.println("   |/_____________________________________________________/");
        System.out.println(ANSI_WHITE);
    }
    
    public static void  interfazTitulo()
    {
        String rainbow[] = new String [7];
        
        rainbow[0] = ANSI_BLUE;
        rainbow[1] = ANSI_GREEN;
        rainbow[2] = ANSI_YELLOW;
        rainbow[3] = ANSI_RED;
        rainbow[4] = ANSI_PURPLE;
        rainbow[5] = ANSI_CYAN;
        rainbow[6] = ANSI_WHITE;

        long tiempo1 =(System.currentTimeMillis());
        long contColores = 0;
        long tiempo2;
        
        while(contColores< 6)  
        {
            //System.out.print(rainbow[(int) contColores]+ " •._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.•• "+ "\r");
            //System.out.println();
            System.out.print("   "+ANSI_WHITE_BACKGROUND + rainbow[(int) contColores]+"|>> (っ◔◡◔)っ ♥ BIENVENIDO ♥ a \u5C3A\u0263\u1640\u049c\u0263\u1640 by 🅴 🅽 🆁 🅸 🆀 🆄 🅴 <<|" + ANSI_BLACK_BACKGROUND+ "\r");
            //System.out.println();
            //System.out.print(rainbow[(int) contColores]+ " •._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.••´¯``•.¸¸.•`•._.•• "+ "\r");
            
            tiempo2 =(System.currentTimeMillis());
            contColores = (int) (tiempo2 - tiempo1) / 1000;
        } 
        System.out.println(ANSI_WHITE);
        
        
    }

    public static void mostrarMatriz(String m[][],int puntos[],int contUsuario,String mostrarJugada[],String mostrarJugadaCols[],String jugadaDiaDerecha,String jugadaDiaIzquierda) //muestra una matriz de juego 5x5, que a su vez contiene la interfaz de los puntos y jugadas
    {
        int j = 0;
        int columnas = m[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = m.length;        //longitud de las filas
        String puntuaciones[] = new String[9];
        
        puntuaciones[8] =" " + ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugada[4]+ANSI_BLACK_BACKGROUND+ANSI_WHITE +"               1PR - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 0200 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[7] = "                   2PR - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 0400 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[6] =" " + ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugada[3]+ANSI_BLACK_BACKGROUND+ANSI_WHITE + "                3K - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 0800 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[5] = "                   STR - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 1000 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[4] =" " + ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugada[2]+ANSI_BLACK_BACKGROUND+ANSI_WHITE + "                FL - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 1400 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[3] = "                    FH - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 1800 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[2] =" " + ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugada[1]+ANSI_BLACK_BACKGROUND+ANSI_WHITE + "                4K - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 2000 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[1] = "                    SF - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 2400 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        puntuaciones[0] =" " + ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugada[0]+ANSI_BLACK_BACKGROUND+ANSI_WHITE +"                RF - " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK +" 2800 " + ANSI_BLACK_BACKGROUND + ANSI_WHITE;
        

        System.out.println("  "+ ANSI_WHITE_BACKGROUND+ANSI_BLACK+mostrarJugadaCols[0]+ ANSI_BLACK_BACKGROUND + ANSI_WHITE+"   "+ ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugadaCols[1] + ANSI_BLACK_BACKGROUND + ANSI_WHITE+"   "+ANSI_WHITE_BACKGROUND+ANSI_BLACK+mostrarJugadaCols[2] + ANSI_BLACK_BACKGROUND + ANSI_WHITE+"   " + ANSI_WHITE_BACKGROUND+ANSI_BLACK+mostrarJugadaCols[3]+ANSI_BLACK_BACKGROUND + ANSI_WHITE+"   "+ ANSI_WHITE_BACKGROUND+ANSI_BLACK+ mostrarJugadaCols[4]+ANSI_BLACK_BACKGROUND + ANSI_WHITE + "   " +ANSI_CYAN_BACKGROUND+ANSI_BLACK+ jugadaDiaDerecha + ANSI_BLACK_BACKGROUND + ANSI_WHITE);
        System.out.println("|-----|-----|-----|-----|-----|");
        
        int conInterfaz = 0;
        
        for (int i = 0; i < fila; i++ ) //muestra la interfaz de los puntos en partida
        {
            for (j = 0; j < columnas; j++)
            {
                System.out.print("| " + m[i][j] + " ");
            }
            System.out.println("|" + puntuaciones[conInterfaz]);
            
            if (conInterfaz < 7)
            {
                conInterfaz++;
            }

            
            if (conInterfaz < 8)
            {
                System.out.println("|-----|-----|-----|-----|-----|" +  puntuaciones[conInterfaz]);
                conInterfaz++;
            }
            
        }
        System.out.println("|-----|-----|-----|-----|-----|");
        System.out.println("                                "  + ANSI_CYAN_BACKGROUND+ANSI_BLACK+ jugadaDiaIzquierda + ANSI_BLACK_BACKGROUND + ANSI_WHITE);

        System.out.println("                                                 TOTAL : "+ ANSI_YELLOW + puntos[contUsuario] + ANSI_WHITE);
        System.out.println("                                                        "+ANSI_BLACK+ "------" + ANSI_WHITE);
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
