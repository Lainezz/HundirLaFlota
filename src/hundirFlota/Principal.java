package hundirFlota;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		int nLanchas = 0, nBuques = 0, nAcorazados = 0, nPortaAviones = 0;
		int nIntentos = 0;
		int nPosiciones = 0;
		// Tablero que mostrar� los disparos del jugador (Agua, Barcos, pos Vacias,
		// Aciertos...)
		Tablero tableroJuego = new Tablero();

		// Tablero que mostrar� el tablero con las posiciones de los barcos
		Tablero tableroSolucion = new Tablero();

		// SELECCION DE DIFICULTAD
		Scanner sc = new Scanner(System.in);
		int opc;
		boolean opcOK = false;
		cabecera();
		menuPpal();
		System.out.flush();
		while (!opcOK) {
			opc = sc.nextInt();

			switch (opc) {
			case 1: {
				opcOK = true;
				System.out.println("Ha elegido dificultad f�cil");
				System.out.flush();
				nLanchas = 5;
				nBuques = 3;
				nAcorazados = 1;
				nPortaAviones = 1;
				nIntentos = 50;
				break;
			}
			case 2: {
				System.out.println("Ha elegido dificultad media");
				System.out.flush();
				opcOK = true;
				nLanchas = 2;
				nBuques = 1;
				nAcorazados = 1;
				nPortaAviones = 1;
				nIntentos = 30;
				break;
			}
			case 3: {
				System.out.println("Ha elegido dificultad dif�cil");
				System.out.flush();
				opcOK = true;
				nLanchas = 1;
				nBuques = 1;
				nAcorazados = 0;
				nPortaAviones = 0;
				nIntentos = 10;
				break;
			}
			case 4: {
				opcOK = true;
				break;
			}

			case 0: {
				despedida();
				opcOK = true;
				return;
			}

			default:
				errorSeleccion();
				menuPpal();
				System.out.flush();
			}
		}

		// Creamos objetos de tipo Barco para meter en el tablero
		Barco lancha = new Barco("Lancha", 'L', 1);
		Barco buque = new Barco("Buque", 'B', 3);
		Barco acorazado = new Barco("Acorazado", 'Z', 4);
		Barco portaAviones = new Barco("PortaAviones", 'P', 5);

		//Calculamos el numero de posiciones a derribar
		nPosiciones = nPosiciones +
				((nLanchas*lancha.getTamanio())
				+ (nBuques*buque.getTamanio())
				+ (nAcorazados*acorazado.getTamanio())
				+ (nPortaAviones*portaAviones.getTamanio()));
		tableroSolucion.setPosicionesBarcos(nPosiciones);
		try {
			for (int i = 0; i < nLanchas; i++) {
				tableroSolucion.colocarBarco(lancha);
			}
			// Colocamos los buques
			for (int i = 0; i < nBuques; i++) {
				tableroSolucion.colocarBarco(buque);
			}
			// Colocamos los acorazados
			for (int i = 0; i < nAcorazados; i++) {
				tableroSolucion.colocarBarco(acorazado);
			}
			// Colocamos los portaAviones
			for (int i = 0; i < nPortaAviones; i++) {
				tableroSolucion.colocarBarco(portaAviones);
			}
		} catch (Exception e) {
			System.out.println("holi");
		}
		// Colocamos las lanchas

		tableroSolucion.mostrarTablero();

		//COMIENZA EL JUEGO
		comienzoJuego();
		boolean ganador = false;
		String columna;
		int fila;
		int col;
		char respuesta;
		while(nIntentos>0 && !ganador) {
			tableroJuego.mostrarTablero();
			System.out.println("�Dispara!");
			System.out.print("Fila [1-10]: ");
			System.out.flush();
			fila = sc.nextInt();
			fila -= 1;
			sc.nextLine();
			System.out.print("Columna [A-J]: ");
			System.out.flush();
			columna = sc.nextLine();
			col = columna.trim().toUpperCase().charAt(0) - 65;

			if(fila<0 || fila>9 || col<0 || col>9) {
				errorPosicion();
			} else {
				respuesta = tableroSolucion.checkDisparo(fila, col);
				tableroJuego.updateTablero(fila, col, respuesta);
				nIntentos--;
				if(tableroSolucion.getPosicionesBarcos()==0) {
					ganador = true;
				}
			}
		}

		tableroJuego.mostrarTablero();
		if(ganador) {
			System.out.println("Ha ganado");
		} else {
			System.out.println("Ha perdido");
		}
	}

	public static void cabecera() {
		System.out.print("____________________________________________\n");
		System.out.print("______________HUNDIR LA FLOTA_______________\n");
		System.out.flush();
	}

	public static void menuPpal() {
		System.out.print(
				"___________Elija dificultad (1 - 4)_________\n"
				+ "____________________________________________\n"
				+ "1. Nivel f�cil\n" + "2. Nivel medio\n"
				+ "3. Nivel dif�cil\n"
				+ "4. Nivel personalizado\n"
				+ "\n"
				+ "0. Salir\n");
		System.out.flush();
	}

	public static void errorSeleccion() {
		System.err.print(
				"************\n"
				+ "Error en la elecci�n de la dificultad.\n"
				+ "Por favor, elija una opci�n v�lida.\n"
				+ "************\n");
	}

	public static void despedida() {
		System.out.print("Nus vemos!\n");
		System.out.flush();
	}

	public static void comienzoJuego() {
		try {
			System.out.print("\n\nComienza el juego en 3");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print("2");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print("1");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print(".");
			Thread.sleep(300);System.out.print("Go!");
			Thread.sleep(500);
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.flush();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void errorPosicion() {
		System.err.println("Error, indique una opcion v�lida");
	}

}
