package hundirFlota;

/**
 * La clase se describe en esta parte
 * @author diego
 * @version 1.0
 *
 */
public class Barco {

	/**
	 * descripcion para el atributo nombre
	 * @see #getNombre()
	 * @see #setNombre(String)
	 */
	private String nombre;
	private char ini;
	private int tamanio;

	public Barco(String nombre, char ini, int tamanio) {
		this.nombre = nombre;
		this.ini = ini;
		this.tamanio = tamanio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getIni() {
		return ini;
	}

	public void setIni(char ini) {
		this.ini = ini;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

}
