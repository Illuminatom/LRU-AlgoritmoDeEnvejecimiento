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
        int n = registros.length;
        int[] resultado = new int[n];
    
        for (int i = 1; i < n; i++) {
            resultado[i] = registros[i - 1];
        }

        if (usado) {
            resultado[0] = 1;
        }
        else{
            resultado[0] = 0;
        }
        registros = resultado;
    }
}