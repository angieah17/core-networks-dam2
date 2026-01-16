package dam.multihilo.repaso4;

public class CountDown implements Runnable {

	private static long milis = 100;
	private int start;
	private String name;



	public CountDown(int start, String name) {
		super();
		this.start = start;
		this.name = name;
	}




	@Override
	public void run() {

		for (int i = start; i >= 0; i--) {
			System.out.printf("%s: %d%n", name, i);

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}


	public static void main(String[] args) {

		CountDown c1 = new CountDown(5, "C1");
		Thread thread = new Thread(c1);

		//Estado NEW
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

		//Estado RUNNABLE

		thread.start();
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

		//Estado TIMED_WAITING

		while (thread.isAlive()) {

			System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

			try {
				Thread.sleep(milis / 3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/*El método run() SÍ se ejecuta en ambos casos, pero sin el sleep en el while,
			 * la salida del hilo principal es tan abundante que oculta visualmente los mensajes del hilo C1.
			 * Por eso es necesario incluir este Thread.sleep
			 *
			 * */

		}

		//Estado TERMINATED

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());




	}




}
