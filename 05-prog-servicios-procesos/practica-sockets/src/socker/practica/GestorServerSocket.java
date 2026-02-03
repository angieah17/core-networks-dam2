package socker.practica;

import java.net.Socket;

public class GestorServerSocket implements Runnable  {

	private Socket socket;
	
	public GestorServerSocket(Socket socket) {
		this.socket = socket;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
