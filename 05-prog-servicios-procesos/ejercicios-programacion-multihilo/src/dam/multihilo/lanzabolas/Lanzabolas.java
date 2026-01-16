package dam.multihilo.lanzabolas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lanzabolas implements Runnable {
	private String nombre;




	public Lanzabolas(String nombre) {
		this.nombre = nombre;
	}




	@Override
	public void run() {
		// Obtener la hora actual
		LocalDateTime ahora = LocalDateTime.now();

		// Formatear la hora para mostrar horas, minutos, segundos y milisegundos
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

		System.out.printf("Lanzando bola desde %s a las %s%n", nombre, ahora.format(formatter));
	}




}
