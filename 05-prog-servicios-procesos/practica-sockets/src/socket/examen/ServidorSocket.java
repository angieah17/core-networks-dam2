package socket.examen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	
	//1. Atributos
	private static final int PUERTO = 8081;
	


	public static void main(String[] args) {
		
		
		try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
			
			System.out.println("====SERVIDOR SOCKET INICIADO ====");
			
			//Bucle infinito para aceptar múltiples clientes
			
			while (true) {
				Socket clienteSocket = serverSocket.accept();
				
				//Thread hiloCliente = new Thread();
				
			}
			
		} catch (IOException e) {
			
		} finally {
			
		}
		
		
	}
}
