package dam.excepciones.basico;

public class Door implements AutoCloseable {

	public void open() {
		System.out.println("Abriendo puerta");
	}


	@Override
	public void close() throws Exception {
		System.out.println("Cerrando puerta");
		/*esto se va a producir siempre se produzca o no una excepción, un poco como el finally, pero cierra primero antes de gestionar la excepción*/

	}


	public static void main(String[] args) {

		try (Door d1 = new Door()) {
			d1.open();
			int i = 1/0;
		} catch (Exception e) {
			System.out.println("Detectada excepción");
		}


	}


}
