package socket.examen;

import java.net.Socket;

public class ManejadorCliente implements Runnable {
	
	private Socket socket;
	
	public ManejadorCliente (Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
