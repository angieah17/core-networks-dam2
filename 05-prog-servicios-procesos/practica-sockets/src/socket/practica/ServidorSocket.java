package socket.practica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	
	private static final int PUERTO = 8082;
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("===SERVIDOR INICIADO===");
			System.out.printf("Esperando conexiones en el puerto: %s%n", PUERTO);
			while (true) {
				Socket socketCliente = serverSocket.accept();
				System.out.printf("Cliente conectado desde: %s%n", socketCliente.getInetAddress());
				Thread clienteHilo = new Thread(new GestorServerSocket(socketCliente));
				clienteHilo.start();
				
			}
			
		} catch (IOException e) {
			System.out.println("Error inicando el servidor: " + e.getMessage());

		}
		
		
	}
	
}
