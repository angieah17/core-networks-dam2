package dam.multihilo.repaso2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LuciernagaR implements Runnable {
	private String nombre;
	private boolean encendido;
	private int energia;
	private static final int milis = 300;


	public LuciernagaR(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia >= 1 && energia <= 50 ? energia : 1;
	}


	public void enciende () {

		if(encendido) {
			System.out.println("La luciérnaga ya está encendida");
			return;
		}

		if(energia <= 0) {
			System.out.println("La luciérnaga no tiene energía suficiente para encenderse");
		}

		encendido = true;


			for (int i = energia; i > 0; i--) {
				System.out.printf("%s - Energía: %d%n", nombre, energia--);
				try {
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

		LuciernagaR l1 = new LuciernagaR("L1", 10);
		LuciernagaR l2 = new LuciernagaR("L2", 5);
		LuciernagaR l3 = new LuciernagaR("L3", 3);

		new Thread(l1).start();
		new Thread(l2).start();
		new Thread(l3).start();

		ExecutorService exs1 = Executors.newSingleThreadExecutor();
		exs1.execute(l3);
		exs1.shutdown();

	}




}
