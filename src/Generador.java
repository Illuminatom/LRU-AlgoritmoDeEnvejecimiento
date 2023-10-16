package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Generador {
    private static int TP;
    private static int NF;
    private static int NC1;
    private static int NC2;
    private static int NR;
    private static int NP;
    private static int mat1[][];
    private static int mat2[][];
    private static int mat3[][];
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tamaño de pagina (en bytes): ");
        TP = scanner.nextInt();

        System.out.println("Ingrese el numero de filas de la primera matriz (NF): ");
        NF = scanner.nextInt();

        System.out.println("Ingrese el numero de columnas de la primera matriz (NC1): ");
        NC1 = scanner.nextInt();

        System.out.println("Ingrese el numero de columnas de la segunda matriz (NC2): ");
        NC2 = scanner.nextInt();        
        
        
        mat1 = generarMatrizAleatoria(NF, NC1);
        mat2 = generarMatrizAleatoria(NC1, NC2);
        mat3 = new int[NF][NC2];
        
        NR = (NF*NC1) + (NC1*NC2) + (NF*NC2);
        NP = (NR*4)/TP;

        multiplicar_matrices(NF, NC1, NC2);
        System.out.println("Matriz 1");
        imprimirMatriz(mat1);
        System.out.println("-------------\n");
        System.out.println("Matriz 2");
        imprimirMatriz(mat2);
        System.out.println("-------------\n");
        System.out.println("Matriz 3 (Matriz 1 * Matriz 2)");
        imprimirMatriz(mat3);

        String path = "..\\LRU-AlgoritmoDeEnvejecimiento/data/references.txt";        
        File archivo = new File(path);

        try {
            fileWriter = new FileWriter(archivo);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("TP= " + TP);
            bufferedWriter.newLine();
            bufferedWriter.write("NF= " + NF);
            bufferedWriter.newLine();
            bufferedWriter.write("NC1= " + NC1);
            bufferedWriter.newLine();
            bufferedWriter.write("NC2= " + NC2);
            bufferedWriter.newLine();
            bufferedWriter.write("NR= " + NR);
            bufferedWriter.newLine();
            bufferedWriter.write("NP= " + NP);
            bufferedWriter.newLine();
            generarReferencias(mat1, mat2, mat3, bufferedWriter);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public static int[][] generarMatrizAleatoria(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        Random random = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(10);
            }
        }

        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void multiplicar_matrices(int nf1, int nc1, int nc2){
        for (int i = 0; i < nf1; i++) {
            for (int j = 0; j < nc2; j++) {
                int acum = 0;
                for (int k = 0; k < nc1; k++) {
                    acum += mat1[i][k] * mat2[k][j];
                }
                mat3[i][j] = acum;
            }
        }
    }

    public static void generarReferencias(int[][] matriz1, int[][] matriz2, int[][] matriz3, BufferedWriter bufferedWriter) throws IOException {
        Random random = new Random();
        int contadorM1 = 0;
        int contadorM2 = 0;
        int contadorM3 = 0;
        
        for (int ref = 0; ref < NR; ref++) {
            Boolean control = true;
            
            while(control){
                int matrizAleatoria = random.nextInt(3) + 1;  
                if(matrizAleatoria == 1 && contadorM1 < NF*NC1){
                    int fila = contadorM1/NC1 + 1;
                    if(contadorM1%NC1 == 0){
                        fila = contadorM1/NC1;
                    }

                    int columna = contadorM1 % NC1 + 1;

                    String referencia = "[" +matrizAleatoria+"-"+fila+"-"+columna+ "]";
                    int paginaVirtual = ((fila*NC1 + columna)*4)/TP;
                    int desplazamiento = ((fila*NC1 + columna)*4)%TP;
                    referencia += ", " +paginaVirtual+", "+ desplazamiento;
                    bufferedWriter.write(referencia);
                    bufferedWriter.newLine();
                    contadorM1 +=1;

                    if (contadorM1 >= NF * NC1) {
                        control = false;
                    }
                }

                else if(matrizAleatoria == 2 && contadorM2 < NC1*NC2){
                    int fila = contadorM2/NC2;
                    if(contadorM2%NC2 == 0){
                        fila = contadorM2/NC2;
                    }

                    int columna = contadorM2 % NC1 + 1;

                    String referencia = "[" +matrizAleatoria+"-"+fila+"-"+columna+ "]";
                    int paginaVirtual = ((fila*NC2 + columna)*4)/TP;
                    int desplazamiento = ((fila*NC2 + columna)*4)%TP;
                    referencia += ", " +paginaVirtual+", "+ desplazamiento;
                    bufferedWriter.write(referencia);
                    bufferedWriter.newLine();
                    contadorM2 +=1;

                    if (contadorM2 >= NC1 * NC2) {
                        control = false;
                    }
                }
                else if (matrizAleatoria == 3 && contadorM3 < NF * NC2) {
                    int fila = contadorM3 / NC2;
                    if (contadorM3 % NC2 == 0) {
                        fila = contadorM3 / NC2;
                    }
                
                    int columna = contadorM3 % NC2 + 1;
                
                    String referencia = "[" + matrizAleatoria + "-" + fila + "-" + columna + "]";
                    int paginaVirtual = ((fila * NC2 + columna) * 4) / TP;
                    int desplazamiento = ((fila * NC2 + columna) * 4) % TP;
                    referencia += ", " + paginaVirtual + ", " + desplazamiento;
                    bufferedWriter.write(referencia);
                    bufferedWriter.newLine();
                    contadorM3 += 1;

                    if (contadorM3 >= NF * NC2) {
                        control = false;
                    }
                }

                control = (contadorM1 < NF * NC1) || (contadorM2 < NC1 * NC2) || (contadorM3 < NF * NC2);
                System.out.println("Hey");
            }   
            
        }
    }
    
}
