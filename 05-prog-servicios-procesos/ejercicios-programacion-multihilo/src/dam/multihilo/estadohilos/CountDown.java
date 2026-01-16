package dam.multihilo.estadohilos;

public class CountDown implements Runnable {

	private static final long milis =100;
	private int start;
	private String name;


	public CountDown(int start, String name) {
		this.start = start;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = start; i >= 0; i--) {

			try {
				System.out.printf("%s: %d%n", name, i);
				Thread.sleep(milis); // ← Duerme al hilo que ejecuta este código (Thread-0)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}


	public static void main(String[] args) {

		CountDown c1 = new CountDown(10, "C1");
		Thread thread = new Thread(c1);

		/*Para conocer el estado de los hilos Thread tiene una serie de métodos:
		 * thread.getName();
		 * thread.getState();
		 *
		 * */

		/*NEW: Hilo creado pero no arrancado
		 * */

		System.out.println("Estado NEW");
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

		/*RUNNABLE: hilo arrancado pero puede estar o no funcionando, sucede después de arrancar con thread.start()*/

		thread.start();

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());


		while (thread.isAlive()) {
	            // Estado TIMED_WAITING: en espera debido a Thread.sleep(). Espera por un tiempo determinado
	            System.out.printf("%s - Estado: %s%n", thread.getName(),thread.getState());
	            try {
	                // Tiempo de espera
	                Thread.sleep(milis/3);  // ← Duerme al hilo principal (main)
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	        }

		System.out.printf("%s - Estado: %s%n",thread.getName(), thread.getState());

	}


}
