package dam.excepciones.pajaros;

public class OperacionesJaula implements AutoCloseable{
	
	private Jaula jaula;
	
	
	public OperacionesJaula(Jaula jaula) throws JaulaException {
		if(jaula == null) throw new JaulaNotAvailableException("El objeto ingresado es nulo");
		if(jaula.isPuertaAbierta()) throw new JaulaNotAvailableException("La Jaula no está disponible, se encuentra abierta.");
		
		/*Simulamos posibilidad de fallo al abrir (atasco de puerta),
		 * Math.random genera números entre 0.0 y 1.0(sin incluirlo)*/
        if (Math.random() < 0.1) { // 10% de probabilidad de atasco
            throw new PuertaAtascadaException("La puerta se ha atascado al abrir la jaula.");
        }
		
		
		jaula.setPuertaAbierta(true);
		this.jaula = jaula;
		System.out.printf("Puerta abierta para operar la Jaula %s", jaula);
	}



	@Override
	public void close() throws JaulaException {
		//Terminadas las operaciones se debe cerrar la puerta siempre para que no se escapen los pájaros.
		
		if (Math.random() < 0.1) { // 10% de probabilidad de atasco
            throw new PuertaAtascadaException("La puerta se ha atascado al cerrar la jaula.");
        }
		
		jaula.setPuertaAbierta(false);
		System.out.printf("Puerta cerrada de la Jaula %s", jaula);
	}
	
}
