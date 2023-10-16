package src;

import java.util.HashMap;
import java.util.Map;

public class ArrayTabla {
    
    private Map<String, int[]> tabla;
	
	public ArrayTabla() {
		tabla = new HashMap<String, int[]>();
	}
	
	public int[] getDireccion(String key) {
		return tabla.get(key);
	}

	public void agregarDireccion(int npagina, int ndesplazamiento, String elemento) {

		int[] values = new int[]{npagina,ndesplazamiento};
		tabla.put(elemento, values);

	}

	public Map<String, int[]> getTabla() {
		return tabla;
	}
}
