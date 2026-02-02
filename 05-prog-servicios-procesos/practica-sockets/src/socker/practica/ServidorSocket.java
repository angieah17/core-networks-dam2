package socker.practica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	private static final int PUERTO = 8081;
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("=== SERVIDOR INICIADO ===");
			
			while (true) {
				Socket clienteSocket = serverSocket.accept();
				
				Thread hiloCliente = new Thread (new ManejadorCliente(clienteSocket));
				hiloCliente.start();
			}
			
		} catch (IOException e) {
			System.out.println("Error en el servidor");
		}
		
		
	}
	
}
