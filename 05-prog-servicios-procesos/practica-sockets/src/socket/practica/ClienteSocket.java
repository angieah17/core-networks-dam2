package socket.practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
	
	public static final String HOST = "localhost";
	public static final int PUERTO = 8082;

	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
				PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in);) {
			
			System.out.println("=== CLIENTE SOCKET CONECTADO ===");
			System.out.printf("Conectado al servidor %s : %d%n", HOST, PUERTO);
			
			
			String mensajeEnviado;
			boolean continuar = true;
			
			while (continuar) {
				
				//1. Introducir mensaje
				System.out.println("Introduzca el mensaje: ");
				mensajeEnviado = scanner.nextLine();
				
				//2. Enviar el mensaje
				System.out.printf("Mensaje enviado: %s%n", mensajeEnviado);
				salida.println(mensajeEnviado);
				
				//3. Leer respuesta del servidor
				String respuesta = entrada.readLine();
				System.out.printf("Mensaje recibido: %s%n", respuesta);
				
				if(respuesta.equals("#Error#")) {
					System.out.println("Mensaje no adecuadamente formateado para su tratamiento.");
				} else if (respuesta.equals("#Finalizado#")) {
					System.out.println("Fin de la conexión");
					continuar = false;
				}
				
			}
			
			
			
		} catch (UnknownHostException e) {
			System.out.printf("Error host desconocido %s%n", e.getMessage() );
		} catch  (IOException e){
			System.out.printf("Error E/S: %s%n", e.getMessage() );
		}
		
	}
	
	
	
}
