package src;

public class AlgoritmoEnvejecimiento {

    public int[] registros;

    public AlgoritmoEnvejecimiento() {
        this.registros = new int[8];
        for(int i= 0; i<8; i++){
            this.registros[i] = 0;
        }
    }

    public void envejecer(boolean usado) {
        for (int i = 0; i < 8; i++) {
            this.registros[i] = this.registros[i] >> 1;
        }

        if (usado) {
            this.registros[0] = 1;
        }
        else{
            this.registros[0] = 0;
        }
    }

    public int obtenerRegistro() {
        int indice = 0;
        for (int i = 1; i < 8; i++) {
            indice += this.registros[i] * Math.pow(2, i - 1);
        }
        return indice;
    }


}
    

