package examen.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	
	private static final int PUERTO = 6000;
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("=== SERVIDOR INICIADO ===");
			System.out.printf("Esperando conexiones desde el puerto: %d%n", PUERTO);
			
			while (true) {
				
				Socket socketCliente = serverSocket.accept();
				Thread hiloCliente = new Thread(new GestorServerSocket(socketCliente));
				hiloCliente.start();
				
			}
			
			
		} catch (IOException e) {
			System.out.printf("Error iniciando el servidor: %s%n", e.getMessage());
		}
		
	}
	
}
