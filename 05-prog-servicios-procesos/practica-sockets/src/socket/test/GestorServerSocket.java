package socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GestorServerSocket implements Runnable {
	
	private Socket socket;
		

	public GestorServerSocket(Socket socket) {
		this.socket = socket;
	}



	@Override
	public void run() {
		try (PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			
			String mensajeRecibido;
			
			while ((mensajeRecibido = entrada.readLine()) != null) {
				//1. Lee el mensajeRecibido
				System.out.printf("Cliente [%s] - Petición: %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//2. Procesa el mensaje recibido
				String respuesta = procesarMensaje(mensajeRecibido);
				
				//3. Envia respuesta
				
				salida.println(respuesta);
				System.out.printf("Cliente [%s] - Respuesta: %s%n", Thread.currentThread().getName(), respuesta);
								
				//4. Finaliza en caso de fin
				
				if(respuesta.equals("#Fin#")) {
					System.out.println("Cliente desconectado %s%n" + socket.getInetAddress());
					break;
				}
				
			}
			
			
			
			
		} catch (IOException e) {
			System.out.println("Error E/S" + e.getMessage());
		}
		
	}
	
	public String procesarMensaje (String mensajeRecibido) {
		
		//Caso 1 #Fin#
		if(mensajeRecibido.equals("#Fin#")) return "#Finalizado#";
		
		//Caso 2 #Error#
		
		String [] partes = mensajeRecibido.split("#");
		
		//2.1 Validación longitud
		if(partes.length != 4) return "#Error#";
		
		//2.2 Validación de números antes de parsear
		
		if(!partes[2].matches("-?\\d+") || !partes[3].matches("-?\\d+")) return  "#Error#";
		
		// Parsear
		
		int inicio = Integer.parseInt(partes[2]);
		int fin = Integer.parseInt(partes[3]);
		
		//2.3 Validación lógica
		
		if(inicio > fin) return "#Error#";
		
		//Comando para elegir el caso
		
		String comando = partes[1];
		
		//Caso 3
		if(comando.equals("Listado números")) {
			StringBuilder resultado = new StringBuilder();
			
			for (int i = inicio; i <= fin; i++) {
				resultado.append(i);
				if( i < fin) {
					resultado.append("|");
				}
			}
			
			return resultado.toString();
		}else if (comando.equals("Numero aleatorio")) {
			Random random = new Random();
			int numAleatorio = random.nextInt(inicio, fin + 1);
			return String.valueOf(numAleatorio);
		}else {
			return "#Error#";
		}
		
		
		
		
	}
	

}
