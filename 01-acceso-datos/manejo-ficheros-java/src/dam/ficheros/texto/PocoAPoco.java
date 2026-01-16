package dam.ficheros.texto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PocoAPoco {


	public static void main(String[] args) {

		String linea1 = "¡Hola mundo!";
		String linea2 = "¡Adiós mundo!";
		String [] lineas = {linea1, linea2};


		/*La clase al ser AutoClosable permite usar el try witch resources
		 * */

		try (FileWriter fw = new FileWriter("src/ficheros/out.txt");) {

			for (int i = 0; i < lineas.length; i++) {
				fw.append(lineas[i]); //Se agrega el String que es un CharSequence, se está usando ese constructor
				if (i != lineas.length - 1) {
					fw.append("\n"); //Se ha creado el archivo pero no se ve el contenido porque no se ha hecho flush
				}
			}
			//fw.flush(); //Si pese a estar el while(true), se quiere hacer el flush se tiene que hacer expreso, no a través del close.
			//while(true); // Si se pone esto hace que no salga del try y por eso el close automático no se ejecuta y no se hace flush, por eso, el texto no aparece

		} catch (IOException e) {
			System.out.println("Problemas creando el archivo");

		}finally {
			System.out.println("Gracias por su confianza");

		}


		try (FileReader fr = new FileReader("src/ficheros/out.txt");) {
			System.out.printf("Encoding: %s%n", fr.getEncoding());
			int c = 0;
			while((c = fr.read()) != -1) { //Aquí se está asignando a c el valor de cada caracter, para que siga leyendo hasta que no haya nada más y se asigne -1
				System.out.print((char)c); //Hacemos el print solo para que sea lea línea completa
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("Problemas abriendo el archivo.");
		}finally {
			System.out.println("Gracias por su confianza");
		}



	}

}
