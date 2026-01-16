package dam.ficheros.buffered.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Persona {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int nacido;
	private final static String pathNombres = "ficheros/nombres.txt";
	private final static String pathApellidos = "ficheros/apellidos.txt";
	private final static List<String> nombres = importarLista(pathNombres); //final: referencia a la lista es fija, no que su contenido no pueda cambiar
	private final static List<String> apellidos = importarLista(pathApellidos);
	//private final static Random random = new Random();
	private static RandomGenerator rnd = RandomGenerator.getDefault();
	/*A partir de Java17 y se recomienda el uso un poco más moderno
	 * 1 sola instancia y respeta multihilo*/

	private static String elementoAleatorio(List<String> lista) {



		return "";
	}



	public Persona() {
		nombre = elementoAleatorio(nombres);
		apellido1 = elementoAleatorio(apellidos);
		apellido2 = elementoAleatorio(apellidos);
		nacido = rnd.nextInt(1920, 2025 + 1);
	}


	private boolean generarSalida(String file,List<Persona>personas) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
			bw.write(String.format("CREATE DATABASE mundo;%n"));
			bw.write(String.format("show databases;%n"));
			bw.write(String.format("USE mundo;%n"));
			bw.write(String.format("CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);%n"));
			bw.write(String.format("DESCRIBE personas;%n"));

			for (Persona element : personas) {
				bw.write(String.format("('%s', '%s', '%s', %d),%n", element.nombre, element.apellido1, element.apellido2, element.nacido));
			}


			return true;
		} catch (IOException e) {
			System.out.printf("Error escribiendo fichero: %s%n", file);
			return false;
		}


	}

	private static List<String> importarLista(String file){
		List<String> listaDatos = null;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){

			String[] arrayDatos = br.readLine().trim().split(",");

			for (int i = 0; i < arrayDatos.length; i++) {
				arrayDatos[i] = arrayDatos[i].trim();
			}
			listaDatos =
			List.of(arrayDatos);

		}catch (IOException e){
			System.out.println("No se ha podido escribir");
		}

		return listaDatos;

	}



	public static void main(String[] args) {

		int cantidadPersonas = 10;

		List<Persona> personas = new ArrayList<>();

		for (int i = 0; i < cantidadPersonas; i++) {
			personas.add(new Persona());
		}

		System.out.println(Persona.nombres);



	}


}
