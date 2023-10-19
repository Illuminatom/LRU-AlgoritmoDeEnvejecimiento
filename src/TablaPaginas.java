package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TablaPaginas {
    private ArrayList<AlgoritmoEnvejecimiento> tablaPaginas;
    private ArrayList<Integer> bitsR;

    public TablaPaginas(int marcos) {
        tablaPaginas = new ArrayList<AlgoritmoEnvejecimiento>(marcos);
        bitsR = new ArrayList<Integer>(Collections.nCopies(marcos, 0));
    }    

    public void añadirPagina(AlgoritmoEnvejecimiento pagina){
        tablaPaginas.add(pagina);
    }

    public void actualizarBitsR(){
        for (int i = 0; i < tablaPaginas.size(); i++) {
            bitsR.set(i, tablaPaginas.get(i).registros[0]);
        }
    }

    public ArrayList<AlgoritmoEnvejecimiento> getTablaPaginas(){
        return tablaPaginas;
    }

    //Envejece todas las paginas en la tabla de paginas
    public void envejecerPaginas(List<Integer> paginasPulso) {
        for (int i = 0; i < tablaPaginas.size(); i++) {
            if (paginasPulso.get(i) == 1) {
                tablaPaginas.get(i).envejecer(true);
            } else {
                tablaPaginas.get(i).envejecer(false);
            }
        }
        actualizarBitsR();
    }

    public ArrayList<Integer> getBitsR() {
        return bitsR;
    }

    public int darPaginaAReemplazar(List<Integer> paginas){
        List<Integer> numerosPaginas = new ArrayList<Integer>(tablaPaginas.size()-1);
        int j = 0;
        for (AlgoritmoEnvejecimiento pagina: tablaPaginas) {
            System.out.print("\nBits de la pagina "+paginas.get(j)+" : ");
            for (int bit : pagina.registros) System.out.print(bit+",");

            String binarioPagina = "";
            for (Integer bit : pagina.registros) {
                binarioPagina+=bit;
            }
            int numeroPagina = Integer.parseInt(binarioPagina);
            numerosPaginas.add(numeroPagina);
            j++;
        }

        int indiceMinimo = 0;
        int minimo = numerosPaginas.get(0);
        for (int i = 1; i < numerosPaginas.size(); i++) {
            if (numerosPaginas.get(i) < minimo) {
                minimo = numerosPaginas.get(i);
                indiceMinimo = i;
            }
        }

        return indiceMinimo;
    }

    public void reemplazarPagina(int index, AlgoritmoEnvejecimiento pagina){
        pagina.envejecer(true);
        tablaPaginas.set(index, pagina);
    }
}
