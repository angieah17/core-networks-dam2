package socker.practica;

import java.net.Socket;

public class ManejadorCliente implements Runnable {
	
	//1. Atributo
	private Socket socket;
	
	//2. Constructor necesita el socket
	
	public ManejadorCliente(Socket socket) {
		this.socket = socket;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
