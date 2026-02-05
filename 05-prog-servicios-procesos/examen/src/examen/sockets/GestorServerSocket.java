package examen.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
			
			//1. Leer la petición del cliente
			while ((mensajeRecibido = entrada.readLine()) != null) {
				
				System.out.printf("Cliente [%s] - Petición recibida: %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//2. Procesar mensaje
				
				String respuesta = procesarMensaje(mensajeRecibido);
				
								
				//3. Enviamos respuesta al cliente
				
				salida.println(respuesta);
				System.out.printf("Cliente [%s] - Respuesta enviada: %s%n", Thread.currentThread().getName(), respuesta);
				
				//4. Finalizar 
				
				if(respuesta.equals("#Desconectado#")) {
					System.out.printf("Cliente desconectado %s%n", socket.getInetAddress());
					break;
				}
				
			}
			
			
		} catch (IOException e) {
			System.out.println("Error E/S en el servidor: " + e.getMessage());
		}
		
	}
	
	
	
	private String procesarMensaje(String mensajeRecibido) {
		
		//1. Caso 1 Fin
		
		if(mensajeRecibido.equals("#Desconectar#")) return "#Desconectado#";
		
		
		String [] partes = mensajeRecibido.split("#");
		
		
		//2. CASO SUMA Y MULTIPLICACION
		if(partes.length == 4) {
			String comando = partes[1];
			//2.1 Validacion numeros antes de parsear
			
			if(!partes[2].matches("-?\\d+") || !partes[3].matches("-?\\d+")) return "#Instrucción no válida#";
			
			int x = Integer.parseInt(partes[2]);
			int y = Integer.parseInt(partes[3]);
			
			//2.2 Comando para caso
			
				//A. Suma
				
				if(comando.equals("Suma")) {
					int suma = x + y;
					return String.valueOf(suma);
				} else if(comando.equals("Multiplicación")){ // B. Multiplicación
					int multi = x * y;
					return String.valueOf(multi);
				} else {
					return "#Instrucción no válida#";
				}
				
		}
		
		//3. CASO PALÍNDROMO
		
		if(partes.length == 3) {
			String comando = partes[1];
			if(comando.equals("Palíndromo")) {
				
				String palabra = partes[2];
				
				boolean iguales = false;
				
				StringBuilder palabraReves = new StringBuilder();
								
				for (int i = palabra.length() -1 ; i >= 0; i--) {
					
					palabraReves.append(palabra.charAt(i));
					
				}
				
				
				if(palabra.equals(palabraReves.toString())) {
					iguales = true;
				} else {
					iguales = false;
				}
				
				
				if(iguales) {
					return "Sí";
				} else {
					return "No";
				}
				
			} else {
				return "#Instrucción no válida#";
			}
			
			
		}

		
		
		
		
		return "#Instrucción no válida#";
	}
	
	
}
