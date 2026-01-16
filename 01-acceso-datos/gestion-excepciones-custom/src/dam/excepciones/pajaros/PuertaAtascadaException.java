package dam.excepciones.pajaros;

public class PuertaAtascadaException extends JaulaException {

	public PuertaAtascadaException(String message) {
		super(message);
	}
	
	/*Si la puerta no se puede abrir o no se puede cerrar se lanza esta excepción
	 * En este caso se simula una posibilidad de fallo en este proceso*/
	
}
