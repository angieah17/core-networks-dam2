package socket.texto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTextNoCiclico {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private int port;
	
	//Las siguientes son propiedades para soportar el texto en la comunicación
	
	private PrintWriter pw;
	private InputStreamReader isr; //este no es necesario ponerlo como propiedad
	private BufferedReader br;
	
	
	//2. Constructor
	
	public ServerSocketTextNoCiclico(int port) {
		this.port = port;
	}
	
	/*PrintWriter algo que me permite guardar texto, se monta a través
	 * del os. Es necesario ponerle el true para que haga flash. 
	 * 
	 * El BufferedReader: tira de isr y el isr tira de is, lo cual, permite
	 * leer textos. 
	 * 
	 * El isr no es necesario declararlo como propiedad porque en realidad,
	 * se puede llamar dentro del BufferedReader: new InputStreamReader (is).
	 * 
	 * Necesito los métodos:
	 *  1. Crear o cerrar conexión. 
	 *  2. Crear canales de comunicación: es pw, isr, br ... 
	 * 
	 * Antes no era necesario, porque socket permite únicamente
	 * enviar y recibir bynarios (transformados en int), entonces
	 * necesitamos algo que permita transformar texto en ese int, byt.
	 * 
	 * */
	
	//3. Iniciar la conexión 
	
	public void start() {
		
	}
	
	
	
}
