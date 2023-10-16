package src;

public class MarcoPagina{
    private AlgoritmoEnvejecimiento[] AlgoritmoMarcoArray;
    private int[] marcoPag;
    private int numMarco;
    private int frameDisponible;
    private int[] contadorCiclo;
    private int fallos;
    private boolean fin;

    public MarcoPagina (int oNumMarco) {

        this.AlgoritmoMarcoArray = new AlgoritmoEnvejecimiento[oNumMarco];
        this.marcoPag = new int[oNumMarco];
        this.numMarco = oNumMarco;
        this.fallos = 0;
        this.fin = false;

        for (int i = 0; i < oNumMarco; i++) {
            marcoPag[i] = -1;
            AlgoritmoMarcoArray[i] = new AlgoritmoEnvejecimiento();
        }

        contadorCiclo = new int[oNumMarco];

        for (int i = 0; i < oNumMarco; i++) {
            contadorCiclo[i] = 0;
        }
    }

    public synchronized void insertarPagina(int nPagina) {

        fallos += 1;

        int posicionVacio = -1;

        for (int i = 0; i < marcoPag.length; i++) {
            if (marcoPag[i] == -1) {
                posicionVacio = i;
                break;
            }
        }

        if (posicionVacio != -1) {
            marcoPag[posicionVacio] = nPagina;
            AlgoritmoEnvejecimiento nuevoContador = new AlgoritmoEnvejecimiento();
            AlgoritmoMarcoArray[posicionVacio] = nuevoContador;
            contadorCiclo[posicionVacio] = 1;
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            marcoPag[frameDisponible] = nPagina;
            AlgoritmoEnvejecimiento nuevoContador = new AlgoritmoEnvejecimiento();
            AlgoritmoMarcoArray[frameDisponible] = nuevoContador;
            contadorCiclo[frameDisponible] = 1;
        }
    }

    public synchronized boolean inPagina(int nPagina) {

        for (int i = 0; i < numMarco; i++) {
            if (marcoPag[i] == nPagina) {
                contadorCiclo[i] = 1;
                return true;
            }
        }
        return false;
    }

    public synchronized void reiniciarContador() {

        for (int i = 0; i < numMarco; i++) {
            if (contadorCiclo[i] == 1) {
                AlgoritmoMarcoArray[i].envejecer(true);
            } else {
                AlgoritmoMarcoArray[i].envejecer(false);
            }

            contadorCiclo[i] = 0;

        }
    }

	public int calcularFrame()
	{
		int menor = 256;
		int menorframe = 0;
		for (int i =0;i<numMarco;i++)
		{
			if(AlgoritmoMarcoArray[i].obtenerRegistro()<=menor){
				menor = AlgoritmoMarcoArray[i].obtenerRegistro();
				menorframe = i;
			}
		}
		return menorframe;

	}

	public int getFallos() 
	{
		return fallos;
	}
	public AlgoritmoEnvejecimiento[] getAlgoritmoMarcoArray() {
		return AlgoritmoMarcoArray;
	}

	public int[] getMarcoPag() {
		return marcoPag;
	}
	public void setMarcoPag(int[] marcoPag) {
		this.marcoPag = marcoPag;
	}
	public int getNummarco() {
		return numMarco;
	}
	public void setNummarco(int nummarco) {
		this.numMarco = nummarco;
	}
	public int getFrameDisponible() {
		return frameDisponible;
	}

	public int[] getContadorCiclo() {
		return contadorCiclo;
	}

	public boolean isFin() {
		return fin;
	}

	public void setAlgoritmoMarcoArray(AlgoritmoEnvejecimiento[] AlgoritmoMarcoArray) {
		this.AlgoritmoMarcoArray = AlgoritmoMarcoArray;
	}

	public void setFrameDisponible(int frameDisponible) {
		this.frameDisponible = frameDisponible;
	}

	public void setContadorCiclo(int[] contadorCiclo) {
		this.contadorCiclo = contadorCiclo;
	}

	public void setFallos(int fallos) {
		this.fallos = fallos;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}
}