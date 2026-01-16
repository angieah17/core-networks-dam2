package dam.multihilo.recordatorio;

public class CuentaAtrasRunnable implements Runnable{

	private String nombre;
	private int inicio;

	/*Runnable tiene el método run que se sobreecribe. Se recomienda este uso,
	 * de tal forma que la clase pueda extender de una clase.
	 *
	 * Para usar el run en el test se crea un new Thread() pasando el objeto runnable como
	 * parámetro.
	 * */


	public CuentaAtrasRunnable(String nombre, int inicio) {
		this.nombre = nombre;
		this.inicio = inicio;
	}

	@Override
	public void run() {

		for (int i = inicio; i >= 0; i--) {
			System.out.printf("%s: %d ",nombre, i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



}
