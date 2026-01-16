package dam.excepciones.pajaros;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Jaula {

	private static int nextID = 1;
	private int id;
	private boolean puertaAbierta;
	private int capacidad;
	private Set<Pajaro> listaPajaros; //almacenamiento de pajaros

	Random random = new Random();


	public Jaula() {
		id = nextID++;
		capacidad = random.nextInt(3, 7);
		listaPajaros = new HashSet<>();
	}


	@Override
	public String toString() {
		return String.format("J-%d %s", id, listaPajaros);
	}


	public static void main(String[] args) {



		System.out.println(new Jaula());



	}




}
