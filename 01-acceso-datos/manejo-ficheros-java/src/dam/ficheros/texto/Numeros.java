package dam.ficheros.texto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Numeros {
	public static void main(String[] args) {

		// 1. Generar pares e impares del 1 al 100
		List<Integer> numerosPares = new ArrayList<>();
		List<Integer> numerosImpares = new ArrayList<>();

		for (int i = 1; i <= 100; i++) {
			if(i % 2 == 0) {
				numerosPares.add(i);
			} else {
				numerosImpares.add(i);
			}
		}



		//2. Escribiendo en los archivos pares e impares

		try (FileWriter fwPares = new FileWriter("src/ficheros/pares.txt");
			FileWriter fwImpares = new FileWriter("src/ficheros/impares.txt");) {


			for (int num : numerosPares) {
				fwPares.write(String.valueOf(num) + "\n");
			}


			for (int num : numerosImpares) {
				fwImpares.write(String.valueOf(num) + "\n");
			}



		} catch (IOException e) {

			e.printStackTrace();
		}



		//3. Leyendo y escribiendo en todos y todosOrdenados

		try (FileReader frPares = new FileReader("src/ficheros/pares.txt");
			FileReader frImpares = new FileReader("src/ficheros/impares.txt");
			FileWriter fwTodos = new FileWriter("src/ficheros/todos.txt");
			FileWriter fwTodosOrdenados = new FileWriter("src/ficheros/todos_ordenados.txt");) {

			//3.1 Leyendo pares e impares y escribiendolo en todos

			int c = 0;
			while((c = frPares.read()) != -1) {
				fwTodos.write(c);
			}

			c = 0;

			while((c = frImpares.read()) != -1) {
				fwTodos.write(c);
			}

			//3.2 Creando la lista de todos para escribirlo en todosAlReves
			List<Integer> todos = new ArrayList<>(numerosPares);

			todos.addAll(numerosImpares);

			List<Integer> todosAlReves =
			todos.stream().sorted(Collections.reverseOrder()).toList();


			for (Integer num : todosAlReves) {
				fwTodosOrdenados.write(num + "\n");
			}



		} catch (IOException e) {

			e.printStackTrace();
		}







	}
}
