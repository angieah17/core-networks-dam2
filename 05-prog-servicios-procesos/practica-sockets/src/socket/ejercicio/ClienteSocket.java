package socket.ejercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {

	private static final String HOST = "localhost";
	private static final int PUERTO = 8084;
	
	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
				PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in);) {
			
			System.out.println("=== CLIENTE CONECTADO ===");
			System.out.printf("Establecida la conexión Host: %s - Puerto: %d%n", HOST, PUERTO);
			
			String mensajeEnviado;
			boolean continuar = true;
			
			while (continuar) {
				
				//1. El usuario introduzca el mensaje 
				System.out.println("Introduce el mensaje: ");
				mensajeEnviado = scanner.nextLine();
				
				//2. Enviamos el mensaje
				salida.println(mensajeEnviado);
				System.out.println("Mensaje enviado: " + mensajeEnviado);
				
				//3. Leemos la respuesta del servidor
				
				String respuesta = entrada.readLine();
				
				System.out.println("Mensaje recibido: " + respuesta);
				
				//4. Procesamos el mensaje 
				
				if(respuesta.equals("#Error#")) {
					System.out.println("Mensaje no adecuadamente formateado para su tratamiento.");
				} else if (respuesta.equals("#Finalizado#")) {
					continuar = false;
					System.out.println("Fin de la conexión");
				}				
								
				
			}
			
			
			
		} catch (UnknownHostException e) {
			System.out.println("Error host desconocido" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error E/S" + e.getMessage());
		}
		
	}
	
}
