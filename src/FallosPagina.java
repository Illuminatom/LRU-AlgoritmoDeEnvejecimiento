package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FallosPagina {
    private TablaPaginas tablaPaginas;
    private List<Integer> paginas; //paginas que estan en la memoria RAM
    private int numeroPaginas;
    private int fallos;
    private String nombreArchivo;
    private File archivo;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private Scanner scanner;
    private BufferedReader bufferedReader;

    public FallosPagina() {
        //Aqui en el constructor se piden tanto el nombre del archivo como la cantidad de marcos de pagina
        scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo de referencias (Sin el .txt) ");
        nombreArchivo = scanner.next();
        archivo = new File("..\\LRU-AlgoritmoDeEnvejecimiento/data/"+ nombreArchivo +".txt");
        
        System.out.println("Ingrese el numero de marcos de pagina ");
        numeroPaginas = scanner.nextInt();
        paginas = new ArrayList<>(numeroPaginas);
        tablaPaginas = new TablaPaginas(numeroPaginas);
    }
    
    public void cargarReferencias() throws IOException{
        cargarPaginasIniciales();
        
        fileInputStream = new FileInputStream(archivo);
        inputStreamReader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        
        List<Integer> paginasPulso = new ArrayList<Integer>(Collections.nCopies(numeroPaginas, 0));
        
        //Cada que se lee un numero de paginas igual al que pedimos por parametro, todas las paginas envejecen

        int pulsoReloj = numeroPaginas;
        String linea;
        linea = bufferedReader.readLine();
        while(linea != null){
            System.out.println("LEYENDO LINEA : " +linea);
            int numPagina = 0;
            String[] partes = linea.split(",");
            if(!linea.contains("=")){
                numPagina = Integer.parseInt(partes[1]);
                pulsoReloj-=1;
            }

            if(pulsoReloj >= 0){
                // Si la tabla ya esta llena, aca toca tomar medidas para reemplazar alguna pagina teniendo en cuenta el Algoritmo
                if(!paginas.contains(numPagina)){
                    System.out.println("\nLa pagina no esta en memoria, debemos reemplazar alguna para ingresar esta nueva");
                    System.out.println("Se trato de acceder a la pagina "+numPagina+" pero esta no estaba en la tabla de paginas");
                    fallos +=1;
                    System.out.println("Las tablas en memoria son: "+paginas+"");
                    int indexPaginaReemplazar = tablaPaginas.darPaginaAReemplazar(paginas);
                    System.out.println("\nLa pagina que deberia reemplazarse es "+paginas.get(indexPaginaReemplazar)+"\n");

                    System.out.println();
                    tablaPaginas.reemplazarPagina(indexPaginaReemplazar, new AlgoritmoEnvejecimiento());
                    paginas.set(indexPaginaReemplazar, numPagina);
                }
                else if(!linea.contains("=")){
                    System.out.println("\nLa pagina esta en la tabla de paginas");
                    System.out.println("Se accedio a la pagina "+numPagina+"\n");


                    int posicionPagina = paginas.indexOf(numPagina);
                    paginasPulso.set(posicionPagina, 1);
                }
                //La linea solo se lee si no se ha acabado el pulso de reloj
                linea = bufferedReader.readLine();
            }
            else{
                System.out.println("+++++++++++++++++++++++++++\nHora de envejecer!! \n++++++++++++++++++++++++++++++++");
                System.out.println("Paginas usadas en este ciclo: ");

                for (int i = 0; i < paginasPulso.size(); i++) {
                    if(i < paginas.size() && paginasPulso.get(i)==1){
                        System.out.println(paginas.get(i)+": Si");
                    }
                    else if(i < paginas.size()){
                        System.out.println(paginas.get(i)+": No");
                    }
                }

                tablaPaginas.envejecerPaginas(paginasPulso);
                
                paginasPulso = new ArrayList<Integer>(Collections.nCopies(numeroPaginas, 0));
                pulsoReloj = numeroPaginas;
            }
        }
        
        System.out.println("EL NUMERO DE FALLOS DE PAGINA DURANTE LA EJECUCION FUE DE "+fallos);
        fileInputStream.close();
        inputStreamReader.close();
        bufferedReader.close();
    }
    
    public void cargarPaginasIniciales() throws NumberFormatException, IOException{
        fileInputStream = new FileInputStream(archivo);
        inputStreamReader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);

        String linea;
        while((linea = bufferedReader.readLine()) != null){
            //Aca lo que se hace es que se agregan paginas a la tabla de paginas mientras tenga cupo
            int numPagina = 0;
            String[] partes = linea.split(",");
            if(!linea.contains("=")){
                numPagina = Integer.parseInt(partes[1]);
            }
            if(!linea.contains("=") && tablaPaginas.getTablaPaginas().size()<numeroPaginas && !paginas.contains(numPagina)){
                System.out.println("Se cargo la pagina "+numPagina+" a la tabla de paginas\n");
                AlgoritmoEnvejecimiento pagina = new AlgoritmoEnvejecimiento();
                tablaPaginas.añadirPagina(pagina);

                paginas.add(numPagina);
            }
        }

        fileInputStream.close();
        inputStreamReader.close();
        bufferedReader.close();
    }

}