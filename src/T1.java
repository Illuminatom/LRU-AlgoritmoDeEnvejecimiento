package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class T1 extends Thread {

    public static TablaPaginas tablaPaginas;
    public static List<Integer> paginas; // paginas que estan en la memoria RAM
    public static int numeroPaginas;
    public static int fallos;
    public static String nombreArchivo;
    public static File archivo;
    public static FileInputStream fileInputStream;
    public static InputStreamReader inputStreamReader;
    public static Scanner scanner;
    public static BufferedReader bufferedReader;
    public static List<Integer> paginasUsadas;
    public static boolean fin;


    public void run(){

        fallos = 0;
        String linea;
        try {
            fileInputStream = new FileInputStream(archivo);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            linea = bufferedReader.readLine();
            while(linea != null){
                System.out.println("LEYENDO LINEA : "+linea);
                String[] partes = linea.split(",");
                if(!linea.contains("=")){
                    partes = linea.split(",");
                    String numPaginaString = partes[1].trim(); 
                    Integer numPagina = Integer.parseInt(numPaginaString);
                    if(!paginas.contains(numPagina) && paginas.size() < numeroPaginas){
                        tablaPaginas.añadirPagina(new AlgoritmoEnvejecimiento());
                        paginas.add(numPagina);
                        
                    }
                    else if(!paginas.contains(numPagina) && paginas.size() >= numeroPaginas){
                        T1.fallos++;
                        int indexPaginaReemplazar = tablaPaginas.darPaginaAReemplazar(paginas);
                        tablaPaginas.reemplazarPagina(indexPaginaReemplazar, new AlgoritmoEnvejecimiento());
                        paginas.set(indexPaginaReemplazar, numPagina);
                        System.out.println("FALLO DE PAGINA");
                    }
                    else{
                        tablaPaginas.añadirPagina(new AlgoritmoEnvejecimiento());
                    }
                    System.out.println("PAGINAS EN RAM : "+paginas.size());
                    paginasUsadas.add(numPagina);
                    
                }
                sleep(2);
                linea = bufferedReader.readLine();
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finalizar();

        try {
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void finalizar(){
        fin = true;
    }
    
}
