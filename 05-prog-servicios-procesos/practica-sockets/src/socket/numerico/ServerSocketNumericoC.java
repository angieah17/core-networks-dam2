package socket.numerico;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketNumericoC {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private OutputStream os;
	private InputStream is;
	private int port;
	
	public ServerSocketNumericoC(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}

	public void start() throws IOException {
		System.out.printf("[Servidor] Esperando conexión ...%n");
		socket = serverSocket.accept();
		System.out.printf("[Servidor] Conexión establecida.%n");
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	public void stop() throws IOException {
		System.out.printf("[Server] Cerrando conexión ...%n");
		//is.close();
		//os.close();
		socket.close();
		serverSocket.close();
		System.out.printf("[Server] Conexión cerrada.%n");
	}
	
	
	public static void main(String[] args) {
		
		try {
			
			ServerSocketNumericoC server = new ServerSocketNumericoC(8081);
			server.start();
			while(true) {
				int datoLeido = server.is.read();
				if(datoLeido == -1) break;
				System.out.printf("[Server] Dato recibido: %d %n", datoLeido);
				int datoADevolver = ++datoLeido;
				server.os.write(datoADevolver);
				System.out.printf("[Server] Dato enviado: %d %n", datoLeido);				
			}
			server.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
