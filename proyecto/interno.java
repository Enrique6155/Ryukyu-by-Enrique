
import java.util.Arrays;

public class interno 
{
    public static int[] crearBaraja() // funciona - CREA LA BARAJA 
    {
        int baraja[] = new int[53];
        for (int i = 1; i < 53;i++)
        {
            baraja[i] = i;
        }
    return baraja;
    }

    public static int[][] verCartas(int filas, int cols, int[] baraja) //CREA UNA MATRIZ SEGUN LA DIFICULTAD ELEGIDA QUE RELLENA CON NUMEROS(CARTAS) DE LA BARAJA Y DEVUELVE ESTA MATRIZ 
    {
        int tablero[][] = new int[filas][cols]; 
        int aleatorio;
        int carta;
        int longitud = baraja.length-2;
        
        for (int i = 0; i < filas; i++ ) //rellena la matriz con cartas de la baraja y a su vez los elmina de esta
        {
            for (int j = 0; j < cols; j++) 
            {
                aleatorio = (int) (Math.random()*longitud) + 1; //(NUMS = 1-52) --> generamos un random para determinar la posicion de la que vamos a sacar la carta del vector baraja
                carta = baraja[aleatorio];  
                
                baraja = MiArrayInt.eliminar(baraja, aleatorio);        //eliminamos la carta escogida puesto que no se pueden repetir
                
                longitud--;                                             //disminuimos la longitud ya que si no nos saldriamos de rango generando randoms
                tablero[i][j] = carta;                                 //rellenamos la matriz con la carta escogida
            }
        }
        //System.out.println(Arrays.toString(baraja));
    return tablero;
    }

    public static int[] actualizaBaraja(int tablero[][], int barajaBorrada[])  // devuelve la baraja actualizada quitando las cartas que ya se encuentran en la matriz
    {
        int j = 0;
        int columnas = tablero[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = tablero.length;        //longitud de las filas
        //int barajaBorrada[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52};
        int clave;
        int encontrado;

        for (int i = 0; i < fila; i++ )
        {
            for (j = 0; j < columnas; j++) //recorremos los numeros de la matriz comparandolos con una baraja entera, y eliminamos los numeros que esten en la matriz de la baraja.
            {
                clave = tablero[i][j];  

                encontrado = MiArrayInt.buscar(barajaBorrada,clave);
                
                if(encontrado != -1)
                {
                    barajaBorrada = MiArrayInt.eliminar(barajaBorrada, encontrado);
                }
            }
        }
    return barajaBorrada;
    }

    public static int pedirCarta(int tablero[][], int pos) //devuelve el numero de la carta en la columna que le indiquemos.
    {
        int carta;
        //int columnas = tablero[0].length;
        int fila = tablero.length; 

        carta = tablero[fila-1][pos];
    
        return carta;
    }

    public static int[][] bajarPosiciones(int pos, int tablero[][],int vbaraja[],int filasDificultad, int colsDificultad,int montones[]) // baja los numeros una fila en la columna que hemos pedido la carta y devuelve el tablero con un nuevo numero insertado de la baraja.
    {
        int fila = tablero.length;
        int baraja[] = actualizaBaraja(tablero,vbaraja);
        int longitud = baraja.length-2;
        int carta;
        int aleatorio;
        
        if (montones[pos] != 0) // si quedan cartas en ese monton
        {
            if (filasDificultad != 1) // si el numero de filas es solo uno no podemos bajar ninguna posición, por lo que omitimos este paso
            {
                for (int i = 0; i < fila; i++ ) // baja las cartas de posicion
                {
                    tablero[fila-1][pos] = tablero[fila-2][pos];
                    fila--;
                }
            }

            // insertamos un nuevo numero en la primera fila de la columna que estamos editando
            aleatorio = (int) (Math.random()*longitud) + 1; //(NUMS = 1-52) --> generamos un random para determinar la posicion de la que vamos a sacar la carta del vector baraja
                carta = baraja[aleatorio];                    //guardamos en la carta la que hemos cogido aleatoriamente de la baraja
            
            baraja = MiArrayInt.eliminar(baraja, aleatorio);            //eliminamos la carta escogida puesto que no se pueden repetir
            longitud--;                                             //disminuimos la longitud ya que si no nos saldriamos de rango generando randoms
            
            tablero[fila-1][pos] = carta;                               //insertamos esa carta encima de las que hemos bajado
            montones[pos]--;
        }

        if(montones[pos] < 3) // si el numero de montones es inferior a 3 significa que tienen que empezar a mostrarse espacios en blanco en esa columna
            {
                tablero[fila-1][pos] = 0;
            }
        
    return tablero;
    }

    public static int[][] generarTableroJuego()
    {
        int tablero5x5[][] = new int [5][5];
    return tablero5x5;
    }

    public static void tableroJuego(int tablero5x5[][], int carta,int col) //inserta una carta en la matriz 5x5
    {
        boolean insertada = false;  // controlamos si hemos podido insertar la carta en el sitio que queriamos
        int fila = tablero5x5.length-1;;
        
        while (insertada == false) // mientras que no podamos insertar la carta seguimos en el bucle decrementando las filas, es decir subiendo posiciones hasta encontrar una libre
        {
            if (tablero5x5[fila][col] == 0)  // si es 0 significa que aun no tiene ninguna carta asignada, es decir está "vacío"
            {
                tablero5x5[fila][col] = carta;      //insertamos la carta en la columna que nos dice el usuario
                fila = tablero5x5.length-1;  // reiniciamos las filas
                insertada = true;   // salimos del bucle
            }
                else        //decrementamos las filas para subir de posicion en busca de un hueco libre
                {
                    fila--;
                }   
            
            if (fila == - 1)  // con esto controlamos que la columna está llena, y no insertamos nada
            {
                insertada = true;
            }
        }
    }

}
