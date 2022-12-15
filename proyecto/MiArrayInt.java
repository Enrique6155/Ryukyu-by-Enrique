
import java.util.Arrays;
public class MiArrayInt
//herramientas que no están en la clase Arrays para usar con enteros
{
    public static int buscar(int v[], int elem)
    //busca un elemento en el vector v
    //si lo encuentra, devuelve la posición
    //si no lo encuentra, devuelve -1
    {
        int resul=-1; //suponemos que no está
        int elemento;

        for(int j=0; j<v.length; j++)
        {
            elemento = v[j];

            if(elem == elemento) //está!!!
            {
                resul = j;
                break; //no necesitamos comparar más
            }
        }

        return resul;
    }

    
    public static int[] insertar(int v[], int num)
    //inserta un nuevo elemento en el array pasado como argumento
    {
        v= Arrays.copyOf(v, v.length+1);
        v[v.length-1] = num;
        return v;
    }
    public static char[] insertar(char v[], char num)
    //inserta un nuevo elemento en el array pasado como argumento
    {
        v= Arrays.copyOf(v, v.length+1);
        v[v.length-1] = num;
        return v;
    }
    
    public static int[] eliminar(int[] arr, int indice) 
    {
        int[] result = new int[arr.length - 1];
        System.arraycopy(arr, 0, result, 0, indice);
        if (arr.length != indice) {
            System.arraycopy(arr, indice + 1, result, indice, arr.length - indice - 1);
        }
        return result;
    
    }

    public static int[] sinRepetidos(int t[])
    {
        int resul[];

        resul = new int[0];
        
        for(int i=0; i<t.length; i++) 
        {
            if(buscar(resul, t[i])==-1) //si no está...
            //insertamos en resul el nuevo elemento que hemos comprobado
            //que no está repetido
            {
                resul = Arrays.copyOf(resul, resul.length+1);
                resul[resul.length-1] = t[i];
            }
        }

        return resul;
    }

    public static void reverseProc(int input[])
    {
        int last = input.length - 1;
        int middle = input.length / 2;
        
        for (int i = 0; i <= middle; i++)
        {
            int temp = input[i];
            input[i] = input[last - i];
            input[last - i] = temp;
        }
    }

    public static int[] buscarTodos(int t[], int clave) // busca la clave que le pasemos en otro vector y devuelve las posiciones en las que se encuentra en un nuevo vector
    {
        int encontrados[] = new int[0];
        for (int i = 0; i < t.length; i++)
        {
            if ( t[i] == clave)
            {
                encontrados = MiArrayInt.insertar(encontrados, i);
            }
        }  
    return encontrados;   
    }
    public static int[] buscarTodos(char t[], char clave) // busca la clave que le pasemos en otro vector y devuelve las posiciones en las que se encuentra en un nuevo vector
    {
        int encontrados[] = new int[0];
        for (int i = 0; i < t.length; i++)
        {
            if ( t[i] == clave)
            {
                encontrados = MiArrayInt.insertar(encontrados, i);
            }
        }  
    return encontrados;   
    }
    public static int[] generarNumerosAleatoriosSinRepetir(int cantidad, int min, int max)    // cantidad de numeros aleatorios que queremos generar, cambiando este parámetro podemos hacer un algoritmo más general
    {
      int num;
      int cont; 
      int combinacion[]; // creación del array
      int i;
        combinacion = new int[cantidad]; // asignación de memoria del array 
      cont=0;
      while(cont < cantidad)
      {
         num = (int) Math.floor((Math.random()* ((max+1)-min)) + min); 
          for (i = 0;i < cont; i++)  //comparar num con todo lo que ha salido hasta ahora
            {
               if(combinacion[i] == num) 
                  {  
                    break; //salimos sin incrementar contador porque no guardamos nada en esa posicion
                  }
            }
            
         if (i == cont) //(i==6) significa que no hay coincidencias, guardamos numero 
           
            {
               combinacion[cont] = num;
               cont++;
            }
      } 
    return combinacion;
    }
    
    public static int[] desordenar(int t[]) //desordena y devuelve un vector pasado como argumento
    {
        int copiaTemporal[] = Arrays.copyOf(t, t.length);
        int random[] = MiArrayInt.generarNumerosAleatoriosSinRepetir(t.length, 0, t.length-1);  //guardamos la tabla devuelta en una nueva
        int tablafinal[] = new int[t.length];   //generamos la tabla que devolveremos
        for(int i = 0; i < t.length; i++)   //cambiamos los valores de posición acorde con los valores de la tabla random
        {
            tablafinal[i] = copiaTemporal[(random[i])];
        }
    return tablafinal;
    }
    
    public static void mostrarMatriz(int m[][]) //muestra una matriz pasada como argumento por pantalla
    {
        int j = 0;
        int columnas = m[0].length; //longitud de la columna que referencia la posicion 0 de la fila
        int fila = m.length;        //longitud de las filas
        for (int i = 0; i < fila; i++ )
        {
            for (j = 0; j < columnas; j++)
            {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }



}