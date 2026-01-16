package dam.ficheros.binarios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaIdentica {
	public static void main(String[] args) {

		try(FileInputStream fis = new FileInputStream("src/ficheros/gato_negro.jpg");
				FileOutputStream fos = new FileOutputStream("src/ficheros/gato_negro2.jpg");) {

			int b = 0;

			//Mientras b sea un int distintio de -1 es que se puede leer misma lógica que en el FileReader
			while ((b = fis.read()) != -1 ) {
				fos.write(b);
			}



		} catch (IOException e) {
			System.out.println("Problemas con los archivos");
		}finally {
			System.out.println("Gracias por su confianza");
		}


	}
}
