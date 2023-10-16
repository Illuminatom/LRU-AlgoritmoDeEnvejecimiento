package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SegundoGenerador {
    private static int TP;
    private static int NF;
    private static int NC1;
    private static int NC2;
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

        String path = "..\\LRU-AlgoritmoDeEnvejecimiento/data/references.txt";        

        try {
            generarReferencias(path, NF, NC1, NC2, TP);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        scanner.close();
    }


    public static void generarReferencias(String path,int NF, int NC1, int NC2, int TP) throws IOException {
        
        File archivo = new File(path);

        int tamanoMatrizA = (NF*NC1)*4;
        int tamanoMatrizB = (NC1*NC2)*4;
        int tamanoMatrizC = (NF*NC2)*4;

        int NR = NF*NC1*NC2+NC1*NC2*NF+NF*NC2;

        // number of pages to save the three matrices
        int nPaginasMatrizA = (tamanoMatrizA+TP-1) / TP;
        int nPaginasMatrizB = (tamanoMatrizB+TP-1) / TP;
        int nPaginasMatrizC = (tamanoMatrizC+TP-1) / TP;

        int NP = nPaginasMatrizA+nPaginasMatrizB+nPaginasMatrizC;
        System.out.print(nPaginasMatrizA);
        System.out.print(nPaginasMatrizB);
        System.out.print(nPaginasMatrizC);


        try {
            fileWriter = new FileWriter(archivo);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("TP= " + String.valueOf(TP));
            bufferedWriter.newLine();
            bufferedWriter.write("NF= " + String.valueOf(NF));
            bufferedWriter.newLine();
            bufferedWriter.write("NC1= " + String.valueOf(NC1));
            bufferedWriter.newLine();
            bufferedWriter.write("NC2= " + String.valueOf(NC2));
            bufferedWriter.newLine();
            bufferedWriter.write("NR= " + String.valueOf(NR));
            bufferedWriter.newLine();
            bufferedWriter.write("NP= " + String.valueOf(NP));
            bufferedWriter.newLine();

            for (int i = 0; i < NF; i++) {
                for (int j = 0; j < NC2; j++) {
                    for (int k = 0; k < NC1; k++) {
                        
                        int direccion = (i * NC1 + k) * 4;
                        int nPagina = direccion / TP;
                        int desplazamiento = direccion % TP;
                        bufferedWriter.write("[A-" + i + "-" + k + "]," + nPagina + "," + desplazamiento);
                        bufferedWriter.newLine();
                        
                        direccion = (k * NC2 + j) * 4;
                        nPagina = direccion / TP + nPaginasMatrizA;
                        desplazamiento = direccion % TP;
                        bufferedWriter.write("[B-" + k + "-" + j + "]," + nPagina + "," + desplazamiento);
                        bufferedWriter.newLine();
                        
                    }
                    
                    int direccion = (i * NC2 + j) * 4;
                    int page = direccion / TP + nPaginasMatrizA + nPaginasMatrizB;
                    int desplazamiento = direccion % TP;
                    bufferedWriter.write("[C-" + i + "-" + j + "]," + page + "," + desplazamiento);
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
            fileWriter.close();
            
        } catch (Exception e) {
            System.out.println("Error al generar el archivo");
            e.printStackTrace();
        }

    }
    
}
