package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static FallosPagina fallosPagina;
    private static SegundoGenerador generador;
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
    public static boolean fin = false;

    public static void main(String[] args) throws IOException {
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);
        while (opcion != 0) {
            System.out.println("BIENVENIDO: SELECCIONE UNA OPCION");
            System.out.println("0. Parar la ejecución");
            System.out.println("1. Generar un nuevo archivo de referencias");
            System.out.println("2. Leer el archivo de referencias y calcular fallos de página");
            System.out.println("3. Leer el archivo de referencias y calcular fallos de página con Threads");
            System.out.print("Ingrese su opcion : ");
            opcion = scanner.nextInt();

            System.out.println("Eligio la opcion " + opcion);
            if (opcion == 0) {
                System.out.println("Ejecución detenida. ¡Adios!");
            } else if (opcion == 1) {
                generador = new SegundoGenerador();
                generador.generarArchivo();
            } else if (opcion == 2) {
                fallosPagina = new FallosPagina();
                fallosPagina.cargarReferencias();
            } else if (opcion == 3) {
                System.out.println("Ingrese el nombre del archivo de referencias (Sin el .txt): ");
                scanner.nextLine();
                nombreArchivo = scanner.nextLine();
                archivo = new File("..\\LRU-AlgoritmoDeEnvejecimiento/data/" + nombreArchivo + ".txt");
                System.out.println("Ingrese el numero de marcos de pagina: ");
                numeroPaginas = scanner.nextInt();
                paginas = new ArrayList<>(numeroPaginas);
                tablaPaginas = new TablaPaginas(numeroPaginas);
                paginasUsadas = new ArrayList<Integer>();
                T1 t1 = new T1();
                t1.start();
                T2 t2 = new T2();
                t2.start();
            } else {
                System.out.println("Seleccione una opción valida (0, 1, 2 o 3).");
            }
        }

        scanner.close();
    }

}
