package dam.multihilo.recordatorio;

public class CuentaAtras extends Thread {

	private String nombre;
	private int inicio;


	/*Con Thread ya se limita a que la clase no extienda de ninguna otra clase y esto lo limita. Por eso
	 * se recomienda el uso de la interfaz Runnable.
	 * */

	public CuentaAtras(String nombre, int inicio) {
		this.nombre = nombre;
		this.inicio = inicio;
	}

	@Override
	public void run() {

		for (int i = inicio; i >= 0; i--) {
			System.out.printf("%s: %d %n",nombre, i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



}
