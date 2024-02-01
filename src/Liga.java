import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Liga {
	
	public static Scanner teclado = new Scanner(System.in);
	public static ArrayList listaPer = new ArrayList(); 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ArrayList listaPer = new ArrayList();
		
        int eleccion = 0;
		
		do {
			menu();
			eleccion = ComprobarSiEsNum("Elige opción: ");
			
			switch(eleccion) {
				case 1:
					insertaJugador();
					break;
				case 2:
					insertaArbitro();
					break;
				case 3:
					if(listaPer.isEmpty()) {
						System.out.println("No hay nada que mostrar");
					}else {
						mostrarTodo();
					}
					break;
				case 4:
					if(listaPer.isEmpty()) {
						System.out.println("No hay nada que ordenar");
					}else {
						ordenaPerVeloRecursivo(0, 1);
					}
					
					break;
				case 5:
					if(listaPer.isEmpty()) {
						System.out.println("No hay nada que mostrar");
					}else {
						mostrarSoloJugadores();
					}
					
					break;
				case 6:
					System.out.print("Dame nombre a buscar:");
					String nombre = teclado.next();
					int posi = buscarRecursivo(nombre,0);
					if(posi == -1) {
						System.out.println("No hay nadie que se llame: " + nombre);
					}else {
						if(listaPer.get(posi) instanceof Jugador) {
							System.out.println("Se ha encontrado un jugador:");
							System.out.println(((Jugador)listaPer.get(posi)));
						}else {
							System.out.println("Se ha encontrado un arbitro:");
							System.out.println(((Arbitro)listaPer.get(posi)));
						
						}
					}
					break;
				case 7:
					Jugador aux = null;
					aux = JugadorMasRegate();
					if(aux == null) {
						System.out.println("No hay ningun jugador");
					}else {
						System.out.println("El jugador con mas regate es:");
						System.out.println(aux);
					}
					
					break;	
				case 8:
					System.out.println("LA suma de todas las velocidades es: " + sumaVelocidades(0));
					break;
				case 9:
					System.out.println("FIN DEL PROGRAMA");
					break;		
				default:
					System.out.println("Solo se puede elegir entre 1, 2, 3, 4 y 5");
					break;
					
			}
		}while(eleccion != 9);
		
		
	}
	
	
	
	private static int sumaVelocidades(int i) {
		
		if(i == listaPer.size()-1) {
			if(listaPer.get(i) instanceof Jugador) {
				return ((Jugador)listaPer.get(i)).getVelocidad();
			}else {
				return ((Arbitro)listaPer.get(i)).getVelocidad();
			}
		}
		
		if(listaPer.get(i) instanceof Jugador) {
			return ((Jugador)listaPer.get(i)).getVelocidad() + sumaVelocidades(i+1);
		}else {
			return ((Arbitro)listaPer.get(i)).getVelocidad() + sumaVelocidades(i+1);
		}
	}

	private static Jugador JugadorMasRegate() {
		
		int posi = -1;
		int regate = 0;
		int regate2 = 0;
		for(int i=0; i<listaPer.size(); i++) {
			if(listaPer.get(i) instanceof Jugador) {
				regate2 = ((Jugador)listaPer.get(i)).getRegate();
				if(regate < regate2) {
					regate = regate2;
					posi = i;
				}
			}
		}
		if(posi == -1) {
			return null;
		}else {
			return (Jugador) listaPer.get(posi);
		}
		
	}

	private static int buscarRecursivo(String nombre, int i) {
		if(listaPer.get(i) instanceof Jugador && nombre.equalsIgnoreCase(((Jugador)listaPer.get(i)).getNombre())) {	
			return i;
		}else {
			if(listaPer.get(i) instanceof Arbitro && nombre.equalsIgnoreCase(((Arbitro)listaPer.get(i)).getNombre())) {
				return i;
			}
		}
		
		if(i == listaPer.size()-1) {
			return -1;
		}else {
			return buscarRecursivo(nombre, i+1);
		}
		
	}
	
	private static int buscarIterativo(String nombre) {
		
		int i = 0;
		
		while(i < listaPer.size()) {
			if(listaPer.get(i) instanceof Jugador && nombre.equalsIgnoreCase(((Jugador)listaPer.get(i)).getNombre())) {		
				return i;
			}else {
				if(listaPer.get(i) instanceof Arbitro && nombre.equalsIgnoreCase(((Arbitro)listaPer.get(i)).getNombre())) {
					return i;
				}
			}
			i++;
		}
		return -1;
	}

	private static void mostrarSoloJugadores() {
		boolean hay_jugadores = false;
		for(Object o : listaPer) {
			if(o instanceof Jugador) {
				System.out.println(o);
				hay_jugadores = true;
			}
		}
		if(!hay_jugadores) {
			System.out.println("No hay ningún jugador");
		}
		
	}

	private static void ordenaPerVeloRecursivo(int i, int j) {
		
		
		if(i < listaPer.size()-1) {
			int veloi = 0;
			int veloj = 0;
			
			if(listaPer.get(i) instanceof Jugador) {
				veloi = ((Jugador)listaPer.get(i)).getVelocidad();
			}else {
				veloi = ((Arbitro)listaPer.get(i)).getVelocidad();
			}
			
			if(listaPer.get(j) instanceof Jugador) {
				veloj = ((Jugador)listaPer.get(j)).getVelocidad();
			}else {
				veloj = ((Arbitro)listaPer.get(j)).getVelocidad();
			}
			
			if(veloi > veloj) {
				Object aux = null;
				aux = listaPer.get(i);
				listaPer.set(i, listaPer.get(j));
				listaPer.set(j, aux);
			}
			
			if(j == listaPer.size()-1) {
				ordenaPerVeloRecursivo(i+1, i+2);
			}else {
				ordenaPerVeloRecursivo(i, j+1);
			}
			
			// la j ha llegado al final y avanza la i y la j hay que inicializarla
			
			
		}
	}

	private static void ordenarPerVelo() {
		int veloi = 0;
		int veloj = 0;
		
		for(int i=0; i<listaPer.size()-1; i++) {
			if(listaPer.get(i) instanceof Jugador) {
				veloi = ((Jugador)listaPer.get(i)).getVelocidad();
			}else {
				veloi = ((Arbitro)listaPer.get(i)).getVelocidad();
			}
			
			for(int j=i+1; j<listaPer.size(); j++) {
				if(listaPer.get(j) instanceof Jugador) {
					veloj = ((Jugador)listaPer.get(j)).getVelocidad();
				}else {
					veloj = ((Arbitro)listaPer.get(j)).getVelocidad();
				}
				
				if(veloi > veloj) {
					Object aux;
					aux = listaPer.get(i);
					listaPer.set(i, listaPer.get(j));
					listaPer.set(j, aux);
				}
			}
		}
		
	}

	private static void mostrarTodo() {
		for(Object o : listaPer) {			
				System.out.println(o);												
		}
		
	}

	private static void insertaJugador() {
		System.out.print("Dame nombre jugador: ");
		String nombre = teclado.next();
		System.out.print("Dame posición jugador: ");
		String posicion = teclado.next();
		int velo = (int) (Math.floor(Math.random()*(100-0+1)+0));
		int regate = (int) (Math.floor(Math.random()*(100-0+1)+0));
		boolean lesion = preguntaBool("Está lesionado el jugador? S/N: ");
		
		listaPer.add(new Jugador(nombre, posicion, velo, regate, lesion));
		
		
		
	}
	
	private static void insertaArbitro() {
		System.out.print("Dame nombre arbitro: ");
		String nombre = teclado.next();
		System.out.print("Dame colegio arbitro: ");
		String colegio = teclado.next();
		int velo = (int) (Math.floor(Math.random()*(100-0+1)+0));
		int acierto = (int) (Math.floor(Math.random()*(100-0+1)+0));
		boolean activo = preguntaBool("esta activo el arbitro? S/N:");
		
		
		listaPer.add(new Arbitro(nombre, colegio, velo, acierto, activo));
		
		
		
	}
	
	private static boolean preguntaBool(String frase) {		
		
		do {		
			System.out.print(frase);
			String activado = teclado.next();
			if(activado.equalsIgnoreCase("N")) {
				return false;
			}else if(activado.equalsIgnoreCase("S")) {
				return true;
			}else {	
				System.out.println("Solo se puede insertar S/N");
			}
		}while(true);
	
	}

	private static void menu() {
		System.out.println("1) INSERTA JUGADOR");
		System.out.println("2) INSERTA ARBITRO");
		System.out.println("3) MOSTRAR TODO");
		System.out.println("4) ORDENAR PERSONAS POR VELOCIDAD");
		System.out.println("5) MOSTRAR SOLO JUGADORES");
		System.out.println("6) BUSCAR POR NOMBRE");
		System.out.println("7) JUGADOR CON MAS REGATE");
		System.out.println("8) SUMA TODAS LAS VELOCIADADES");
		System.out.println("9) SALIR");
		
	}
	
	public static int ComprobarSiEsNum(String frase) {
		int num = 0;
		String opcion = "";
		boolean correcto = true;
		do {
			correcto = true;
			System.out.print(frase);
			try {
				opcion = teclado.next();
				num = Integer.parseInt(opcion);
			}catch(Exception e) {
				correcto = false;
				System.out.println(opcion + " no es un NUMERO");
				//e.printStackTrace();
			}
		}while(correcto == false);
		
		return num;
	}

}
