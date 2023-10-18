package src;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static FallosPagina fallosPagina;
    private static SegundoGenerador generador;

    public static void main(String[] args) throws IOException {
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);
        while (opcion != 0) {
            System.out.println("BIENVENIDO: SELECCIONE UNA OPCION");
            System.out.println("0. Parar la ejecución");
            System.out.println("1. Generar un nuevo archivo de referencias");
            System.out.println("2. Leer el archivo de referencias y calcular fallos de página");
            System.out.print("Ingrese su opcion : ");
            opcion = scanner.nextInt();

            System.out.println("Eligio la opcion "+opcion);
            if (opcion == 0) {
                System.out.println("Ejecución detenida. ¡Adios!");
            } 
            else if (opcion == 1) {
                generador = new SegundoGenerador();
                generador.generarArchivo();
            } 
            else if (opcion == 2) {
                fallosPagina = new FallosPagina();
                fallosPagina.cargarReferencias();
            }
            else {
                System.out.println("Seleccione una opción valida (0, 1 o 2).");
            }
        }

        scanner.close();
    }
}
