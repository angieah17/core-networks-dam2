package dam.multihilo.repaso3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanzaBolas implements Runnable {

	private String nombre;


	public LanzaBolas(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public void run() {

		LocalTime ahora = LocalTime.now();

		DateTimeFormatter formatea = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

		System.out.printf("Lanzando bola desde %s a las %s%n", nombre, ahora.format(formatea));

	}


	public static void main(String[] args) {


		LanzaBolas l1 = new LanzaBolas("Lanzador1");
		LanzaBolas l2 = new LanzaBolas("Lanzador2");
		LanzaBolas l3 = new LanzaBolas("Lanzador3");

		/*ExecutorService executor = Executors.newFixedThreadPool(3);

		executor.execute(l1);
		executor.execute(l2);
		executor.execute(l3);

		executor.shutdown();

		ExecutorService executor9 = Executors.newFixedThreadPool(9);

		executor9.execute(l1);
		executor9.execute(l2);
		executor9.execute(l3);

		executor.shutdown();*/

		ExecutorService executor1 = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 3; i++) {
			executor1.execute(l1);
			executor1.execute(l2);
			executor1.execute(l3);
		}

		executor1.shutdown();

	}


}
