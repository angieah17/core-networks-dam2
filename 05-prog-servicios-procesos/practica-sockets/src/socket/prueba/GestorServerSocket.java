package socket.prueba;

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
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			String mensajeRecibido;
			
			while ((mensajeRecibido = entrada.readLine()) != null) {
				//1. Leer la petición
				System.out.printf("Cliente [%s] - Petición: %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//2. procesarMensaje
				
				String respuesta = procesarMensaje(mensajeRecibido);
				
				//3. Enviar respuesta
				salida.println(respuesta);
				System.out.printf("Cliente [%s] - Respuesta: %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//4. Finalizar en caso de 
				
				if(respuesta.equals("#Finalizado")) {
					System.out.printf("Cliente desconectado [%s]%n", Thread.currentThread().getName());
					break;
				}
				
				
			}
			
			
		} catch (IOException e) {
			System.out.println("Error E/S: " + e.getMessage());
		}
		
		
	}
	
	private String procesarMensaje (String mensajeRecibido) {
		
		//Caso 1 Fin
		if(mensajeRecibido.equals("#Fin#")) return "#Finalizado#";
		
		//Caso 2 Error
		
		String [] partes = mensajeRecibido.split("#");
		
		//Validacion de longitud
		if(partes.length != 4) return "#Error";
		
		//Validacion de numero
		
		if(!partes[2].matches("-?\\d+") || !partes[3].matches("-?\\d+") ) return "#Error";
		
		int inicio = Integer.parseInt(partes[2]);
		int fin = Integer.parseInt(partes[3]);
		
		//Validacion logica
		
		if(inicio > fin)  return "#Error";
		
		//Caso 3 y 4 comando
		
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
		}else if(comando.equals("Numero aleatorio")) {
			Random random = new Random();
			int numeroAleatorio =
			random.nextInt(inicio, fin + 1);
			return String.valueOf(numeroAleatorio);
			
		} else {
			return "#Error";
		}
		
		
	}

}
