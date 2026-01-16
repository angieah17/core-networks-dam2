package socket.numerico;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class ClientSocketNumericoNC {
	
	private int port; //si coincide el port con el port del server acaba habiendo socket
	private String address; 
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	
	public ClientSocketNumericoNC(String address, int port) {
		this.port = port;
		this.address = address;
	}
	
	public void start() throws UnknownHostException, IOException {
		System.out.printf("[Cliente: %s%d] Solicitando conexión ...%n", address, port);
		socket = new Socket(address, port);
		System.out.printf("[Cliente: %s%d] Conexión establecida ...%n", address, port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	public void stop() throws UnknownHostException, IOException {
		System.out.printf("[Cliente: %s%d] Cerrando conexión ...%n", address, port);
		is.close();
		os.close();
		socket.close();
		System.out.printf("[Cliente: %s%d] Conexión cerrada ...%n", address, port);
	}
	
	public static void main(String[] args) {
		
		ClientSocketNumericoNC client = new ClientSocketNumericoNC("localhost", 8081);
		
		try {
			client.start();
			int datoAEnviar = new Random().nextInt(256);
			client.os.write(datoAEnviar);
			System.out.printf("[Cliente: %s%d] Dato enviado (%d) ...%n", client.address, client.port, datoAEnviar);
			int datoRecibido = client.is.read();
			System.out.printf("[Cliente: %s%d] Dato recibido (%d) ...%n", client.address, client.port, datoRecibido);
			client.stop();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
