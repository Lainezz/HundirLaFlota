package hundirFlota;

import java.util.Random;

/**
 * Clase correspondiente a la creacion de un tablero de juego
 * @author diego
 * @version 1.0
 *
 */
public class Tablero {

	/**
	 * Numero de filas del tablero. Hardcodeado a 10.
	 */
	private final int NFILAS = 10;

	/**
	 * Numero de columnas del tablero. Hardcodeado a 10.
	 */
	private final int NCOLS = 10;

	/**
	 * Guion - Correspondiente a una casilla con agua, no disparada a�n
	 */
	private final char guion = (char) 45;

	/**
	 * Arroba - Correspondiente a una casilla donde se ha disparado y no ha tocado ning�n barco
	 */
	private final char arroba = (char) 64;

	/**
	 * Numero de posiciones totales que ocupan los barcos en el tablero
	 */
	private int posicionesBarcos;

	char[][] tablero;

	/**
	 * Constructor por defecto. Crea un tablero de NFILAS y NCOLUMNAS
	 * Setea las posiciones de barcos y llena el tablero vac�o.
	 * @see #posicionesBarcos
	 */
	public Tablero() {
		this.tablero = new char[NFILAS][NCOLS];
		setPosicionesBarcos(0);
		llenarTableroVacio();
	}

