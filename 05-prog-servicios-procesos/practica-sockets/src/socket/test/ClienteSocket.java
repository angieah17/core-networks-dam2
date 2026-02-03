package socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
	
	private static final int PUERTO = 8083;
	private static final String HOST = "localhost";
	
	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
				PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in);) {
			System.out.println("=== CLIENTE SOCKET CONECTADO ===");
			System.out.printf("Cliente conectado desde: %s%n", socket.getInetAddress());
			
			String mensajeEnviado;
			boolean continuar = true;
			
			
			while (continuar) {
				//1. Introducir mensaje 
				System.out.println("Introduce el mensaje: ");
				mensajeEnviado = scanner.nextLine();
				
				//2. Enviar mensaje al server
				
				System.out.println("Mensaje enviado: " + mensajeEnviado);
				salida.println(mensajeEnviado);
				
				//3. Leer mensaje recibido del server
				String respuesta = entrada.readLine();
				System.out.println("Mensaje recibido: " + respuesta);
				
				//4. Procesar respuesta
				
				if(respuesta.equals("#Error#")) {
					System.out.println("Mensaje no adecuadamente formateado para su tratamiento.");
				} else if(respuesta.equals("#Finalizado#")) {
					continuar = false;
					System.out.println("Fin de la conexión.");
				} 
				
			}
			
			
		} catch (UnknownHostException e) {
			System.out.println("Error estableciendo conexión con el HOST: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro E/S: " + e.getMessage());
		}
		
	}
	
}
