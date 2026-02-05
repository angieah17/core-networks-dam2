package examen.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
	
	private static final String HOST = "localhost";
	private static final int PUERTO = 6000;
	
	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
				PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in)) {
			
			System.out.println("=== CLIENTE CONECTADO ===");
			System.out.printf("Cliente conectado desde el %s - %d%n", HOST, PUERTO);
			
			String mensajeEnviado;
			boolean continuar = true;
			
			while (continuar) {
				//1. Introduce el mensaje
				System.out.println("Introduce el mensaje:");
				mensajeEnviado = scanner.nextLine();
				
				//2. Enviar el mensaje al servidor
				
				salida.println(mensajeEnviado);
				System.out.println("Mensaje enviado: " + mensajeEnviado);
				
				//3. Leer la respuesta del servidor
				
				String respuesta = entrada.readLine();
				System.out.println("Mensaje recibido: " + respuesta);
				
				//4. Finalizar la conexión
				
				if(respuesta.equals("#Desconectado#")) {
					continuar = false;
					System.out.println("Conexión finalizada.");
				}
				
				
			}
			

			
		} catch (UnknownHostException e) {
			System.out.println("Erorr Host Desconocido: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erorr E/S en el Cliente: " + e.getMessage());
		}
		
		
		
	}
	
}
