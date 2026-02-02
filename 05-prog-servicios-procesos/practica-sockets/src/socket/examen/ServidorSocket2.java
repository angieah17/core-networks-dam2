package socket.examen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket2 {
	
	//1. Atributos
	private static final int PUERTO = 8081;
	

	//2. Main para dejar la clase en un bucle infinito
	public static void main(String[] args) {
				
		try (ServerSocket serverSocket = new ServerSocket(PUERTO)) { //El serverSocket se crea a partir de un puerto, comienza a eschar
			
			System.out.println("====SERVIDOR SOCKET INICIADO ====");
			
			//Bucle infinito para aceptar múltiples clientes
			
			while (true) {
				Socket clienteSocket = serverSocket.accept(); //Bloquea el hilo hasta que llega un cliente y cuando se genera devuelve un objeto socket
				
				//Por cada clienteSocket (por cada conexión establecida se crea un hilo)
				Thread hiloCliente = new Thread(new GestorSocketServer2(clienteSocket));
				hiloCliente.start();
				
			}
			
		} catch (IOException e) {
			System.out.println("Error en el servidor");
		} 
		
	}
}
