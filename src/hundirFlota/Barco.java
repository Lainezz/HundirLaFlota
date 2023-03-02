package hundirFlota;

import java.util.List;

/**
 * La clase se describe en esta parte
 * @author diego
 * @version 1.0
 *
 */
public class Barco {

	/**
	 * Atributos de clase
	 */
	private String nombre;
	private char ini;
	private int tamanio;
	
	/**
	 * Lista con las iniciales admitidas en el juego
	 */
	private char[] listaAdmitidos = new char[]{'L','B','Z','P'};

	/**
	 * Constructor principal de clase
	 * @param ini
	 */
	public Barco(char ini) {
		setIni(ini);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(char ini) {
		switch (ini) {
		case 'L': {
			this.nombre = "Lancha";
			break;
		}
		case 'B': {
			this.nombre = "Buque";		
			break;
		}
		case 'Z': {
			this.nombre = "Acorazado";
			break;
		}
		case 'P': {
			this.nombre = "Porta-Aviones";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + ini);
		}
	}

	public char getIni() {
		return ini;
	}

	public void setIni(char ini) throws IllegalArgumentException{
		for (char c : listaAdmitidos) {
			if(c == ini) {
				this.ini = ini;
				break;
			}
		}
		if(this.ini != ini) throw new IllegalArgumentException("Inicial no admitida");
		
		//Si se ha establecido la inicial correctamente, establecemos el nombre y el tamanio del Barco
		setNombre(ini);
		setTamanio(ini);
	}

	public int getTamanio() {
		return tamanio;
	}

	private void setTamanio(char ini) {
		switch (ini) {
		case 'L': {
			this.tamanio = 1;
			break;
		}
		case 'B': {
			this.tamanio = 3;		
			break;
		}
		case 'Z': {
			this.tamanio = 4;
			break;
		}
		case 'P': {
			this.tamanio = 5;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + ini);
		}
	}

}
