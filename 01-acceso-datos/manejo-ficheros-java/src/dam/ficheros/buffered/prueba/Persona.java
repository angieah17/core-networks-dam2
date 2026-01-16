package dam.ficheros.buffered.prueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

public class Persona {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int nacido;
	private static List<String> nombres;
	private static List<String> apellidos;
	private static RandomGenerator rnd = RandomGenerator.getDefault();



	@Override
	public String toString() {
	    return nombre + " " + apellido1 + " " + apellido2 + " (" + nacido + ")";
	}


	public Persona() {
		nombre = generarNombreAleatorio();
		apellido1 = generarApellidoAleatorio();
		apellido2 = generarApellidoAleatorio();
		nacido = rnd.nextInt(1920, 2025);

	}



	private static String generarNombreAleatorio() {

		return nombres.get(rnd.nextInt(nombres.size()));
	}

	private static String generarApellidoAleatorio() {

		return apellidos.get(rnd.nextInt(apellidos.size()));
	}


	public static List<Persona> damePersonas (int cantidad){

		List<Persona> personas = new ArrayList<>();

		for (int i = 0; i < cantidad; i++) {
			personas.add(new Persona());
		}

		return personas;
	}


	public static boolean cargarNombresYApellidos() {

		try(BufferedReader brNombres = new BufferedReader(new FileReader("src/ficheros/personas/nombres.txt"));
			BufferedReader brApellidos = new BufferedReader(new FileReader("src/ficheros/personas/apellidos.txt"));) {

			nombres = Arrays.asList(brNombres.readLine().trim().split(","));


			apellidos = Arrays.asList(brApellidos.readLine().trim().split(","));



			return true;

		} catch (IOException e) {
			System.out.println("Problema manejando fichero de entrada");
			return false;
		}

	}

	public static boolean formateaPersonas(List<Persona> personas) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/ficheros/personas/probador.sql"));) {

			bw.write(String.format("CREATE DATABASE mundo;%n"));
			bw.write(String.format("show databases;%n"));
			bw.write(String.format("USE mundo;%n"));
			bw.write(String.format("CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);%n"));
			bw.write(String.format("DESCRIBE personas;%n"));
			bw.write(String.format("INSERT INTO personas(nombre, apellido1, apellido2, nacimiento) VALUES %n"));


			for (int i = 0; i < personas.size() - 1; i++) {
				bw.write(String.format("('%s', '%s', '%s', %d),%n", personas.get(i).nombre, personas.get(i).apellido1, personas.get(i).apellido2, personas.get(i).nacido));

			}

			//En la última línea debe ir diferente para que termine en ;
			bw.write(String.format("('%s', '%s', '%s', %d);", personas.get(personas.size() -1).nombre, personas.get(personas.size() -1).apellido1, personas.get(personas.size() -1).apellido2, personas.get(personas.size() -1).nacido));

			return true;

		} catch (IOException e) {
			System.out.println("Problema manejando fichero de salida");
			return false;
		}


	}



	public static void main(String[] args) {
		/*
		System.out.println(damePersonas(10));*/
		int totalPersonas = 10_000;
		if (!cargarNombresYApellidos() || !formateaPersonas(damePersonas(totalPersonas))) {
			return;
		}
		System.out.printf("Operación realizada satisfactoriamente con %d personas.%n", totalPersonas );



	}


}
