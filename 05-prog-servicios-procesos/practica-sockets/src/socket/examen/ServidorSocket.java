package socket.examen;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorSocket {
	
	//1. Atributos
	private ServerSocket server;
	private int port;
	
	
	//2. Constructor del Servidor, se crea a partir del puerto
	
	public ServidorSocket(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}




	public static void main(String[] args) {
		
	}
}
