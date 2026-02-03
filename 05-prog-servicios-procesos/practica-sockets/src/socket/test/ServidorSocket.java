package socket.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	private static final int PUERTO = 8083;
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO);) {
			
			System.out.println("===SERVIDOR INICIADO===");
			
			
			while (true) {
				Socket socketCliente = serverSocket.accept();
				System.out.printf("Esperando conexiones en el puerto: %s%n", PUERTO);
				
				Thread hiloCliente = new Thread(new GestorServerSocket(socketCliente));
				hiloCliente.start();
			}
			
		} catch (IOException e) {
			System.out.println("Error estableciendo conexión en el servidor");
		}
		
	}
}
