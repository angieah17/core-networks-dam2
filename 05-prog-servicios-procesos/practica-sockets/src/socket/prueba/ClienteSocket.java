package socket.prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {

	public static final String HOST = "localhost";
	public static final int PUERTO = 8086;
	
	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
				PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in)) {
			
			
			String mensajeEnviado;
			boolean continuar = true; 
			
			while (continuar) {
				
				//1. El usuario introduce el mensaje
				System.out.println("Introduce el mensaje: " );
				mensajeEnviado = scanner.nextLine();
								
				//2. Se envía el mensaje:
				salida.println(mensajeEnviado);
				System.out.printf("Mensaje enviado: %s%n",Thread.currentThread().getName(), mensajeEnviado);
				
				//3. Se recibe la respuesta
				
				String respuesta = entrada.readLine();
				System.out.printf("Mensaje recibido: %s%n",Thread.currentThread().getName(), respuesta);
				
				//4. Se procesa el mensaje 
				
				if(respuesta.equals("#Error#")) {
					System.out.println("Mensaje no adecuadamente formateado para su tratamiento.");
				} else if(respuesta.equals("#Finalizado#")) {
					continuar = false;
					System.out.println("Fin de la conexión");
				}
				
			
				
			}
			
			
		} catch (UnknownHostException e) {
			System.out.println("Error host desconocido: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error E/S: " + e.getMessage());
		}
		
	}
	
	
}
