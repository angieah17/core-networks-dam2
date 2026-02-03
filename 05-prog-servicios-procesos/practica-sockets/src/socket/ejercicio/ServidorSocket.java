package socket.ejercicio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	
	private static final int PUERTO = 8084;
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("=== INICIADO EL SERVIDOR ===");
			System.out.printf("Se abre conexión a partir del puerto: %d%n", PUERTO);
			
			while (true) {
				
				Socket socketClient = serverSocket.accept(); 
				
				Thread hiloCliente = new Thread(new GestorSocketServer(socketClient));
				hiloCliente.start();				
				
			}
			
		} catch (IOException e) {
			System.out.println("Error iniciando el servidor" + e.getMessage());
		}
		
	}
	
}
