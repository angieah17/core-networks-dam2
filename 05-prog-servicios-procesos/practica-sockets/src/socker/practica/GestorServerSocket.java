package socker.practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GestorServerSocket implements Runnable  {

	private Socket socket;
	
	public GestorServerSocket(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void run() {
		
		try (PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			String mensajeRecibido;
			System.out.println("LLEGUE AQUI");
			//1. Leer mensaje recibido
			while ((mensajeRecibido = entrada.readLine()) != null) {
				
				//2. Mostrar la petición recibida
				System.out.printf("Hilo: [%s] Peticion: %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//3. Procesar la petición
				
				String respuesta = procesarPeticion(mensajeRecibido);
				
				//4. Enviar la respuesta y mostrarla
				
				salida.println(respuesta);
				System.out.printf("Hilo: [%s] Respuesta: %s%n", Thread.currentThread().getName(), respuesta);
				
				//si es fin se cierra la conexión
				
				if(mensajeRecibido.equals("#Fin#")) {
					System.out.printf("Cliente desconectado de: %s%n", socket.getInetAddress());
					break;
				}
				
			}
			
			
			
		} catch (IOException e) {
			System.out.println("Error E/S" + e.getMessage());
		}
		
	}
	
	
	public String procesarPeticion(String mensajeRecibido) {
		System.out.println("LLEGUE AQUI");
		//Caso 1 #Fin#
		if(mensajeRecibido.equals("#Fin#")) return "#Finalizado#";
		
		//Caso 2 #Error# validaciones
		
		String [] partes  = mensajeRecibido.split("#");
		
		//2.1 lenght que tenga exactamente 4 parámetros
		
		if (partes.length != 4) {
            return "#Error#";
        }
		
		//2.2 verificar si son números antes de parsear
		if(!partes[2].matches("-?\\d+") || !partes[3].matches("-?\\d+")) {
			return "#Error#";
		}
		
		//2.3 Parsear
		
		int inicio = Integer.parseInt(partes[2]);
		int fin = Integer.parseInt(partes[3]);
		
		//2.4 Verificación lógica 
		
		if(inicio > fin) {
			return "#Error#";
		}
		
		//2.5 Aplicación a cada caso (3 y 4)
		
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
			int numeroRandom = random.nextInt(inicio, fin + 1);
			return String.valueOf(numeroRandom) ;
		}else {
			return "#Error#";
		}
		
	
	}

	
	

}
