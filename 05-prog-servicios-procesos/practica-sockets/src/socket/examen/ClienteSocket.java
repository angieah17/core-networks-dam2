package socket.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
	
	//1. Atributos
	
	private static final int PUERTO = 8081;
	private static final String HOST = "localhost";
	
	//2. Método auxiliar
	private static void procesarMensaje(String mensajeEnviado, String respuesta) {
		if(respuesta.equals("#Error#")) {
			System.out.println("Mensaje no adecuadamente formateado para su tratamiento.");
		} else if (respuesta.equals("#Finalizado#")) {
			System.out.println("Fin de la conexión");
		} else {
			System.out.println("Resultado: " + respuesta);
		}
	}
	
	
	public static void main(String[] args) {
		
		try (Socket socket = new Socket(HOST, PUERTO);
			PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in)) {
			
			String mensajeEnviado;
			boolean continuar = true;
			
			while (continuar) {
				
				//1.1 El usuario introduzca el mensaje
				System.out.println("Introduzca el mensaje;");
				mensajeEnviado = scanner.nextLine();
								
				//1.2 Enviar mensaje al servidor
				salida.println(mensajeEnviado);
				System.out.println("Mensaje enviado al servidor: " + mensajeEnviado);
				
				//1.3 Leer respuesta del servidor
				String respuesta = entrada.readLine();
				System.out.println("Mensaje recibido del servidor: " + respuesta);
				
				//1.4 Procesar la respuesta del servidor
				
				procesarMensaje(mensajeEnviado, respuesta);
				
				//1.5 Finalizar en caso de #Finalizado#
				
				if(respuesta.equals("#Finalizado#")) {
					continuar = false;
				}
				
			}
			
			
		} catch (UnknownHostException e) {
			System.out.println("No se pudo encontrar el servidor");
		} catch ( IOException e) {
			System.out.println("Error de Entrada/Salida");
		}
		
		
		
	}
	
}
