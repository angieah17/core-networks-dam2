package dam.multihilo.luciernagas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LuciernagaR implements Runnable {
	private String nombre;
	private boolean encendido;
	private int energia;
	private static int milis = 300;



	public LuciernagaR(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia >= 1 && energia <= 50 ? energia : 1;
	}

	public void enciende() {

		if(encendido) {
			System.out.println("La luciérnaga ya está encendida");
			return;
		}

		if (energia <= 0) {
			System.out.println("La luciérnaga no tiene energía suficiente para encenderse");
			return;
		}


		encendido = true;

		while (energia > 0) {
				try {
					System.out.printf("Energía de %s: %d%n", nombre ,energia--);
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		encendido = false;



	}



	@Override
	public void run() {
		enciende();
	}



	public static void main(String[] args) {
		LuciernagaR l1 = new LuciernagaR("L1", 5);
		LuciernagaR l2 = new LuciernagaR("L2", 8);
		LuciernagaR l3 = new LuciernagaR("L3", 2);

		/*new Thread(l1).start();
		new Thread(l2).start();
		new Thread(l3).start();*/

		ExecutorService executorDe1 = Executors.newSingleThreadExecutor();


		executorDe1.execute(l2);
		executorDe1.execute(l3);
		executorDe1.shutdown();

	}



}
