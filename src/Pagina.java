package src;

public class Pagina {

    private int[] paginaArray;
	private int tamano;
	private int disponible;
	
	public Pagina(int paginaTamano ) {
		paginaArray = new int[paginaTamano];
		disponible = paginaTamano;
	}
	public int getValue(int index ) {
		return paginaArray[index];
	}
	
	public boolean putValue(int index, int value) {
		if(disponible>0){
			paginaArray[index/ tamano] = value;
			disponible -=1;
			return true;
		}

		else{
			return false;
		}
	}
    
}
