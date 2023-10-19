package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T1 extends Thread {


    public void run(){

        String linea;
        try {
            Main.fileInputStream = new FileInputStream(Main.archivo);
            Main.inputStreamReader = new InputStreamReader(Main.fileInputStream);
            Main.bufferedReader = new BufferedReader(Main.inputStreamReader);
            linea = Main.bufferedReader.readLine();
            while(linea != null){
                System.out.println("LEYENDO LINEA : "+linea);
                int numPagina = 0;
                String[] partes = linea.split(",");
                if(!linea.contains("=")){
                    partes = linea.split(",");
                    numPagina = Integer.parseInt(partes[1]);
                    if(!Main.paginas.contains(numPagina) && Main.paginas.size() < Main.numeroPaginas){
                        Main.paginas.add(numPagina);
                        Main.tablaPaginas.añadirPagina(new AlgoritmoEnvejecimiento());
                    }
                    else if(!Main.paginas.contains(numPagina) && Main.paginas.size() == Main.numeroPaginas){
                        Main.paginas.add(numPagina);
                        Main.fallos++;
                        int indexPaginaReemplazar = Main.tablaPaginas.darPaginaAReemplazar(Main.paginas);
                        Main.tablaPaginas.reemplazarPagina(indexPaginaReemplazar, new AlgoritmoEnvejecimiento());
                        Main.paginas.set(indexPaginaReemplazar, numPagina);
                        System.out.println("FALLO DE PAGINA");
                    }
                    else{
                        Main.tablaPaginas.añadirPagina(new AlgoritmoEnvejecimiento());
                    }
                    System.out.println("PAGINAS EN RAM : "+Main.paginas.size());
                    Main.paginasUsadas.add(numPagina);
                    
                }
                sleep(2);
                linea = Main.bufferedReader.readLine();
            }
            Main.fin = true;
            Main.bufferedReader.close();
            Main.inputStreamReader.close();
            Main.fileInputStream.close();
            System.out.println("FALLOS DE PAGINA : "+Main.fallos);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        




    }
    
}
