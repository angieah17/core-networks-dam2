package dam.multihilo.lanzador;

public class LanzadorVaciadoBotella extends Thread {

	private Botella botella;

	public LanzadorVaciadoBotella(Botella botella) {
		this.botella = botella;
	}


	@Override
	public void run() {
		botella.vaciar();
	}




}
