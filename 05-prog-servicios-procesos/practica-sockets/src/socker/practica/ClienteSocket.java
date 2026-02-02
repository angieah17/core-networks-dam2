package socker.practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
	//1. Atributos
	
	private static final String HOST = "localhost"; 
	private static final int PUERTO = 8081;
	
	//2. Método para procesar respuesta
	
	private static void procesarRespuesta (String mensajeEnviado, String respuesta) {
		if(respuesta.equals("#Error#")) {
			System.out.println("Mensaje no cumple el formato para su tratamiento");
		} else if (respuesta.equals("#Finalizado#")) {
			System.out.println("Fin de la conexión");
		} else {
			//la respuesta es válida:
			System.out.println("Resultado: " + respuesta);
		}
	}
	
	
	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
			PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in);
				) {
			
			System.out.println("=== CLIENTE SOCKET CONECTADO ===");
			System.out.printf("Conectado al servidor en %s : %d", HOST, PUERTO);
			
			String mensajeEnviado;
			boolean continuar = true;
			
			while (continuar) {
				System.out.print("Introduce mensaje: ");
				mensajeEnviado = scanner.nextLine();
				
				//1.1 Enviar el mensaje al servidor
				
				salida.println(mensajeEnviado); //El método println() añade un salto de línea (\n), que el servidor puede detectar con readLine()
				System.out.printf("El mensaje enviado es: %s%n", mensajeEnviado);
				
				//1.2 Recibir respuesta del servidor
				String respuesta = entrada.readLine();
				System.out.printf("La respuesta recibida del servidor es: %s%n", respuesta);
				
				//1.3 Procesar la respuesta según el tipo
				procesarRespuesta(mensajeEnviado, respuesta);
				
				//1.4 Si recibimos #Finalizado#, terminamos el loop
				if(respuesta.equals("#Finalizado#")) continuar = false;
				
			}
			
			
		} catch (UnknownHostException e) {
			System.out.printf("No se pudo encontrar el servidor");
		} catch (IOException e) {
			System.out.printf("Error de E/S: %s", e.getMessage() );
		}
				
	}
	
	
	
	
	
}