	/**
	 * Llena el tablero de juego con guiones, es decir, lo rellena vacio.
	 */
	public void llenarTableroVacio() {

		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[i].length; j++) {
				this.tablero[i][j] = guion;
			}
		}
	}

	/**
	 * Se realiza un disparo y comprueba si ha dado en el blanco o al agua.
	 * Actualiza {@link #posicionesBarcos} restandole 1 unidad.
	 * @param x - posicion x en tablero, corresponde con la columna del tablero.
	 * @param y - posicion y en el tablero, corresponde con la fila del tablero.
	 * @return si en la posicion indicada hay un barco, devuelve el char de ese barco, si no, devuelve un arroba.
	 */
	public char checkDisparo(int x, int y) {
		if(this.tablero[x][y] != guion) {
			System.out.println("Tocado");
			System.out.flush();
			setPosicionesBarcos(getPosicionesBarcos() - 1);
			return this.tablero[x][y];
		} else {
			System.out.println("Agua");
			System.out.flush();
			return arroba;
		}
	}

	/**
	 * Actualiza el tablero y pone en la posici�n x-y otra cosa
	 * @param x Corresponde con la columna del tablero
	 * @param y corresponde con la fila del tablero
	 * @param cosa - char con el que queremos rellenar la posici�n
	 */
	public void updateTablero(int x, int y, char cosa) {
		this.tablero[x][y] = cosa;
	}

	/**
	 * Metodo para colocar un barco en el tablero.
	 * El {@code Barco.class} puede ser de tipo {@code <L>, <B>, <Z> o <P>}
	 * @param barco Objeto de tipo {@link Barco#Barco(String, char, int)}
	 * @throws Exception lanza la excepci�n cuando se ha producido alg�n error {@link #imprimirError(String)}
	 */
	public void colocarBarco(Barco barco) throws Exception{

		if (barco.getIni() == 'L') {
			colocarL(barco);
		} else if (barco.getIni() == 'B' || barco.getIni() == 'Z') {
			colocarHorizontal(barco);
		} else if (barco.getIni() == 'P') {
			colocarVertical(barco);
		} else {
			imprimirError("Barco err�neo");
		}
	}

	/**
	 * Coloca una lancha dentro del tablero de juego
	 * @param lancha - Objeto de tipo barco que debe ser una lancha
	 */
	private void colocarL(Barco lancha) {
		Random rand = new Random();

		int x;
		int y;

		boolean colocado = false;
		while (!colocado) {
			x = rand.nextInt(9);
			y = rand.nextInt(9);

			if (comprobarSiLibre(x, y)) {
				this.tablero[x][y] = lancha.getIni();
				colocado = true;
			}
		}

	}


	/**
	 * M�todo que recibe por par�metros un objeto de tipo barco y lo coloca horizontalmente en el tablero
	 * @param barco
	 */
	private void colocarHorizontal(Barco barco) {
		Random rand = new Random();

		int x, auxX;
		int y, auxY;

		boolean colocado = false;
		boolean libre;
		while (!colocado) {
			//Variable para controlar si las posiciones estan libres
			libre = true;

			//Variables de posicion
			x = rand.nextInt(9);
			y = rand.nextInt(9);

			//Ajustamos y para que no nos de excepcion
			while(y+barco.getTamanio()>10) {
				y--;
			}

			//Variables auxiliares que usaremos para comprobar si las posiciones estan libres
			auxX = x;
			auxY = y;

			//Primero deberiamos comprobar si las posiciones para insertar el barco estan libres
			for(int i=0; i<barco.getTamanio(); i++) {
				if (comprobarSiLibre(auxX, auxY)) {
					//Como nos movemos horizontalmente, incrementamos Y
					auxY++;
				} else {
					libre = false;
					break;
				}
			}

			//Si las posiciones est�n libres, colocamos el barco sin miedo
			if(libre) {
				for(int i=0; i<barco.getTamanio(); i++) {
					this.tablero[x][y] = barco.getIni();
					y++;
				}
				colocado = true;
			}
		}
		System.out.flush();
	}

	private void colocarVertical(Barco barco) {
		Random rand = new Random();

		int x, auxX;
		int y, auxY;

		boolean colocado = false;
		boolean libre;
		while (!colocado) {
			//Variable para controlar si las posiciones estan libres
			libre = true;

			//Variables de posicion
			x = rand.nextInt(9);
			y = rand.nextInt(9);

			//Ajustamos y para que no nos de excepcion
			while(x+barco.getTamanio()>10) {
				x--;
			}

			//Variables auxiliares que usaremos para comprobar si las posiciones estan libres
			auxX = x;
			auxY = y;

			//Primero deberiamos comprobar si las posiciones para insertar el barco estan libres
			for(int i=0; i<barco.getTamanio(); i++) {
				if (comprobarSiLibre(auxX, auxY)) {
					//Como nos movemos verticalmente, incrementamos X
					auxX++;
				} else {
					libre = false;
					break;
				}
			}

			//Si las posiciones est�n libres, colocamos el barco sin miedo
			if(libre) {
				for(int i=0; i<barco.getTamanio(); i++) {
					this.tablero[x][y] = barco.getIni();
					x++;
				}
				colocado = true;
			}
		}
		System.out.flush();
	}


	/**
	 * Comprueba si la posici�n dada est� libre o no.
	 * @param x
	 * @param y
	 * @return False si est� ocupado y True si no est� ocupado
	 */
	private boolean comprobarSiLibre(int x, int y) {
		return (this.tablero[x][y] != guion) ? false : true;
	}
	
	
	/**
	 * M�todo para mostrar el tablero por pantalla
	 */
	public void mostrarTablero() {

		// Imprimo dos espacios
		System.out.print("  ");

		// La letra A en decimal es el numero 65
		int letra = 65;
		for (int i = 0; i < NCOLS; i++) {
			System.out.format("%2c", (char) letra);
			letra++;
		}
		// Imprimo un salto de linea
		System.out.println();

		// Procedo a mostrar el contenido de mi tablero
		for (int i = 0; i < tablero.length; i++) {
			// Primero imprimo el numero de linea
			System.out.format("%2d", i + 1);
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.format("%2c", this.tablero[i][j]);
			}
			// Al terminar la fila, imprimo un salto de l�nea
			System.out.println();
		}
		System.out.flush();
	}

	/**
	 * Si se produce un error en este m�todo, se arroja una excepci�n
	 * @param mensaje mensaje de error que se mostrar� por pantalla
	 * @throws Exception Excepci�n gen�rica que se lanza al producirse un error
	 * @see Barco
	 */
	private void imprimirError(String mensaje) throws Exception {
		System.err.format("ERROR: %s", mensaje);
		throw new Exception(mensaje);
	}

	public int getPosicionesBarcos() {
		return posicionesBarcos;
	}

	public void setPosicionesBarcos(int posicionesBarcos) {
		this.posicionesBarcos = posicionesBarcos;
	}
}
