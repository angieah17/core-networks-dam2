package socket.prueba;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	
	private static final int PUERTO = 8086;
	
	public static void main(String[] args) {
		
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("=== SERVIDOR INICIADO ===");
			System.out.printf("Esperando conexiones desde: %d%n", PUERTO);
			
			while (true) {
				
				Socket socketClient = serverSocket.accept();
				
				Thread hiloCliente = new Thread(new GestorServerSocket(socketClient));
				hiloCliente.start();
			}
			
			
		} catch (IOException e) {
			System.out.println("Error iniciando el servidor" + e.getMessage());
		}
		
		
		
		
	}
	
}
