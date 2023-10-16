package src;

import java.util.ArrayList;

public class Tabla {
    
    private ArrayList<ArrayList<String>> tablePag = new ArrayList<ArrayList<String>>(); 

    private ArrayTabla TablaPagina = new ArrayTabla();

    public void CrearPaginas(int nfilas,int ncolumn,int tentero,int tpagina){
        int cantidadElementos = tpagina/tentero;
        int tmatriz = nfilas*ncolumn;
        ArrayList<String> pag = new ArrayList<String>();
        int nfila = 0;
        int ielemento = 0;
        int imaximo = 0;
        int iMatriz = 1;
        int ipagina = 0;
        while((ielemento<cantidadElementos && iMatriz < 4)){
            if(nfila<nfilas){
                for(int ncolumna=0;ncolumna<ncolumn;ncolumna++){
                    String direccion = "[" + iMatriz + "," + nfila + "," + ncolumna + "]";

                    TablaPagina.agregarDireccion(ipagina, ielemento*4, direccion);

                    ielemento+=1;
                    imaximo +=1;

                    pag.add(direccion);

                    if(ielemento==cantidadElementos){
                        ielemento = 0;
                        ipagina +=1 ;
                        tablePag.add(pag);
                        pag = new ArrayList<String>();
                    }

                    if(imaximo == tmatriz*3 && cantidadElementos!=tmatriz){
                        tablePag.add(pag);
                        pag = new ArrayList<String>();
                    }

                }

                nfila +=1;

                if(nfila==nfilas){
                    nfila = 0;
                    iMatriz +=1;
                }


            }

        }
        
    }

    public ArrayList<ArrayList<String>> getTablePag() {
        return tablePag;
    }

    public ArrayTabla getTablaPagina() {
        return TablaPagina;
    }
    

}
