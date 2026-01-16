package dam.multihilo.repaso2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanzaBolas implements Runnable{

	private String nombre;




	public LanzaBolas(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public void run() {

		LocalTime ahora = LocalTime.now();

		DateTimeFormatter formatear = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		ahora.format(formatear);

		System.out.printf("Lanzando bola desde %s a las %s%n", nombre, ahora);

	}

	public static void main(String[] args) {

		LanzaBolas l1 = new LanzaBolas("Lanzador1");
		LanzaBolas l2 = new LanzaBolas("Lanzador2");
		LanzaBolas l3 = new LanzaBolas("Lanzador3");

		ExecutorService executor1 = Executors.newFixedThreadPool(3);

		executor1.execute(l1);
		executor1.execute(l2);
		executor1.execute(l3);
		executor1.shutdown();




	}


}
