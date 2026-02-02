package socket.examen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import socker.practica.ManejadorCliente;

public class ServidorSocket {
	
	private static final int PUERTO = 8081;
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("=== SE HA INICIADO EL SERVIDOR ===");
			System.out.printf("Esperando conexiones en el puerto: %s%n", PUERTO);
			while (true) {
				Socket clienteSocket = serverSocket.accept();
				System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());
                
				Thread hiloCliente = new Thread(new GestorServerSocket(clienteSocket));
				hiloCliente.start();
			}
			
			
		} catch (IOException e) {
			System.out.println("Error iniciando el servidor: " + e.getMessage());
			
		}
		
		
		
	}
	
}
