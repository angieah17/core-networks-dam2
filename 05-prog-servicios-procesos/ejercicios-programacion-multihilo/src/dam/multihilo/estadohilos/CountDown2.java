package dam.multihilo.estadohilos;

import java.util.ArrayList;
import java.util.List;

public class CountDown2 implements Runnable {

	private static final long milis =100;
	private int start;
	private String name;



	public CountDown2(int start, String name) {
		this.start = start;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = start; i >= 0; i--) {

			try {
				System.out.printf("%s: %d%n", name, i);
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}


	public static void main(String[] args) {

		CountDown2 c1 = new CountDown2(10, "C1");
		Thread thread = new Thread(c1);
		List<Thread.State> states = new ArrayList<>();
		//Thread.State state; también se podría creando una variable local, pero creo que hace el código más largo

		// Estado NEW
		states.add(thread.getState());
		System.out.println(thread.getState());

		// Estado RUNNABLE
		thread.start();
		states.add(thread.getState());
		System.out.println(thread.getState());

		while (thread.isAlive()) {
		    states.add(thread.getState());
		    System.out.println(thread.getState());
		    try {
		        Thread.sleep(milis / 2);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}

		// Estado TERMINATED
		states.add(thread.getState());

		 // Imprimir todos los estados por los que pasó el hilo
	        System.out.println("Estados por los que pasó el hilo:");
	        states.forEach(System.out::println);
	        System.out.printf("Estados almacenados: %d%n", states.size());



	}


}
