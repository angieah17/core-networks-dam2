package socket.numerico;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketNumericoC {
	
	/*Hacer lo mismo con texto*/
	
	private int port; //si coincide el port con el port del server acaba habiendo socket
	private String address; 
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	
	public ClienteSocketNumericoC(int port, String address) {
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
		
		ClienteSocketNumericoC cliente = new ClienteSocketNumericoC(8081, "localhost");
		
		try {
			cliente.start();
			for (int i = 0; i < 254; i++) {
				int datoAEnviar = i;
				System.out.printf("[Client] Enviando %d a %s:%d.%n", 
						datoAEnviar, cliente.address, cliente.port);
				cliente.os.write(datoAEnviar);
				int datoRecibido = cliente.is.read();
				System.out.printf("[Client] Recibido %s %d de %s:%d.%n", 
						datoRecibido - datoAEnviar == 1 ? "bien": "mal",
						datoRecibido, cliente.address, cliente.port);	
			}
			cliente.stop();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
