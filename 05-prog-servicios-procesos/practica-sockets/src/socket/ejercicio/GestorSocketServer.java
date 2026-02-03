package socket.ejercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GestorSocketServer implements Runnable {

	
	private Socket socket;
	
	
	
	public GestorSocketServer(Socket socket) {
		this.socket = socket;
	}



	@Override
	public void run() {
		
		try (PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			//1. Leemos el mensaje recibido
			
			String mensajeRecibido;
			
			while ((mensajeRecibido = entrada.readLine()) != null) {
				
				System.out.printf("Petición del [%s] : %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//2. Procesar el mensaje Recibido
				
				String respuesta = procesarMensaje(mensajeRecibido);
				
				//3. Enviar la respuesta
				
				salida.println(respuesta);
				System.out.printf("Respuesta enviada a [%s] : %s%n", Thread.currentThread().getName(), respuesta);
				
				//4. En caso de #Finalizado# cerrar conexión
				
				if(respuesta.equals("#Finalizado#")) {
					System.out.println("Cliente deconectado" + socket.getInetAddress());
					break;
				}
				
			}
			
			
		} catch (IOException e) {
			System.out.println("Error E/S en el servidor" + e.getMessage());
		}
		
		
	}
	
	
	
	public String procesarMensaje (String mensajeRecibido) {
		
		//Caso 1 - Finalizado
		
		if(mensajeRecibido.equals("#Fin#")) return "#Finalizado#";
		
		//Caso 2 - #Error#
		
		//Verificamos la longitud
		String [] partes = mensajeRecibido.split("#");
		for (String p : partes) {
			System.out.println(p);
		}
		if(partes.length != 4) return "#Error#";  
		
		//Verificamos si es número antes de parsear
				
		
		if(!partes[2].matches("-?\\d+") || !partes[3].matches("-?\\d+")) return "#Error#";
		
		//parseamos
		
		int inicio = Integer.parseInt(partes[2]);
		int fin = Integer.parseInt(partes[3]);
		
		//Comando
		
		String comando = partes[1];
		
		if(comando.equals("Listado números")) {
			
			StringBuilder resultado = new StringBuilder();
			
			for (int i = inicio; i <= fin; i++) {
				resultado.append(i);
				if(i < fin) {
					resultado.append("|");
				}
			}
			
			
			return resultado.toString();
		} else if(comando.equals("Numero aleatorio")) {
			Random random = new Random();
			 int numeroAleatorio = random.nextInt(inicio, fin + 1);
			
			return String.valueOf(numeroAleatorio);
		}else {
			return "#Error#";
		}

	}
	
}
