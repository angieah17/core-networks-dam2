package dam.excepciones.pajaros;

public class JaulaNotAvailableException extends JaulaException {

	public JaulaNotAvailableException(String message) {
		super(message);
	}
	
	//Si la Jaula ya está abierta o se pasa null, se lanza esta excepción
	
}
