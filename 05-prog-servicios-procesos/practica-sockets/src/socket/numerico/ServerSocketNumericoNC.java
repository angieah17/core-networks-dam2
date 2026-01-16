package socket.numerico;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketNumericoNC {
	
	private int port; // Número de puerto donde el servidor escucha o el cliente se conecta
	private Socket socket; // Representa la conexión activa entre cliente y servidor
	private ServerSocket serverSocket; // Se usa en el servidor para abrir un puerto y esperar conexiones 	 
	private InputStream is; // Canal por el que se reciben datos desde el otro extremo
	private OutputStream os; // Canal por el que se envían datos al otro extremo
	
	
	//Constructor que se debe crear:
	
	public ServerSocketNumericoNC(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
	}
	
	
	public void start() throws IOException {
		
		System.out.printf("[Servidor: %d] Esperando conexión ...%n", port);
		socket = serverSocket.accept();
		System.out.printf("[Servidor: %d] Conexión establecida.%n", port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		
	}
	
	public void stop() throws IOException {
		System.out.printf("[Servidor: %d] Cerrando conexiones.%n", port);
		is.close();
		os.close();
		socket.close();//esta línea cierra las líneas de comunicación, solo que se agregan para ser más específicos en el ejemplo de lo que sucede
		System.out.printf("[Servidor: %d] Conexión cerrada.%n", port);
	}
	
	
	
	public static void main(String[] args) {
		
		
		try {
			ServerSocketNumericoNC server = new ServerSocketNumericoNC(8081);
			server.start();
			int datoLeido = server.is.read(); //el protocolo de comunicación es que va a recibir un dato numérico
			System.out.printf("[Servidor: %d] Dato recibido: (%d).%n", server.port, datoLeido );
			int datoADevolver = datoLeido + 1;
			server.os.write(datoADevolver);
			System.out.printf("[Servidor: %d] Dato enviado: (%d).%n", server.port, datoADevolver);
			server.stop(); //Para el servidor
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	}
	
}
