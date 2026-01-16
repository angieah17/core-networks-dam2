package dam.excepciones.pajaros;

public class OperacionesJaula implements AutoCloseable{

	private Jaula jaula;







	public OperacionesJaula(Jaula jaula) {
		this.jaula = jaula;
	}







	@Override
	public void close() throws Exception {
		//Terminadas las operaciones se debe cerrar la puerta siempre para que no se escapen los pájaros.

	}

}
