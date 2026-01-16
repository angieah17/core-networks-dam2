package dam.multihilo.repaso4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LuciernagaR  implements Runnable{
	private String nombre;
	private boolean encendida;
	private int energia;
	private static final long milis = 300;



	public LuciernagaR(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia >= 1 && energia <= 50 ? energia : 1 ;
	}

	public void enciende() {

		if(encendida) {
			System.out.println("La luciérnaga ya está encendida");
			return;
		}

		if(energia <= 0) {
			System.out.println("La luciérnaga no tiene energía");
			return;
		}

		encendida = true;

		for (int i = energia; i >= 0; i--) {
			System.out.printf("Luciérnaga %s con energia %d%n", nombre, energia--);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		encendida = false;


	}

	@Override
	public void run() {
		enciende();
	}



	public static void main(String[] args) {

		LuciernagaR l1 = new LuciernagaR("L1", 5);
		LuciernagaR l2 = new LuciernagaR("L2", 10);

		/*
		new Thread(l1).start();
		new Thread(l2).start();*/


		//En 1 hilo diferente al main
		/*ExecutorService exs1 = Executors.newSingleThreadExecutor();
		exs1.execute(l1);
		exs1.execute(l2);
		exs1.shutdown();*/

		//En 3 hilos
		ExecutorService exs2 = Executors.newFixedThreadPool(3);
		exs2.execute(l1);
		exs2.execute(l2);
		exs2.shutdown();


	}




}
